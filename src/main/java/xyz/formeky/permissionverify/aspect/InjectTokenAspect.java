package xyz.formeky.permissionverify.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import xyz.formeky.permissionverify.token.TokenMsg;
import xyz.formeky.permissionverify.token.TokenSettings;
import xyz.formeky.permissionverify.util.TokenUtil;

/**
 * @author zcw
 */
@Aspect
public class InjectTokenAspect {

    @Autowired
    private TokenSettings tokenSettings;

    @Around("@annotation(xyz.formeky.permissionverify.annotions.InjectToken)")
    public Object injectToken(ProceedingJoinPoint joinPoint) throws Throwable {
        TokenMsg tokenMsg = TokenUtil.getMsgFromToken(((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(),
                tokenSettings.getTokenSecretKey());
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Class[] parameterTypes = signature.getParameterTypes();
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < parameterTypes.length; i++) {
            if (parameterTypes[i].equals(TokenMsg.class)) {
                args[i] = tokenMsg;
            }
        }
        return joinPoint.proceed(args);
    }
}
