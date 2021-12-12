package by.schepov.bsu.diploma.censorgram.main.service.impl;

import by.schepov.bsu.diploma.censorgram.main.model.dto.ModeratorRequestDto;
import by.schepov.bsu.diploma.censorgram.main.model.dto.ModeratorResponseDto;
import by.schepov.bsu.diploma.censorgram.main.service.ModeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ModeratorServiceImpl implements ModeratorService {

    @Value("${moderator.base-url}")
    private String moderatorBaseUrl;
    @Value("${moderator.api.inspect}")
    private String inspectApiUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public ModeratorResponseDto inspectText(UUID id, String text) {
        ModeratorRequestDto requestDto = new ModeratorRequestDto().setId(id).setText(text);
        return restTemplate.postForEntity(moderatorBaseUrl + inspectApiUrl, requestDto, ModeratorResponseDto.class).getBody();
    }

}
