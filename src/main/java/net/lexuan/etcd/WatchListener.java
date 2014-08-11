package net.lexuan.etcd;

public interface WatchListener {
    public void onChange(EtcdResponse result);
}
