package ru.rgr.Blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.User;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
    User findByUsername(String username);

    List<User> findAllByPostsIn(Collection<List<Post>> posts);
}
