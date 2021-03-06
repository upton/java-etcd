package net.lexuan.etcd;

import com.alibaba.fastjson.JSON;

/**
 * Etcd response object
 * @author Upton
 * 
 */
public class EtcdResponse {
    // Base values
    private String action;
    private EtcdNode node;
    private EtcdNode prevNode;

    // error response
    private Integer errorCode;
    private String message;
    private String cause;
    private int errorIndex;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public EtcdNode getNode() {
        return node;
    }

    public void setNode(EtcdNode node) {
        this.node = node;
    }

    public EtcdNode getPrevNode() {
        return prevNode;
    }

    public void setPrevNode(EtcdNode prevNode) {
        this.prevNode = prevNode;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public int getErrorIndex() {
        return errorIndex;
    }

    public void setErrorIndex(int errorIndex) {
        this.errorIndex = errorIndex;
    }

    public boolean hasError() {
        return errorCode != null;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }
}