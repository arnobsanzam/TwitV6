package basepackage.payload;



import basepackage.entity.User;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TweetPayload
{
    @NotNull(message = "Tweet cannot be null")
    @NotEmpty(message = "Tweet cannot be empty")
    private String message;

    private User user;
    private LocalDateTime timestamp;
    public TweetPayload()
    {
        timestamp = LocalDateTime.now();
    }
    public TweetPayload(String message)
    {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public User getUser() {
        return user;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public void setUser(User user) {
        this.user = user;
    }
}


