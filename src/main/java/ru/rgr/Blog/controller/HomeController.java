package ru.rgr.Blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.service.PostService;
import ru.rgr.Blog.service.UserService;

import java.util.List;

@Controller
@RequestMapping("/")
@Slf4j
public class HomeController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public HomeController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String findFivePosts(Model model) {
        List<Post> posts = postService.findAll().stream().limit(3).toList();

        model.addAttribute("posts", posts);
        log.info("Got first " + posts.size() + " posts");
        return "/main";
    }
}
