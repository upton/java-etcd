package net.lexuan.etcd;

import java.io.IOException;

/**
 * Etcd exception 
 * @author Upton
 *
 */
public class EtcdException extends IOException {
    private static final long serialVersionUID = 1L;

    private final Integer httpStatusCode;
    private final EtcdResponse result;

    public EtcdException(String message) {
        super(message);
        this.httpStatusCode = null;
        this.result = null;
    }
    
    public EtcdException(String message, Throwable cause) {
        super(message, cause);
        this.httpStatusCode = null;
        this.result = null;
    }

    public EtcdException(String message, int httpStatusCode) {
        super(message + "(httpStatusCode=" + httpStatusCode + ")");
        this.httpStatusCode = httpStatusCode;
        this.result = null;
    }

    public EtcdException(String message, EtcdResponse result) {
        super(message);
        this.httpStatusCode = null;
        this.result = result;
    }

    public int getHttpStatusCode() {
        return httpStatusCode;
    }

    public boolean isHttpError() {
        return httpStatusCode != null;
    }

    public boolean isEtcdError() {
        return result != null && result.getErrorCode() != null;
    }
}