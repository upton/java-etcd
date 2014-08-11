package net.lexuan.etcd;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.config.RequestConfig;
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

    // only support v2 version
    private final static String VERSION = "v2";

    private long dialTimeout = 3000L;

    private CloseableHttpAsyncClient httpClient;
    private String[] machines;

    public EtcdClient(String[] machines) {
        if (machines == null || machines.length <= 0) {
            throw new RuntimeException("machines must not null && with less one machine string.");
        }

        this.machines = machines;
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
     * @param etcdRequest
     * @param listener
     * @return
     * @throws EtcdException
     */
    public EtcdResponse cancelableGet(EtcdRequest etcdRequest, WatchListener listener) throws EtcdException {
        HttpUriRequest request = buildUriRequest(etcdRequest);

        listener.setRequest(request);
        return syncExecute(request);
    }

    /**
     * async resquest the etcd with future return
     * 
     * @param request
     * @return
     * @throws EtcdException
     */
    private ListenableFuture<EtcdResponse> asyncExecute(HttpUriRequest request) throws EtcdException {
        ListenableFuture<HttpResponse> httpResponse = asyncExecuteHttp(request);
        return Futures.transform(httpResponse, new AsyncFunction<HttpResponse, EtcdResponse>() {
            public ListenableFuture<EtcdResponse> apply(HttpResponse httpResponse) throws Exception {
                EtcdResponse result = readHttpResponse(httpResponse);
                return Futures.immediateFuture(result);
            }
        });
    }

    /**
     * sync request the etcd with response return
     * 
     * @param request
     * @return
     * @throws EtcdException
     */
    private EtcdResponse syncExecute(HttpUriRequest request) throws EtcdException {
        try {
            return asyncExecute(request).get();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new EtcdException("Interrupted during request", e);
        } catch (ExecutionException e) {
            throw new EtcdException("ExecutionException during request", e);
        }
    }

    /**
     * async to request the etcd
     * 
     * @param request
     * @return the ListenableFuture
     */
    private ListenableFuture<HttpResponse> asyncExecuteHttp(HttpUriRequest request) {
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

        return future;
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

    private HttpUriRequest buildUriRequest(EtcdRequest etcdRequest) {
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