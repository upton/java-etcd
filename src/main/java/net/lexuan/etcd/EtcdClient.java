package net.lexuan.etcd;

import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ExecutionException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.concurrent.FutureCallback;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.util.concurrent.AsyncFunction;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.SettableFuture;

public class EtcdClient {
    private final static Logger logger = LoggerFactory.getLogger(EtcdClient.class);

    private final static Random RD = new Random();

    // only support v2 version
    private final static String VERSION = "v2";

    private long dialTimeout = 3000L;

    private CloseableHttpAsyncClient httpClient;
    private Cluster cluster;

    public EtcdClient(String[] machines) {
        if (machines == null || machines.length <= 0) {
            throw new RuntimeException("machines must not null && with less one machine string.");
        }

        cluster = new Cluster(machines);

        RequestConfig requestConfig = RequestConfig.custom().build();
        httpClient = HttpAsyncClients.custom().setDefaultRequestConfig(requestConfig).build();
    }

    public EtcdClient setDialTimeout(long timeout) {
        dialTimeout = timeout;
        return this;
    }

    public EtcdResponse get(EtcdRequest etcdRequest) {
        return null;
    }

    public EtcdResponse put(EtcdRequest etcdRequest) {
        return null;
    }

    public EtcdResponse post(EtcdRequest etcdRequest) {
        return null;
    }

    public EtcdResponse delete(EtcdRequest etcdRequest) {
        return null;
    }

    /**
     * cancelable get request for watch operation
     * 
     * @param etcdRequest
     * @param listener
     * @return
     * @throws EtcdException
     */
    public EtcdResponse cancelableGet(EtcdRequest etcdRequest, WatchListener listener) throws EtcdException {
        HttpGet request = new HttpGet(getHttpPath(etcdRequest));

        listener.setRequest(request);
        return sendRequest(request);
    }

    private String getHttpPath(boolean random, String path) {
        String machine;

        if (random) {
            machine = cluster.getMachines()[RD.nextInt(cluster.getMachines().length)];
        } else {
            machine = cluster.getLeader();
        }

        String fullPath = machine + "/" + VERSION + path;

        return fullPath;
    }

    /**
     * Send request to etcd. Use the HttpAsyncClient
     * 
     * @param request
     * @return EtcdResponse
     * @throws EtcdException
     */
    private EtcdResponse sendRequest(HttpUriRequest request) throws EtcdException {
        try {
            final SettableFuture<HttpResponse> future = SettableFuture.create();

            httpClient.execute(request, new FutureCallback<HttpResponse>() {
                public void completed(HttpResponse result) {
                    future.set(result);
                }

                public void failed(Exception ex) {
                    future.setException(ex);
                }

                public void cancelled() {
                    future.setException(new InterruptedException());
                }
            });

            return Futures.transform(future, new AsyncFunction<HttpResponse, EtcdResponse>() {
                public ListenableFuture<EtcdResponse> apply(HttpResponse httpResponse) throws Exception {
                    EtcdResponse result = readHttpResponse(httpResponse);
                    return Futures.immediateFuture(result);
                }
            }).get();

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EtcdException("Interrupted during request", e);
        } catch (ExecutionException e) {
            throw new EtcdException("ExecutionException during request", e);
        }
    }

    /**
     * read the HTTP response entity & parse the entity to EtcdResponse
     * 
     * @param httpResponse
     * @return
     * @throws EtcdException
     */
    private EtcdResponse readHttpResponse(HttpResponse httpResponse) throws EtcdException {
        try {
            StatusLine statusLine = httpResponse.getStatusLine();
            int statusCode = statusLine.getStatusCode();

            String json = null;

            if (statusCode == 200) {
                if (httpResponse.getEntity() != null) {
                    try {
                        json = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
                    } catch (IOException e) {
                        throw new EtcdException("Error reading response string", e);
                    }
                }
            } else {
                throw new EtcdException("Error response from etcd: " + statusLine.getReasonPhrase(), statusCode);
            }

            return parseJson(json, statusCode);
        } finally {
            close(httpResponse);
        }
    }

    /**
     * parse the etcd response json string to EtcdResponse object
     * 
     * @param json
     * @param statusCode
     * @return
     */
    private EtcdResponse parseJson(String json, int statusCode) {
        return null;
    }

    /**
     * close the http response
     * 
     * @param response
     */
    public void close(HttpResponse response) {
        if (response == null) {
            return;
        }
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            EntityUtils.consumeQuietly(entity);
        }
    }
}