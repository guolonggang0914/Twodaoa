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
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by luccc
 * Create Time: 2017/7/19
 * Description:
 */

public class OkhttpEnginen implements IHttpEngien {
    private OkHttpClient client;
    private String token;
    private Request request;

    public OkhttpEnginen () {
        client = new OkHttpClient();
    }

    @Override
    public void get(String url, Map<String, Object> params, final CallBack callBack) {
        StringBuffer sb = new StringBuffer();
        if(params!=null) {
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
        }
            url = url+sb.toString();
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

    @Override
    public void post(String url, Map<String, Object> params, final CallBack callBack) {
        RequestBody requestBody = null;

        FormBody.Builder builder = new FormBody.Builder();
        for (Map.Entry<String, Object> entry : params.entrySet()) {
            String key = entry.getKey().toString();
            String value = null;
            if (entry.getValue() == null) {
                value = "";
            } else {
                value = entry.getValue().toString();
            }
            Log.e("=====", "getDataPostFromServer: " + key + " ," + value);
            if("token".equals(key))
            {
                key=token;
            }
            else
            {
                builder.add(key, value);
            }


        }
        requestBody = builder.build();



//        if (null != params) {
//            for (Map.Entry<String, Object> entry:params.entrySet()) {
//                body.add(entry.getKey(), (String) entry.getValue());
//                Log.e("body", "post: "+entry.getKey()+"/"+(String) entry.getValue() );
//            }
//        }
        Request request=null;
        if(token==null)
        {
          request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
        }
        else
        {
            request = new Request.Builder()
                    .url(url)
                    .addHeader("token",token)
                    .post(requestBody)
                    .build();
        }



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

                    }
                });
            }
        });
    }
}
