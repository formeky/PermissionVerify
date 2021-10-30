package xyz.formeky.permissionverify.conf;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xyz.formeky.permissionverify.token.TokenMsg;

/**
 * @author zcw
 * @description:
 */
@Configuration
public class AppConfig{

    @Bean
    @ConditionalOnMissingBean
    public TokenMsg tokenMsg(){
        return new TokenMsg(1,1,"1");
    }

}
