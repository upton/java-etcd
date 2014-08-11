package net.lexuan.etcd;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtcdClient {
    private final static Logger logger = LoggerFactory.getLogger(EtcdClient.class);
    private long dialTimeout = 3000;
    
    private CloseableHttpAsyncClient httpClient;
    private String[] machines;

    public EtcdClient(String[] machines) {
        if (machines == null || machines.length <= 0) {
            throw new RuntimeException("machines must not null && with less one machine string.");
        }

        this.machines = machines;
        RequestConfig requestConfig = RequestConfig.custom().build();
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    public EtcdClient setDialTimeout(long timeout){
        dialTimeout = timeout;
        return this;
    }
    
    public String get(String url) {
        return null;
    }

    public String put(String url) {
        return null;
    }

    public String post(String url) {
        return null;
    }

    public String delete(String url) {
        return null;
    }
}