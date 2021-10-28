package xyz.formeky.permissionverify.token;

/**
 * @author zcw
 * @description:token实体类
 */
public class TokenMsg {
    private Integer id;
    private Integer role;
    private String username;

    public TokenMsg() {
    }

    public TokenMsg(Integer id, Integer role, String username) {
        this.id = id;
        this.role = role;
        this.username = username;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "TokenMsg{" +
                "id=" + id +
                ", role=" + role +
                ", username='" + username + '\'' +
                '}';
    }
}
