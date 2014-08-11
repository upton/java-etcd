package net.lexuan.etcd;

public interface Etcd {
    EtcdResponse addChild(String key, String value, long ttl) throws EtcdException;

    EtcdResponse addChildDir(String key, long ttl) throws EtcdException;

    EtcdResponse compareAndDelete(String key, String prevValue, long prevIndex) throws EtcdException;

    EtcdResponse compareAndSwap(String key, String value, long ttl, String prevValue, long prevIndex) throws EtcdException;

    EtcdResponse create(String key, String value, long ttl) throws EtcdException;

    EtcdResponse createDir(String key, long ttl) throws EtcdException;

    EtcdResponse createInOrder(String dir, String value, long ttl) throws EtcdException;

    EtcdResponse delete(String key, boolean recursive) throws EtcdException;

    EtcdResponse deleteDir(String key) throws EtcdException;

    EtcdResponse get(String key, boolean sort, boolean recursive) throws EtcdException;

    EtcdResponse set(String key, String value, long ttl) throws EtcdException;

    EtcdResponse setConsistency(String consistency) throws EtcdException;

    EtcdResponse setDir(String key, long ttl) throws EtcdException;

    EtcdResponse update(String key, String value, long ttl) throws EtcdException;

    EtcdResponse updateDir(String key, long ttl) throws EtcdException;

    EtcdResponse watch(String prefix, long waitIndex, boolean recursive, WatchListener listener) throws EtcdException;

    String[] getCluster();

    EtcdRawResponse rawCompareAndDelete(String key, String prevValue, long prevIndex) throws EtcdException;

    EtcdRawResponse rawCompareAndSwap(String key, String value, long ttl, String prevValue, long prevIndex) throws EtcdException;

    EtcdRawResponse rawCreate(String key, String value, long ttl) throws EtcdException;

    EtcdRawResponse rawCreateDir(String key, long ttl) throws EtcdException;

    EtcdRawResponse rawCreateInOrder(String dir, String value, long ttl) throws EtcdException;

    EtcdRawResponse rawDelete(String key, boolean recursive, boolean dir) throws EtcdException;

    EtcdRawResponse rawGet(String key, boolean sort, boolean recursive) throws EtcdException;

    EtcdRawResponse rawSet(String key, String value, long ttl) throws EtcdException;

    EtcdRawResponse rawSetDir(String key, long ttl) throws EtcdException;

    EtcdRawResponse rawUpdate(String key, String value, long ttl) throws EtcdException;

    EtcdRawResponse rawUpdateDir(String key, long ttl) throws EtcdException;

    EtcdRawResponse rawWatch(String prefix, long waitIndex, boolean recursive, WatchListener listener) throws EtcdException;
}