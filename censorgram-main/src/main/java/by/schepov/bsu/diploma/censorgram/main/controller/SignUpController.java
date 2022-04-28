package by.schepov.bsu.diploma.censorgram.main.controller;

import by.schepov.bsu.diploma.censorgram.main.model.dto.SignUpRequestDto;
import by.schepov.bsu.diploma.censorgram.main.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

//@Controller
@AllArgsConstructor
public class SignUpController {

    private final UserService userService;

    @GetMapping("/signup")
    public String signUp(@ModelAttribute("user") SignUpRequestDto requestDto) {
        return "signup";
    }

    @PostMapping("/signup")
    public String registerUser(@ModelAttribute("user") SignUpRequestDto requestDto) {
        userService.registerUser(requestDto);
        return "redirect:/login";
    }
}
