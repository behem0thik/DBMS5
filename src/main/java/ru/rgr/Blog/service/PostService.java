package ru.rgr.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.Tag;
import ru.rgr.Blog.repository.PostRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class PostService {
    private final PostRepository postRepository;

    @Autowired
    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> findAll() {
        return postRepository.findAll();
    }

    public Post findPostById(UUID postId) {
        return postRepository.getReferenceById(postId);
    }

    public List<Post> findPostsByUser(UUID userId) {
        return postRepository.findPostsByUserId(userId);
    }

    public List<Post> findPostsByTags(Collection<Set<Tag>> tags) {
        return postRepository.findAllByTagsIn(tags);
    }

    public Post create(Post post) {
        return postRepository.save(post);
    }

    @Transactional
    public Post update(UUID postId, Post post) {
        postRepository.deleteById(postId);
        return postRepository.save(post);
    }

    public void deleteById(UUID postId) {
        postRepository.deleteById(postId);
    }
}
