package by.schepov.bsu.diploma.censorgram.main.controller.rest;

import by.schepov.bsu.diploma.censorgram.main.model.dto.PostDto;
import by.schepov.bsu.diploma.censorgram.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostRestController {
    private final PostService postService;

    @GetMapping("/my")
    public Page<PostDto> getMyPosts(Pageable pageable) {
        return postService.getMyPosts(pageable);
    }
}
