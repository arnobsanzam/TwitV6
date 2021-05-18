package basepackage.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public class TweetResponse
{
    private Long id;
    private String message;
    private String username;

    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private LocalDateTime timestamp;

    public TweetResponse(Long id,String message, String username, LocalDateTime timestamp)
    {
        this.id = id;
        this.message = message;
        this.username = username;
        this.timestamp = timestamp;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
