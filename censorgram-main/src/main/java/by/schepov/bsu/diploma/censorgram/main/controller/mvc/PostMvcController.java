package by.schepov.bsu.diploma.censorgram.main.controller.mvc;

import by.schepov.bsu.diploma.censorgram.main.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
//@Controller
@RequestMapping("/posts")
public class PostMvcController {
    private final PostService postService;

    @GetMapping("/my")
    public String getMyPosts(Model model) {
        model.addAttribute("posts", postService.getMyPosts());
        return "posts/my";
    }

    @GetMapping("/create")
    public String create() {
        return "posts/create";
    }

    @PostMapping
    public String save(@ModelAttribute("content") String text) {
        postService.createPost(text);
        return "redirect:/posts/my";
    }
}
