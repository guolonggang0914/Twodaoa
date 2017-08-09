package com.bway.two.model.net.okhttp;


import android.util.Log;

import com.bway.two.utils.http.IHttpEngien;
import com.bway.two.view.IMview.customcallback.CallBack;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * OKHttp网络请求
 */

public class OkhttpEnginen implements IHttpEngien {
    private OkHttpClient client;

    public OkhttpEnginen() {
        client = new OkHttpClient();
    }

    @Override
    public void get(String url, Map<String, Object> params, final CallBack callBack) {
        StringBuffer sb = new StringBuffer();
        if (params != null) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                sb.append("&")
                        .append(entry.getKey())
                        .append("=")
                        .append(entry.getValue());
            }
            if (!url.contains("?")) {
                sb.replace(0, 1, "?");//把&替换成？
            } else if (url.endsWith("?")) {
                sb.deleteCharAt(0);// 把第一个删除
            }
            url = url + sb.toString();
            Request request = new Request.Builder()
                    .url(url)
                    .get()
                    .build();
            Call call = client.newCall(request);
            call.enqueue(new Callback() {
                @Override
                public void onFailure(Call call, final IOException e) {
                    HttpManager.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            callBack.onFailure(e.getMessage(), 0);
                        }
                    });
                }

                @Override
                public void onResponse(Call call, final Response response) throws IOException {
                    final String result = response.body().string();
                    HttpManager.handler.post(new Runnable() {
                        @Override
                        public void run() {
                            Gson gson = new Gson();
                            Class<?> clazz = GenericUtil.getSuperGenericClass(callBack.getClass());
                            callBack.onSuccess(gson.fromJson(result, clazz));
                        }
                    });
                }
            });
        }


    }

    @Override
    public void post(String url, Map<String, Object> params, final CallBack callBack) {

        FormBody.Builder body = new FormBody.Builder();

        if (null != params) {
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                body.add(entry.getKey(), (String) entry.getValue());
                Log.e("body", "post: " + entry.getKey() + "/" + entry.getValue());
            }
        }

        Request request = new Request.Builder()
                .url(url)
                .post(body.build())
                .build();

        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                HttpManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        callBack.onFailure(e.getMessage(), 0);
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                final String result = response.body().string();
                HttpManager.handler.post(new Runnable() {
                    @Override
                    public void run() {
                        Gson gson = new Gson();
                        Class<?> clazz = GenericUtil.getSuperGenericClass(callBack.getClass());
                        callBack.onSuccess(gson.fromJson(result, clazz));
                        //
                    }
                });
            }
        });
    }
}
