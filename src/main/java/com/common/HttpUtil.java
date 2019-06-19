package com.common;

import org.asynchttpclient.*;
import org.asynchttpclient.request.body.multipart.Part;

import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

/**
 * Created by frank_zhao on 2017/8/23.
 *
 * @apiNote AsyncHttp 相关操作
 */
public class HttpUtil {

    private static final AsyncHttpClient asyncHttpClient = new DefaultAsyncHttpClient();


    /**
     * @param uri     http://******
     * @param headers
     * @param timeout sec
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @apiNote http get request
     */
    public static String httpGet(String uri, Map<String, String> headers, long timeout) throws InterruptedException,
            ExecutionException, TimeoutException {
        return httpGetV2(uri, headers, timeout).getResponseBody();
    }


    /**
     * @param url     http://******
     * @param params  params
     * @param headers
     * @param timeout sec
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @apiNote http get request return rsp
     */
    public static Response httpGet(String url, List<Param> params, Map<String, String> headers, long timeout) throws
            InterruptedException,
            ExecutionException, TimeoutException {
        return asyncHttpClient.prepareGet(url)
                .setFormParams(params)
                .setSingleHeaders(headers)
                .execute().get(timeout, TimeUnit.SECONDS);
    }


    /**
     * @param uri
     * @param headers
     * @param completionHandler back handler
     */
    public static void httpGet(String uri, Map<String, String> headers, AsyncCompletionHandler completionHandler) {
        asyncHttpClient.prepareGet(uri)
                .setSingleHeaders(headers).execute(completionHandler);
    }


    /**
     * @param uri
     * @param headers
     * @return future
     */
    public static Future httpGet(String uri, Map<String, String> headers) {
        return asyncHttpClient.prepareGet(uri)
                .setSingleHeaders(headers)
                .execute();
    }

    /**
     * @param uri     http://******
     * @param headers
     * @param timeout sec
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @apiNote http get request
     */
    public static Response httpGetV2(String uri, Map<String, String> headers, long timeout) throws InterruptedException,
            ExecutionException, TimeoutException {
        return asyncHttpClient.prepareGet(uri)
                .setSingleHeaders(headers)
                .execute().get(timeout, TimeUnit.SECONDS);
    }


    /**
     * @param url     http://******
     * @param body
     * @param headers
     * @param timeout sec
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @apiNote http post request
     */
    public static String httpPost(String url, String body, Map<String, String> headers, long timeout) throws InterruptedException,
            ExecutionException, TimeoutException {
        return httpPostV2(url, body, headers, timeout).getResponseBody();
    }


    /**
     * @param url
     * @param body
     * @param headers
     * @param completionHandler back handler
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static void httpPost(String url, String body, Map<String, String> headers,
                                AsyncCompletionHandler completionHandler) throws InterruptedException, ExecutionException, TimeoutException {
        asyncHttpClient.preparePost(url)
                .setBody(body)
                .setSingleHeaders(headers)
                .execute(completionHandler);
    }


    /**
     * @param url
     * @param body
     * @param headers
     * @return Future
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     */
    public static Future httpPost(String url, String body, Map<String, String> headers) throws InterruptedException,
            ExecutionException, TimeoutException {
        return asyncHttpClient.preparePost(url)
                .setBody(body)
                .setSingleHeaders(headers)
                .execute();
    }


    /**
     * @param url     http://******
     * @param body
     * @param headers
     * @param timeout sec
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @apiNote http post request
     */
    public static Response httpPostV2(String url, String body, Map<String, String> headers, long timeout) throws InterruptedException,
            ExecutionException, TimeoutException {
        return asyncHttpClient.preparePost(url)
                .setBody(body)
                .setSingleHeaders(headers)
                .execute().get(timeout, TimeUnit.SECONDS);
    }

    /**
     * @param url     http://******
     * @param body
     * @param headers
     * @param timeout sec
     * @return
     * @throws InterruptedException
     * @throws ExecutionException
     * @throws TimeoutException
     * @apiNote http post request
     */
    public static Response httpPostV3(String url, List<Part> body, Map<String, String> headers, long timeout) throws InterruptedException,
            ExecutionException, TimeoutException {
        return asyncHttpClient.preparePost(url)
                .setBodyParts(body)
                .setSingleHeaders(headers)
                .execute().get(timeout, TimeUnit.SECONDS);
    }


}
