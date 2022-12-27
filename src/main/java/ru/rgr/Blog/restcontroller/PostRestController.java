package ru.rgr.Blog.restcontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.service.PostService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/rest/posts")
@RequiredArgsConstructor
@Slf4j
public class PostRestController {
    private PostService postService;

    @Autowired
    public PostRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping
    public List<Post> findAll() {
        List<Post> allPosts = postService.findAll();
        log.info("Got all Posts");
        return allPosts;
    }

    @GetMapping("/{id}")
    public Post findPostById(@PathVariable("id") UUID id) {
        Post foundPost = postService.findPostById(id);
        log.info("Got a Post with given id: {}", id);
        return foundPost;
    }

    @GetMapping("/{id}/posts")
    public List<Post> findPostsByAuthorId(@PathVariable("id") UUID id) {
        List<Post> postsByUser = postService.findPostsByUser(id);
        log.info("Got Posts with given user id: {}", id);
        return postsByUser;
    }

    @PostMapping("/upload")
    public Post create(@Valid @ModelAttribute Post post) {
        Post newPost = postService.create(post);
        log.info("Uploaded a new Post");
        return newPost;
    }

    @PutMapping("/{id}")
    public Post update(@PathVariable("id") UUID id, @Valid @ModelAttribute Post post) {
        Post updatedPost = postService.update(id, post);
        log.info("Updated a Post with given id: {}", id);
        return updatedPost;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        postService.deleteById(id);
        log.info("Deleted a Post with given id={}", id);
    }
}
