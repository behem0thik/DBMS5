package ru.rgr.Blog.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "tags")
public class Tag implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "tag_id", updatable = false, nullable = false, unique = true)
    @ColumnDefault("random_uuid()")
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID tagId;

    @Column(name = "tag_name")
    private String tagName;

    @ManyToMany(mappedBy = "tags")
//    @ToString.Exclude
    @JsonIgnore
    private Set<Post> posts = new HashSet<>();

    public Tag(String tagName) {
        this.tagName = tagName;
    }
}