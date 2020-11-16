package com.example.fuzz.service;

import com.example.fuzz.util.HttpUtils;
import okhttp3.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/13 14:36
 */
@Service
public class OauthService {
    @Autowired
    private HttpUtils httpUtils;

    public String getClientToken() throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("grant_type", "client_credentials");
        map.put("client_id", "3");
        map.put("client_secret", "hCWoAF4LW1fLJxbddfs7NhPLB4ZmwlOetfDaGhQW");
        map.put("scope", "");

        Response response = httpUtils.postMap("http://hypernew.cn/oauth/token", map);
        System.out.println(response.body().string());
        return "aaa";
    }

    public String getOauth2Token(String code) throws IOException {
        Map<String, Object> map = new HashMap<>();
        map.put("grant_type", "authorization_code");
        map.put("client_id", 3);
        map.put("client_secret", "Yc0z56EGOoLuPltiU0G5sJtTyOy7rOTGUGg5Nt4L");
        map.put("redirect_uri", "http://localhost:8080/callback");
        map.put("code", code);

        Response response = httpUtils.postMap("http://localhost/Laravel/test/public/index.php/oauth/token", map);
        String result = response.body().string();
        System.out.println(result);
        return result;
    }

}
