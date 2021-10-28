package xyz.formeky.permissionverify;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import xyz.formeky.permissionverify.token.TokenBuilder;
import xyz.formeky.permissionverify.token.TokenMsg;

@SpringBootTest
class PermissionVerifyApplicationTests {

    @Autowired
    private TokenMsg testToken;
    @Test
    void contextLoads() {
        System.out.println(testToken);
    }

}
