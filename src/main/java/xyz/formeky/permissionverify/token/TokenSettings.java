package xyz.formeky.permissionverify.token;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author JirathLiu
 * @date 2020/11/19
 * @description:
 */
@Component
public class TokenSettings {
    @Value("${token.secretKey:qwerqwerqr34345r#535@($URUHFH}")
    private  String tokenSecretKey;

    @Value("${token.millisecond:3000}")
    private String millisecond;

    public  String getTokenSecretKey() {
        return tokenSecretKey;
    }

    public String getMillisecond() {
        return millisecond;
    }
}
