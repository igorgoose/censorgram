package by.schepov.bsu.diploma.censorgram.main.service.impl;

import by.schepov.bsu.diploma.censorgram.main.model.dto.SignUpRequestDto;
import by.schepov.bsu.diploma.censorgram.main.model.entity.User;
import by.schepov.bsu.diploma.censorgram.main.repository.UserRepository;
import by.schepov.bsu.diploma.censorgram.main.security.details.AppUserDetails;
import by.schepov.bsu.diploma.censorgram.main.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.HttpClientErrorException;

import javax.transaction.Transactional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public static final String EMAIL_REGEX = "[a-zA-z0-9]+@[a-zA-z0-9]+\\.[a-zA-z]+";

    @Transactional
    @Override
    public AppUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                                  .orElseThrow(() -> new UsernameNotFoundException(String.format("Username '%s' not found!", username)));
        return new AppUserDetails(user.getId(), user.getUsername(), user.getPassword(), user.getRole().getAuthorities());
    }

    @Transactional
    @Override
    public User registerUser(SignUpRequestDto userDto) {
        validateNewUser(userDto);
        User user = userDto.convert();
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "User '" + userDto.getUsername() + "' already exists.");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return user;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND));
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    private void validateNewUser(SignUpRequestDto requestDto) {
        if (requestDto.getUsername() == null || requestDto.getUsername().equals("")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Username must not be empty!");
        }
        if (requestDto.getPassword() == null || requestDto.getPassword().equals("")) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password must not be empty!");
        }
        if (!requestDto.getPassword().equals(requestDto.getPasswordControl())) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Password and password control fields must match!");
        }
        if (!requestDto.getEmail().matches(EMAIL_REGEX)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "Email is not valid!");
        }
    }
}
