package ru.rgr.Blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static jakarta.persistence.FetchType.EAGER;

@Entity
@NoArgsConstructor
@Data
@Table(name = "posts")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    @Column(name = "post_id", updatable = false, nullable = false, unique = true)
    @ColumnDefault("random_uuid()")
    private UUID postId;

    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID userId;

    @Column(name = "article")
    private String article;

    @Column(name = "post_content")
    private String content;

    //    @Column(name = "image", columnDefinition = "BLOB NULL")
    @Column(name = "image")
    @Basic(fetch = EAGER)
    @JdbcTypeCode(SqlTypes.BLOB)
    private byte[] image;

    @ManyToMany(fetch = EAGER, cascade = CascadeType.ALL)
    @JoinTable(
            name = "posts_tags",
            joinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "post_id")},
            inverseJoinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")}
    )
    @JsonIgnore
    private Set<Tag> tags = new HashSet<>();

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
//        this.image = image;
        this.tags = tags;
    }
}
