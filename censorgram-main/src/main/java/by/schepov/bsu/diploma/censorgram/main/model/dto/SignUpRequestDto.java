package by.schepov.bsu.diploma.censorgram.main.model.dto;

import by.schepov.bsu.diploma.censorgram.main.model.entity.User;
import lombok.Data;

import java.time.LocalDate;

@Data
public class SignUpRequestDto {
    private String username;
    private String password;
    private String passwordControl;
    private String email;
    private LocalDate dob;

    public User convert() {
        return new User().setUsername(username).setPassword(password).setEmail(email).setDob(dob);
    }
}
