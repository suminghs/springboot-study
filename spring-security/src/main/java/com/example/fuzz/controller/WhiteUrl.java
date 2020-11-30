package com.example.fuzz.controller;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/30 14:56
 */
@Component
@ConfigurationProperties(prefix = "whitelist")
@Data
public class WhiteUrl {

    private List<String> urlList;

}
