package xyz.formeky.permissionverify.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import xyz.formeky.permissionverify.annotions.CheckRole;
import xyz.formeky.permissionverify.enums.GlobalRoleEnum;

/**
 * @author zcw
 */
@Aspect
public class CheckRoleAspect {

    @Around("@annotation(xyz.formeky.permissionverify.annotions.CheckRole)")
    public void checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckRole annotation = signature.getMethod().getAnnotation(CheckRole.class);
        GlobalRoleEnum[] roles = annotation.roles();
        GlobalRoleEnum[] excludeRoles = annotation.excludeRoles();
        if (roles.length==0&&excludeRoles.length!=0){
            for (GlobalRoleEnum anEnum : excludeRoles){
                // @TODO: 2021/10/28 与jwt中获取的信息进行比对
            }
        }else {
            for (GlobalRoleEnum anEnum : roles){
                // @TODO: 2021/10/28 与jwt中获取的信息进行比对
            }
        }
        joinPoint.proceed();


    }
}
