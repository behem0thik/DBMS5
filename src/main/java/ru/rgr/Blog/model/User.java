package ru.rgr.Blog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UpdateTimestamp;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "user_id", updatable = false, nullable = false, unique = true)
    @ColumnDefault("random_uuid()")
    private UUID userId;

    @Column(name = "username", unique = true)
    private String username;

    @Column(name = "password")
    private String password;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "users", fetch = FetchType.LAZY)
    private List<Post> posts;

    @Column(name = "created_at", updatable = false)
    @ColumnDefault("current_timestamp()")
    @CreationTimestamp
    private Date createdAt;

    @Column(name = "updated_at")
    @ColumnDefault("current_timestamp()")
    @UpdateTimestamp
    private Date updatedAt;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
