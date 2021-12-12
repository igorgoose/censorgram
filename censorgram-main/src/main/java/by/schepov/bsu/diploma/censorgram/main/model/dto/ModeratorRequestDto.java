package by.schepov.bsu.diploma.censorgram.main.model.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.UUID;

@Data
@Accessors(chain = true)
public class ModeratorRequestDto {
    private UUID id;
    private String text;
}
