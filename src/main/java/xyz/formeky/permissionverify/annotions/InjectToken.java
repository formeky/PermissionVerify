package xyz.formeky.permissionverify.annotions;

import java.lang.annotation.*;

/**
 * @author zcw
 * @description:token注入
 */
@Target(ElementType.METHOD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectToken {
}
