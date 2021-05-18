package basepackage.entity;


import javax.persistence.*;

@Entity
@Table(name = "Token")
public class Token
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tokenid")
    private Long tokenid;

    @Column(name = "uuid")
    private String uuid;

    @Column(name = "username", unique = true)
    private String username;

    public Long getTokenid() {
        return tokenid;
    }

    public void setTokenid(Long tokenid) {
        this.tokenid = tokenid;
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
