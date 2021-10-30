package xyz.formeky.permissionverify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.formeky.permissionverify.exception.JwtExpireException;
import xyz.formeky.permissionverify.exception.JwtNotExistException;
import xyz.formeky.permissionverify.exception.JwtVerifyException;
import xyz.formeky.permissionverify.token.TokenBuilder;
import xyz.formeky.permissionverify.token.TokenMsg;
import xyz.formeky.permissionverify.token.TokenSettings;
import xyz.formeky.permissionverify.util.TokenUtil;

@SpringBootTest
class PermissionVerifyApplicationTests {
    @Autowired
    private TokenSettings tokenSettings;

    @Test
    void contextLoads() {


        TokenMsg tokenMsg = new TokenMsg(1,1,"1");
        String token = TokenUtil.buildOJToken(new TokenBuilder(tokenMsg, tokenSettings.getTokenSecretKey() ));
        System.out.println(token);

        try {
            System.out.println(TokenUtil.getMsgFromToken(token, tokenSettings.getTokenSecretKey()));
        } catch (JwtExpireException e) {
            e.printStackTrace();
        } catch (JwtVerifyException e) {
            e.printStackTrace();
        } catch (JwtNotExistException e) {
            e.printStackTrace();
        }
    }

}
