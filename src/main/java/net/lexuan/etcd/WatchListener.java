package net.lexuan.etcd;

public interface WatchListener {
    void onChange(EtcdResponse result);
    void onRawChange(EtcdRawResponse result);
}