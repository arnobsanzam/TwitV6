package basepackage.response;

public class UserResponse
{
    private Long userid;
    private String username;

    public UserResponse(Long userid, String username)
    {
        this.userid = userid;
        this.username = username;
    }

    public UserResponse() {

    }

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
}
