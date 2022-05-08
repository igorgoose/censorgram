package by.schepov.bsu.diploma.censorgram.main.service.impl;

import by.schepov.bsu.diploma.censorgram.main.mapper.PostMapper;
import by.schepov.bsu.diploma.censorgram.main.model.dto.ModeratorResponseDto;
import by.schepov.bsu.diploma.censorgram.main.model.dto.PostDto;
import by.schepov.bsu.diploma.censorgram.main.model.entity.Post;
import by.schepov.bsu.diploma.censorgram.main.model.entity.PostAnalytics;
import by.schepov.bsu.diploma.censorgram.main.model.entity.PostStatus;
import by.schepov.bsu.diploma.censorgram.main.model.entity.User;
import by.schepov.bsu.diploma.censorgram.main.repository.PostAnalyticsRepository;
import by.schepov.bsu.diploma.censorgram.main.repository.PostRepository;
import by.schepov.bsu.diploma.censorgram.main.repository.PostStatusRepository;
import by.schepov.bsu.diploma.censorgram.main.repository.UserRepository;
import by.schepov.bsu.diploma.censorgram.main.service.ModeratorService;
import by.schepov.bsu.diploma.censorgram.main.service.PostService;
import by.schepov.bsu.diploma.censorgram.main.util.SecurityUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final ModeratorService moderatorService;
    private final PostAnalyticsRepository postAnalyticsRepository;
    private final PostStatusRepository postStatusRepository;
    private final PostMapper postMapper;
    private final UserRepository userRepository;
    @Value("${moderator.confidence-limit}")
    private double confidenceLimit;

    @Override
    public List<PostDto> getMyPosts() {
        Long userId = SecurityUtils.getCurrentUserId();
        return postRepository.findByUserIdOrderByUpdatedDateDescCreatedDateDesc(userId).stream()
                             .map(postMapper::postToDto)
                             .collect(Collectors.toList());
    }

    @Override
    public Page<PostDto> getMyPosts(Pageable pageable) {
        Long userId = SecurityUtils.getCurrentUserId();
        return postRepository.findByUserIdOrderByUpdatedDateDescCreatedDateDesc(userId, pageable)
                             .map(postMapper::postToDto);
    }

    @Transactional
    @Override
    public PostDto createPost(String text) {
        UUID uuid = UUID.randomUUID();
        ModeratorResponseDto moderatorResponseDto = moderatorService.inspectText(uuid, text);
        Post post = new Post()
                .setId(uuid)
                .setText(text)
                .setCreatedDate(LocalDateTime.now())
                .setUser(userRepository.getById(SecurityUtils.getCurrentUserId()))
                .setStatus(resolveStatusByModeratorResult(moderatorResponseDto));
        postRepository.save(post);
        PostAnalytics postAnalytics = new PostAnalytics()
                .setPost(post)
                .setCreatedDate(LocalDateTime.now())
                .setProbability(moderatorResponseDto.getProbability())
                .setToxic(moderatorResponseDto.getInappropriate());
        postAnalyticsRepository.save(postAnalytics);
        return postMapper.postToDto(post);
    }

    private PostStatus resolveStatusByModeratorResult(ModeratorResponseDto moderatorResponseDto) {
        String code;
        if (moderatorResponseDto.getInappropriate()) {
            if (moderatorResponseDto.getProbability() > confidenceLimit) {
                code = PostStatus.Code.BLOCKED.name();
            } else {
                code = PostStatus.Code.SUSPICIOUS.name();
            }
        } else {
            code = PostStatus.Code.ACTIVE.name();
        }
        return postStatusRepository.findByCode(code);
    }
}
