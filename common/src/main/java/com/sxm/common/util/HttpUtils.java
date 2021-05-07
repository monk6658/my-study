package com.sxm.common.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpRequest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLHandshakeException;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.net.UnknownHostException;

/**
 * http 工具类
 * @author zxl
 * @date 2021/4/9 15:31
 */
public class HttpUtils {

    /***  utf-8字符编码 */
    private static final String CHARSET_UTF_8 = "utf-8";

    /*** HTTP内容类型 */
    private static final String CONTENT_TYPE_TEXT_HTML = "text/xml";

    /*** HTTP内容类型。相当于form表单的形式，提交数据*/
    private static final String CONTENT_TYPE_FORM_URL = "application/x-www-form-urlencoded";

    /*** HTTP内容类型。相当于form表单的形式，提交数据*/
    private static final String CONTENT_TYPE_JSON_URL = "application/json;charset=utf-8";

    /*** 连接管理器 */
    private static PoolingHttpClientConnectionManager pool;
    /*** 请求配置 */
    private static RequestConfig requestConfig;
    static {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            SSLConnectionSocketFactory self = new SSLConnectionSocketFactory(builder.build());
            // 配置同时支持 HTTP 和 HTTPS
            Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("http", PlainConnectionSocketFactory.getSocketFactory())
                    .register("https", self).build();
            // 初始化连接管理器
            pool = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
            // 将最大连接数增加到200，实际项目最好从配置文件中读取这个值
            pool.setMaxTotal(200);
            // 设置最大路由
            pool.setDefaultMaxPerRoute(2);
            // 根据默认超时限制初始化requestConfig
            int socketTimeout = 10000;
            int connectTimeout = 10000;
            int connectionRequestTimeout = 10000;
            requestConfig = RequestConfig.custom()
                    .setConnectionRequestTimeout(connectionRequestTimeout)
                    .setSocketTimeout(socketTimeout)
                    .setConnectTimeout(connectTimeout)
                    .build();
        }catch (Exception e) {
            e.printStackTrace();
        }
        // 设置请求超时时间
        requestConfig = RequestConfig.custom().setSocketTimeout(500000).setConnectTimeout(500000).setConnectionRequestTimeout(500000).build();
    }

    /**
     * 获取重试控制器
     * @return 重试控制器
     */
    private static HttpRequestRetryHandler getRetryHandler(){
        // 请求重试处理
        return (exception, executionCount, context) -> {
            if (executionCount >= 5) {// 如果已经重试了5次，就放弃
                return false;
            }
            if (exception instanceof NoHttpResponseException) {// 如果服务器丢掉了连接，那么就重试
                return true;
            }
            if (exception instanceof SSLHandshakeException) {// 不要重试SSL握手异常
                return false;
            }
            if (exception instanceof InterruptedIOException) {// 超时
                return false;
            }
            if (exception instanceof UnknownHostException) {// 目标服务器不可达
                return false;
            }
            if (exception instanceof SSLException) {// SSL握手异常
                return false;
            }
            HttpClientContext clientContext = HttpClientContext.adapt(context);
            HttpRequest request = clientContext.getRequest();
            // 如果请求是幂等的，就再次尝试
            return !(request instanceof HttpEntityEnclosingRequest);
        };
    }

    /**
     * 获取http client 对象
     * @return CloseableHttpClient
     */
    private static CloseableHttpClient getHttpClient() {
        return HttpClients.custom()
                // 设置连接池管理
                .setConnectionManager(pool)
                // 设置请求配置
                .setDefaultRequestConfig(requestConfig)
                // 设置重试次数
                .setRetryHandler(getRetryHandler())
                .build();
    }

    /**
     * 发送请求
     * @param httpUrl 地址
     * @param params  组装好参数
     * @return 响应结果
     */
    public static String sendHttpPostForm(String httpUrl, String params) {
        HttpPost httpPost = new HttpPost(httpUrl);
        if (params != null && params.trim().length() > 0) {
            StringEntity stringEntity = new StringEntity(params, CHARSET_UTF_8);
            stringEntity.setContentType(CONTENT_TYPE_FORM_URL);
            httpPost.setEntity(stringEntity);
        }
        return sendHttp(httpPost);
    }

    /**
     * 以问号传参的方式，发送get请求
     * @param httpUrl 请求地址
     * @param params 请求参数
     * @return 响应结果
     */
    public static String sendHttpGet(String httpUrl, String params) {
        return sendHttpGet(httpUrl + "?" + params);
    }

    /**
     * http get 请求
     * @param httpUrl 请求地址
     * @return 请求结果
     */
    public static String sendHttpGet(String httpUrl){
        HttpGet httpGet = new HttpGet(httpUrl);
        return sendHttp(httpGet);
    }

    /**
     * 发送post请求
     * @param httpRequestBase post、get请求体
     * @return 响应结果
     */
    private static String sendHttp(HttpRequestBase httpRequestBase) {
        CloseableHttpClient httpClient = null;
        CloseableHttpResponse response = null;
        try {
            String rs = null;
            httpClient = getHttpClient();
            response = httpClient.execute(httpRequestBase);
            HttpEntity entity = response.getEntity();
            rs = EntityUtils.toString(entity, CHARSET_UTF_8);
            //手动关闭流连接
            EntityUtils.consume(entity);
            return rs;
        }catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if (response != null) {
                    response.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }







}
