package xyz.formeky.permissionverify.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.formeky.permissionverify.annotions.CheckRole;
import xyz.formeky.permissionverify.enums.GlobalRoleEnum;
import xyz.formeky.permissionverify.exception.RejectRoleException;
import xyz.formeky.permissionverify.token.TokenMsg;
import xyz.formeky.permissionverify.token.TokenSettings;
import xyz.formeky.permissionverify.util.TokenUtil;

/**
 * @author zcw
 */
@Aspect
public class CheckRoleAspect {

    @Autowired
    private TokenSettings tokenSettings;

    @Around("@annotation(xyz.formeky.permissionverify.annotions.CheckRole)")
    public void checkRole(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        CheckRole annotation = signature.getMethod().getAnnotation(CheckRole.class);
        GlobalRoleEnum[] roles = annotation.roles();
        GlobalRoleEnum[] excludeRoles = annotation.excludeRoles();
        TokenMsg tokenMsg = TokenUtil.getMsgFromToken(
                ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest(),
                tokenSettings.getTokenSecretKey());
        if (roles.length==0&&excludeRoles.length!=0){
            for (GlobalRoleEnum anEnum : excludeRoles){
                if (tokenMsg.getRole().equals(anEnum.getValue())) {
                    throw new RejectRoleException("拒绝访问");
                }
            }
        }
        for (GlobalRoleEnum anEnum : roles){
            if (tokenMsg.getRole().equals(anEnum.getValue())) {
                joinPoint.proceed();
                return;
            }
        }
    }
}
