package ru.rgr.Blog.restcontroller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.rgr.Blog.model.Tag;
import ru.rgr.Blog.service.TagService;

import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping("/rest/tags")
public class TagRestController {
    private TagService tagService;

    @Autowired
    public TagRestController(TagService tagService) {
        this.tagService = tagService;
    }

    @GetMapping
    public List<Tag> findAll() {
        List<Tag> foundTags = tagService.findAll();
        log.info("Got all Tags");
        return foundTags;
    }

//    @GetMapping("/{posts}")
//    public List<Tag> findTagsByPosts(@PathVariable("posts") Collection<Set<Post>> posts) {
//        List<Tag> tagsByPosts = tagService.findTagsByPosts(posts);
//        log.info("Got all tags with given posts:{}", posts);
//        return tagsByPosts;
//    }

    @GetMapping("/{name}")
    public Tag findTagByTagName(@PathVariable("name") String tagName) {
        Tag tag = tagService.findTagByTagName(tagName);
        log.info("Got Tag with given name: {}", tagName);
        return tag;
    }

    @PostMapping
    public Tag create(@Valid @RequestBody Tag tag) {
        Tag newTag = tagService.create(tag);
        log.info("Uploaded a new Tag");
        return newTag;
    }

    @PutMapping("/{id}")
    public Tag update(@PathVariable("id") UUID id, @Valid @RequestBody Tag tag) {
        Tag updatedTag = tagService.update(id, tag);
        log.info("Updated a Tag with given id: {}", id);
        return updatedTag;
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable("id") UUID id) {
        tagService.deleteById(id);
        log.info("Deleted a Tag with given id={}", id);
    }

}
