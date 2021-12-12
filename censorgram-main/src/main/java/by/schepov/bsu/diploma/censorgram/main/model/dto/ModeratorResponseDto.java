package by.schepov.bsu.diploma.censorgram.main.model.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class ModeratorResponseDto {
    private UUID id;
    private Boolean inappropriate;
    private Double probability;
}
