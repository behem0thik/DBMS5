package ru.rgr.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.User;
import ru.rgr.Blog.repository.UserRepository;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findUserById(UUID userId) {
        return userRepository.getReferenceById(userId);
    }

    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public List<User> findUsersByPosts(Collection<List<Post>> posts) {
        return userRepository.findAllByPostsIn(posts);
    }

    public User create(User user) {
        return userRepository.save(user);
    }

    public User update(UUID userId, User user) {
        return userRepository.save(user);
    }

    public void deleteById(UUID userId) {
        userRepository.deleteById(userId);
    }
}
