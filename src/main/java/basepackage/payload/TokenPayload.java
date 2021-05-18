package basepackage.payload;

public class TokenPayload {
    String uuid;
    String username;

    public TokenPayload(){

    }

    public TokenPayload(String uuid, String username){
        this.uuid = uuid;
        this.username = username;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
