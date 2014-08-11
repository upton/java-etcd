package net.lexuan.etcd;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;

public class EtcdImpl implements Etcd {
    private final static Logger logger = LoggerFactory.getLogger(EtcdImpl.class);
    
    // only support v2 version
    private final static String V2 = "v2/keys";

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
    public EtcdResponse watch(String prefix, long waitIndex, boolean recursive, WatchListener listener) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public String[] getCluster() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawCompareAndDelete(String key, String prevValue, long prevIndex) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawCompareAndSwap(String key, String value, long ttl, String prevValue, long prevIndex) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawCreate(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawCreateDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawCreateInOrder(String dir, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawDelete(String key, boolean recursive, boolean dir) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawGet(String key, boolean sort, boolean recursive) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawSet(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawSetDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawUpdate(String key, String value, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawUpdateDir(String key, long ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdRawResponse rawWatch(String prefix, long waitIndex, boolean recursive, WatchListener listener) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }
    
    
}