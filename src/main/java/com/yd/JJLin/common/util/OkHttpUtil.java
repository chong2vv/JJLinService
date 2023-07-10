package com.yd.JJLin.common.util;
import okhttp3.*;

import java.util.Map;
import java.util.concurrent.TimeUnit;


public class OkHttpUtil {
    private static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");
    private static OkHttpClient client;

    static {
        client = new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .build();
    }

    public static void get(String url, Callback callback) {
        Request request = new Request.Builder()
                .url(url)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void post(String url, String json, Callback callback) {
        String mediaTypeString = "application/json; charset=utf-8";
        RequestBody requestBody = RequestBody.create(mediaTypeString, MediaType.parse(json));
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }

    public static void post(String url, Map<String, String> params, Callback callback) {
        FormBody.Builder formBuilder = new FormBody.Builder();
        for (String key : params.keySet()) {
            formBuilder.add(key, params.get(key));
        }
        RequestBody requestBody = formBuilder.build();
        Request request = new Request.Builder()
                .url(url)
                .post(requestBody)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
