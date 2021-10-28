package xyz.formeky.permissionverify.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import xyz.formeky.permissionverify.token.TokenMsg;

/**
 * @author zcw
 */
@Aspect
public class InjectTokenAspect {

    @Around("@annotation(xyz.formeky.permissionverify.annotions.InjectToken)")
    public Object injectToken(ProceedingJoinPoint joinPoint) throws Throwable {
        TokenMsg tokenMsg = new TokenMsg(); //TODO
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
