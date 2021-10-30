package xyz.formeky.permissionverify.token;

/**
 * Token内容构造器，必须包含TokenMsg对象和后端密钥
 * 允许定制过期时间，使用者，颁发者
 * default millisecond = 30min
 * default jwtIssuer = user
 * default jwtAud = SWUST_OJ
 * @author zcw
 * @date 2021/10/30
 * @description:
 */
public class TokenBuilder {
    private TokenMsg msg;
    private String secretKey;
    private int millisecond = 30*1000*60;
    private String jwtIssuer = "user";
    private String jwtAud = "kda";

    public TokenBuilder(TokenMsg msg, String secretKey, int millisecond, String jwtIssuer, String jwtAud) {
        this.msg = msg;
        this.secretKey = secretKey;
        this.millisecond = millisecond;
        this.jwtIssuer = jwtIssuer;
        this.jwtAud = jwtAud;
    }

    public TokenBuilder(TokenMsg msg, String secretKey) {
        this.msg = msg;
        this.secretKey = secretKey;
    }
    public TokenBuilder millisecond(int millisecond){
        this.millisecond=millisecond;
        return this;
    }

    public TokenBuilder jwtIssuer(String jwtIssuer) {
        this.jwtIssuer=jwtIssuer;
        return this;
    }

    public TokenBuilder jwtAud(String jwtAud) {
        this.jwtAud = jwtAud;
        return this;
    }

    public TokenMsg getMsg() {
        return msg;
    }

    public void setMsg(TokenMsg msg) {
        this.msg = msg;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public int getMillisecond() {
        return millisecond;
    }

    public void setMillisecond(int millisecond) {
        this.millisecond = millisecond;
    }

    public String getJwtIssuer() {
        return jwtIssuer;
    }

    public void setJwtIssuer(String jwtIssuer) {
        this.jwtIssuer = jwtIssuer;
    }

    public String getJwtAud() {
        return jwtAud;
    }

    public void setJwtAud(String jwtAud) {
        this.jwtAud = jwtAud;
    }
}
