package by.schepov.bsu.diploma.censorgram.main.mapper;

import by.schepov.bsu.diploma.censorgram.main.model.dto.PostDto;
import by.schepov.bsu.diploma.censorgram.main.model.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {

    PostDto postToDto(Post post);
}
