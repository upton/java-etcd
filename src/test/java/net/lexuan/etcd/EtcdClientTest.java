package net.lexuan.etcd;

import org.junit.Before;

public class EtcdClientTest {
    EtcdClient client;

    @Before
    public void init() {
        client = new EtcdClient(new String[] { "127.0.0.1:4001", "127.0.0.1:4002", "127.0.0.1:4003" });
    }

    

}