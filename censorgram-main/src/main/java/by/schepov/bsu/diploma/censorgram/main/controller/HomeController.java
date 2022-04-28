package by.schepov.bsu.diploma.censorgram.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

//@Controller
public class HomeController {

    @GetMapping
    public String home() {
        return "redirect:/posts/my";
    }
}
