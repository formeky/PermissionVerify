package xyz.formeky.permissionverify.exception;

/**
 * @author zcw
 */
public class JwtNotExistException extends Exception{
    public JwtNotExistException() {
        super();
    }

    public JwtNotExistException(String message) {
        super(message);
    }

    public JwtNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtNotExistException(Throwable cause) {
        super(cause);
    }

    protected JwtNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
