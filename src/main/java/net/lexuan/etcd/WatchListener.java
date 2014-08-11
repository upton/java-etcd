package net.lexuan.etcd;

import org.apache.http.client.methods.HttpUriRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * callback listener on watch etcd changed 
 * @author Upton
 *
 */
public abstract class WatchListener {
    private final static Logger logger = LoggerFactory.getLogger(WatchListener.class);

    private boolean cancelled = false;
    private HttpUriRequest request;

    protected void setRequest(HttpUriRequest request){
        this.request = request;
    }
    
    public void cancel() {
        if (request != null && !request.isAborted()) {
            try {
                request.abort();
            } catch (UnsupportedOperationException e) {
                logger.error("cancel WatchListener error", e);
            }
        }
        
        cancelled = true;
    }
    
    public boolean isCancelled(){
        return cancelled;
    }

    abstract void onChange(EtcdResponse result);
}