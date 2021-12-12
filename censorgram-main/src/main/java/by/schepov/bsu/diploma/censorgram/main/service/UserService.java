package by.schepov.bsu.diploma.censorgram.main.service;

import by.schepov.bsu.diploma.censorgram.main.model.dto.SignUpRequestDto;
import by.schepov.bsu.diploma.censorgram.main.model.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.transaction.Transactional;

public interface UserService extends UserDetailsService {
    @Transactional
    User registerUser(SignUpRequestDto userDto);
}
