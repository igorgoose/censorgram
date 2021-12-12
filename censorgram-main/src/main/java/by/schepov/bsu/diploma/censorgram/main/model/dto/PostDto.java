package by.schepov.bsu.diploma.censorgram.main.model.dto;

import by.schepov.bsu.diploma.censorgram.main.model.entity.PostStatus;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Accessors(chain = true)
public class PostDto {
    private UUID id;
    private UserIdDto user;
    private String text;
    private PostStatus status;
    private LocalDateTime createdDate;
}
