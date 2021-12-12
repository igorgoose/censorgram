package by.schepov.bsu.diploma.censorgram.main.service;

import by.schepov.bsu.diploma.censorgram.main.model.dto.PostDto;

import java.util.List;

public interface PostService {
    List<PostDto> getMyPosts();

    void createPost(String text);
}
