package com.example.fuzz.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import okhttp3.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/13 14:45
 */
@Component
public class HttpUtils {
    public static final MediaType JSON_TYPE = MediaType.parse("application/json; charset=utf-8");

    public Response get(String url) throws IOException {
        OkHttpClient client = new OkHttpClient();
        return client.newCall(new Request.Builder()
                .url(url)
                .get()
                .build())
                .execute();
    }


    public Response postForm(String url, Object o) throws IOException {
        Map map = JSON.parseObject(JSONObject.toJSONString(o));
        return postMap(url, map);
    }

    public Response postMap(String url, Map map) throws IOException {
        OkHttpClient client = new OkHttpClient();

        FormBody.Builder body = new FormBody.Builder();
        map.forEach((key, value) -> {
            body.add(key.toString(), value.toString());
        });
        return client.newCall(new Request.Builder()
                .post(body.build())
                .url(url)
                .build())
                .execute();
    }

    public Response postJson(String url, String json) throws IOException {
        OkHttpClient client = new OkHttpClient();
        return client.newCall(new Request.Builder()
                .post(RequestBody.create(JSON_TYPE, json))
                .url(url)
                .build())
                .execute();
    }


}
