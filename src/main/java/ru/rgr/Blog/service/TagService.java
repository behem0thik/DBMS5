package ru.rgr.Blog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.rgr.Blog.model.Post;
import ru.rgr.Blog.model.Tag;
import ru.rgr.Blog.repository.TagRepository;

import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Service
public class TagService {

    private final TagRepository tagRepository;

    @Autowired
    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<Tag> findAll() {
        return tagRepository.findAll();
    }

    public List<Tag> findTagsByPosts(Collection<Set<Post>> posts) {
        return tagRepository.findAllByPostsIn(posts);
    }

    public Tag findTagByTagName(String tagName) {
        return tagRepository.findTagByTagName(tagName);
    }

    public Tag create(Tag tag) {
        return tagRepository.save(tag);
    }

    public Tag update(UUID tagId, Tag tag) {
        return tagRepository.save(tag);
    }

    public void deleteById(UUID tagId) {
        tagRepository.deleteById(tagId);
    }
}
