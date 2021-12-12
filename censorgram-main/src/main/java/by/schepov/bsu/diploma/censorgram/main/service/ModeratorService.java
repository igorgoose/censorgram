package by.schepov.bsu.diploma.censorgram.main.service;

import by.schepov.bsu.diploma.censorgram.main.model.dto.ModeratorResponseDto;

import java.util.UUID;

public interface ModeratorService {
    ModeratorResponseDto inspectText(UUID id, String text);
}
