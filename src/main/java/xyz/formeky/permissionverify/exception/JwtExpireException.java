package xyz.formeky.permissionverify.exception;

/**
 * @author zcw
 */
public class JwtExpireException extends Exception{
    public JwtExpireException() {
        super();
    }

    public JwtExpireException(String message) {
        super(message);
    }

    public JwtExpireException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtExpireException(Throwable cause) {
        super(cause);
    }

    protected JwtExpireException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
