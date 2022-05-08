package by.schepov.bsu.diploma.censorgram.main.controller.rest;

import by.schepov.bsu.diploma.censorgram.main.model.dto.PostCreateDto;
import by.schepov.bsu.diploma.censorgram.main.model.dto.PostDto;
import by.schepov.bsu.diploma.censorgram.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {
    private final PostService postService;

    @GetMapping("/my")
    public Page<PostDto> getMyPosts(Pageable pageable) {
        return postService.getMyPosts(pageable);
    }

    @PostMapping
    public PostDto createPost(@RequestBody PostCreateDto postCreateDto) {
        return postService.createPost(postCreateDto.getMessage());
    }
}
