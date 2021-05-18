package basepackage.entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

@Entity
@Table(name = "Tweet")
public class Tweet
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tweetid")
    private Long tweetid;

    @Column(name = "message", nullable = false)
    @NotEmpty
    @NotEmpty
    private String message;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private User user;

    public Long getTweetid() {
        return tweetid;
    }

    public void setTweetid(Long tweetid) {
        this.tweetid = tweetid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
