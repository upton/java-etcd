package net.lexuan.etcd;

import java.net.URI;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EtcdClient {
    private final static Logger logger = LoggerFactory.getLogger(EtcdClient.class);
    
    private CloseableHttpAsyncClient httpClient;
    private URI baseUri;

    public EtcdClient(String baseUrl) {
        if (baseUrl == null) {
            throw new RuntimeException("url must not null.");
        }

        if (!baseUrl.endsWith("/")) {
            baseUrl += "/";
        }

        this.baseUri = URI.create(baseUrl);

        RequestConfig requestConfig = RequestConfig.custom().build();
        CloseableHttpAsyncClient httpClient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
        httpClient.start();
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