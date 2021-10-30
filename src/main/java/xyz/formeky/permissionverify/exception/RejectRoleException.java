package xyz.formeky.permissionverify.exception;

/**
 * @author zcw
 */
public class RejectRoleException extends Exception{
    public RejectRoleException() {
        super();
    }

    public RejectRoleException(String message) {
        super(message);
    }

    public RejectRoleException(String message, Throwable cause) {
        super(message, cause);
    }

    public RejectRoleException(Throwable cause) {
        super(cause);
    }

    protected RejectRoleException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
