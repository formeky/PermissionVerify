package xyz.formeky.permissionverify.annotions;

import xyz.formeky.permissionverify.enums.GlobalRoleEnum;

import java.lang.annotation.*;

/**
 * @author zcw
 * 校验角色
 */
@Target(ElementType.METHOD)
@Documented
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface CheckRole {
    GlobalRoleEnum[] roles() default {};
    GlobalRoleEnum[] excludeRoles() default {};
}
