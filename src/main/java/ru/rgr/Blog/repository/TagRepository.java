package ru.rgr.Blog.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.Tag;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Repository
public interface TagRepository extends JpaRepository<Tag, UUID> {
    Tag findTagByTagName(String tagName);

    List<Tag> findTagsByPosts(Set<Post> posts);
}
