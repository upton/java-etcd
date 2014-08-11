package net.lexuan.etcd;

public interface Etcd {
    EtcdResponse set(String key) throws EtcdException;

    EtcdResponse set(String key, String value) throws EtcdException;

    EtcdResponse set(String key, String value, int ttl) throws EtcdException;

    EtcdResponse set(String key, String value, int ttl, boolean isDir) throws EtcdException;

    EtcdResponse set(String key, String value, int ttl, boolean isDir, String prevValue) throws EtcdException;

    EtcdResponse ttl(String key, int ttl) throws EtcdException;

    EtcdResponse ttl(String key, int ttl, boolean isDir) throws EtcdException;

    EtcdResponse get(String key) throws EtcdException;

    EtcdResponse watch(String key, WatchListener listener) throws EtcdException;

    EtcdResponse watch(String key, long waitIndex, WatchListener listener) throws EtcdException;

    EtcdResponse delete(String key, boolean isDir) throws EtcdException;

    EtcdResponse compareAndSwap(String key, String value, String prevValue, long prevIndex) throws EtcdException;

    EtcdResponse compareAndSwap(String key, String value, boolean prevExist) throws EtcdException;
    
    EtcdResponse mkdir(String key) throws EtcdException;
    
    EtcdResponse ls(String key, boolean recursive) throws EtcdException;
}
