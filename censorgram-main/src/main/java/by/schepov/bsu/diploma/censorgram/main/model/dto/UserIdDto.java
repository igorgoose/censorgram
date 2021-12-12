package by.schepov.bsu.diploma.censorgram.main.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserIdDto {
    private Long id;
    private String username;
}
