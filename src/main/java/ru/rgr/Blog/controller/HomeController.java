package ru.rgr.Blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.User;
import ru.rgr.Blog.service.PostService;
import ru.rgr.Blog.service.UserService;

import java.util.List;
import java.util.UUID;

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

//        log.info("posts : {}", posts);
        model.addAttribute("posts", posts);
        log.info("Got first " + posts.size() +" posts");
        return "/main";
    }

    @GetMapping("/post-create")
    public String createPostForm(Model model) {
        Post post = new Post();
        model.addAttribute("post", post);
        return "post-create";
    }

    @PostMapping("/post-create")
    public String createPost(Post post) {
        User user = userService.findUserByUsername("Valera");
        post.setUserId(user.getUserId());
        // TODO: 25.12.2022 костыль, пока нет spring security

        log.info("create new post: {}", post);
        postService.create(post);
        return "redirect:/";
    }

    @PostMapping("/{id}")
    public String deletePost(@PathVariable("id") UUID postId) {
        postService.deleteById(postId);
        return "redirect:/";
    }
}
