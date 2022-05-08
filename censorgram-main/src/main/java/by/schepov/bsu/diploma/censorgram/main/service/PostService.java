package by.schepov.bsu.diploma.censorgram.main.service;

import by.schepov.bsu.diploma.censorgram.main.model.dto.PostDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PostService {
    List<PostDto> getMyPosts();

    Page<PostDto> getMyPosts(Pageable pageable);

    PostDto createPost(String text);
}
