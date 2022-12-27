package ru.rgr.Blog.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.User;
import ru.rgr.Blog.service.PostService;
import ru.rgr.Blog.service.UserService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/posts")
@Slf4j
public class PostController {
    private final PostService postService;
    private final UserService userService;

    @Autowired
    public PostController(PostService postService, UserService userService) {
        this.postService = postService;
        this.userService = userService;
    }

    @GetMapping
    public String findAll(Model model) {
        List<Post> posts = postService.findAll();

        model.addAttribute("posts", posts);
        log.info("Got all posts");
        return "/posts";
    }

    @GetMapping("/create")
    public String createForm(Model model) {
        Post post = new Post();

        model.addAttribute("post", post);
        log.info("post creation form");
        return "post-create";
    }

    @PostMapping("/create")
    public String create(Post post) {
        // TODO: 25.12.2022 костыль, пока нет spring security
        User user = userService.findUserByUsername("Valera");
        post.setUserId(user.getUserId());

        postService.create(post);
        log.info("Create new post with given id: {}", post.getPostId());
        return "redirect:/posts";
    }

    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable UUID id, Model model) {
        Post post = postService.findPostById(id);

        model.addAttribute("post", post);
        log.info("update form to post with id");
        return "post-update";
    }

    @PostMapping("/update/{id}")
    public String update(Post post, @PathVariable UUID id) {
        // TODO: 25.12.2022 костыль, пока нет spring security
        User user = userService.findUserByUsername("Valera");
        post.setUserId(user.getUserId());

        postService.update(id, post);
        log.info("Updated post with given id: {}", post.getPostId());
        return "redirect:/posts";
    }

    @PostMapping("/{id}")
    public String deleteById(@PathVariable("id") UUID postId) {
        postService.deleteById(postId);
        log.info("Delete post with given id: {}", postId);
        return "redirect:/posts";
    }

    @GetMapping("/{id}")
    public String readMore(@PathVariable("id") UUID postId, Model model) {
        Post post = postService.findPostById(postId);

        log.info("Got post with given id: {}", post.getPostId());
        model.addAttribute("post", post);
        return "post";
    }
}
