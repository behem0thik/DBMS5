package ru.rgr.Blog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tags")
public class Tag {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "tag_id", updatable = false, nullable = false, unique = true)
    private UUID tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "posts_tags",
            joinColumns = { @JoinColumn(name = "tag_id") },
            inverseJoinColumns = { @JoinColumn(name = "post_id") }
    )
    Set<Post> posts = new HashSet<>();
}