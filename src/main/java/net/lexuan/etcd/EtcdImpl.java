package net.lexuan.etcd;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * etcd api implement
 * @author Upton
 *
 */
public class EtcdImpl implements Etcd {
    private final static Logger logger = LoggerFactory.getLogger(EtcdImpl.class);

    private ExecutorService watchListenerExecutorService = Executors.newCachedThreadPool(new ThreadFactory() {
        private AtomicInteger threadCount = new AtomicInteger(0);

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "WatchListener-Thread-" + threadCount.getAndIncrement());
        }
    });

    private EtcdClient client;

    public EtcdImpl(EtcdClient client) {
        this.client = client;
    }

    @Override
    public EtcdResponse addChild(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse addChildDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse compareAndDelete(String key, String prevValue, long prevIndex) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse compareAndSwap(String key, String value, long ttl, String prevValue, long prevIndex) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse create(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse createDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse createInOrder(String dir, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse delete(String key, boolean recursive) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse deleteDir(String key) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse get(String key, boolean sort, boolean recursive) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse set(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse setConsistency(String consistency) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse setDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse update(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse updateDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void watch(String prefix, long waitIndex, boolean recursive, WatchListener listener) throws EtcdException {
        if (listener == null) {
            throw new EtcdException("WatchListener must bo not null");
        }

        EtcdRequest request = new EtcdRequest();
        watchListenerExecutorService.submit(new WatchTask(request, listener, false));
    }

    public void watchOnce(String prefix, long waitIndex, boolean recursive, WatchListener listener) throws EtcdException {
        if (listener == null) {
            throw new EtcdException("WatchListener must bo not null");
        }

        EtcdRequest request = new EtcdRequest();
        watchListenerExecutorService.submit(new WatchTask(request, listener, true));
    }
    
    @Override
    public String[] getCluster() {
        // TODO Auto-generated method stub
        return null;
    }

    class WatchTask implements Runnable {
        private EtcdRequest request;
        private WatchListener listener;
        private boolean watchOne;

        public WatchTask(EtcdRequest request, WatchListener listener, boolean watchOne) {
            this.request = request;
            this.listener = listener;
            this.watchOne = watchOne;
        }

        @Override
        public void run() {
            do {
                try {
                    EtcdResponse response = client.cancelableGet(request, listener);
                    listener.onChange(response);
                    request.setWaitIndex(response.getNode().getModifiedIndex() + 1);
                } catch (Exception e) {
                    logger.error("watch ectd error", e);
                }

                if (listener.isCancelled()) {
                    break;
                }
            } while (!watchOne);
        }
    }
}