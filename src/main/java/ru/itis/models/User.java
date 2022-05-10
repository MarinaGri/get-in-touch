package ru.itis.models;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@EqualsAndHashCode(exclude = {"subscriptions", "posts", "comments", "resumes"})
@ToString(exclude = {"subscriptions", "posts", "comments", "resumes"})
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(
        name = "account",
        indexes = {@Index(columnList = "email"), @Index(columnList = "password")}
)
@SQLDelete(sql = "UPDATE account SET state = 'DELETED' where id = ?")
@Where(clause = "state != 'DELETED'")
public class User {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private String token;

    private Long vkId;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "subscriber_subscription",
            joinColumns = @JoinColumn(name = "subscriber_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "subscription_id", referencedColumnName = "id")
    )
    private Set<User> subscriptions = new HashSet<>();


    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Post> posts;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Comment> comments;

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private Set<Resume> resumes;

    @OneToOne
    private FileInfo avatar;

    @Enumerated(value = EnumType.STRING)
    private State state;

    @Enumerated(value = EnumType.STRING)
    private Role role;

    public enum State {
        ACTIVE,
        BANNED,
        DELETED
    }

    public enum Role {
        ROLE_USER, ROLE_ADMIN
    }

    public boolean isActive() {
        return this.state == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.state == State.BANNED;
    }

    public boolean isAdmin() {
        return this.role == Role.ROLE_ADMIN;
    }

}
