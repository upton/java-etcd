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
    public EtcdResponse set(String key) throws EtcdException {
        String result = client.put(V2 + key);
        return JSON.parseObject(result, EtcdResponse.class);
    }

    @Override
    public EtcdResponse set(String key, String value) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse set(String key, String value, int ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse set(String key, String value, int ttl, boolean isDir) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse set(String key, String value, int ttl, boolean isDir, String prevValue) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse ttl(String key, int ttl) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse ttl(String key, int ttl, boolean isDir) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse get(String key) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse watch(String key, WatchListener listener) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse watch(String key, long waitIndex, WatchListener listener) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse delete(String key, boolean isDir) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse compareAndSwap(String key, String value, String prevValue, long prevIndex) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse compareAndSwap(String key, String value, boolean prevExist) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse mkdir(String key) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public EtcdResponse ls(String key, boolean recursive) throws EtcdException {
        // TODO Auto-generated method stub
        return null;
    }
}