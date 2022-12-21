package ru.rgr.Blog.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

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

//    @ManyToMany
//    @JoinTable(name="posts_tags")
//    private Set<Post> posts;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "posts_tags",
            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")},
            inverseJoinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "post_id")}
    )
    private Set<Post> posts;

//    @ManyToMany
//    @JoinTable(
//            name = "posts_tags",
//            joinColumns = {@JoinColumn(name = "tag_id", referencedColumnName = "tag_id")},
//            inverseJoinColumns = {@JoinColumn(name = "post_id", referencedColumnName = "post_id")}
//    )
//    public Set<Post> posts = getPosts();


//    // In Customer class:
//
//     @ManyToMany
//     @JoinTable(name="CUST_PHONE",
//         joinColumns=
//             @JoinColumn(name="CUST_ID", referencedColumnName="ID"),
//         inverseJoinColumns=
//             @JoinColumn(name="PHONE_ID", referencedColumnName="ID")
//         )
//     public Set<PhoneNumber> getPhones() { return phones; }
//
//     // In PhoneNumberClass:
//
//     @ManyToMany(mappedBy="phones")
//     public Set<Customer> getCustomers() { return customers; }
}