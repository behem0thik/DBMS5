package ru.rgr.Blog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@NoArgsConstructor
@Data
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "post_id", updatable = false, nullable = false, unique = true)
    private UUID postId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    @Column(name = "user_id")
//    @Type(type = "uuid-char")
    private UUID userId;

    @Column(name = "article")
    private String article;

    @Column(name = "post_content")
    @Lob @Basic(fetch=LAZY)
    private String content;

    @Column(name = "image",  columnDefinition="BLOB NOT NULL")
    @Lob @Basic(fetch=LAZY)
    private byte[] image;

    @ManyToMany(mappedBy="posts")
    private Set<Tag> tags;

    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp()")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp()")
    @UpdateTimestamp
    private Date updatedAt;

    public Post(UUID userId, String article, String content, Set<Tag> tags) {
        this.userId = userId;
        this.article = article;
        this.content = content;
        this.tags = tags;
    }
}
