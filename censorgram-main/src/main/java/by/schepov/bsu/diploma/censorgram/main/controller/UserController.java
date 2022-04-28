package by.schepov.bsu.diploma.censorgram.main.controller;

import by.schepov.bsu.diploma.censorgram.main.model.entity.Role;
import by.schepov.bsu.diploma.censorgram.main.model.entity.User;
import by.schepov.bsu.diploma.censorgram.main.model.entity.UserStatus;
import by.schepov.bsu.diploma.censorgram.main.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

//@Controller
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.getById(id));
        return "users/edit";
    }

    @PostMapping("put")
    public String edit(@ModelAttribute("user") User user) {
        user.setRole(new Role().setId(2));
        user.setStatus(new UserStatus().setId(1));
        userService.updateUser(user);
        return "redirect:/";
    }

}
