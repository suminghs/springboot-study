package com.example.fuzz.controller;

import com.example.fuzz.service.OauthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/13 14:35
 */
@RestController
public class IndexController {
    @Autowired
    private OauthService oauthService;

    @GetMapping("/token")
    public String getClientToken() throws IOException {
        return oauthService.getClientToken();
    }

    @GetMapping("/callback")
    public String callback(String code) throws IOException {
        System.out.println("===============callback===============");
        return oauthService.getOauth2Token(code);
    }


}
