package ru.rgr.Blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.Tag;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface PostRepository extends JpaRepository<Post, UUID> {
    List<Post> findPostsByUserId(UUID userId);

    List<Post> findAllByTagsIn(Collection<Set<Tag>> tags);
}
