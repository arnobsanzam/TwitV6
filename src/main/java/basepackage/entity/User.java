package basepackage.entity;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table(name = "User")
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotFound(action = NotFoundAction.IGNORE)
    @Column(name = "userid")
    private Long userid;

    @Column(name = "username",unique = true, length = 255)
    @NotEmpty
    @NotNull
    @Size(min = 3, max = 15)
    @Pattern(regexp = "^[a-z0-9_-]{3,15}$")
    private String username;


    @Column(name = "password")
    @NotNull
    @NotEmpty
    private String password;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private Collection<Tweet> tweets = new ArrayList<>();

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Tweet> getTweets() {
        return tweets;
    }

    public void setTweets(Collection<Tweet> tweets) {
        this.tweets = tweets;
    }
}
