package com.example.fuzz.util;

import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.example.fuzz.common.ApiResponse;
import com.example.fuzz.common.Status;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * Response 通用工具类
 * </p>
 *
 * @package: com.xkcoding.rbac.security.util
 * @description: Response 通用工具类
 * @author: yangkai.shen
 * @date: Created in 2018-12-07 17:37
 * @copyright: Copyright (c) 2018
 * @version: V1.0
 * @modified: yangkai.shen
 */
@Slf4j
public class ResponseUtil {

    /**
     * 往 response 写出 json
     *
     * @param response 响应
     * @param status   状态
     * @param data     返回数据
     */
    public static void renderJson(HttpServletResponse response, Status status, Object data) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);

            // FIXME: hutool 的 BUG：JSONUtil.toJsonStr()
            //  将JSON转为String的时候，忽略null值的时候转成的String存在错误
            response.getWriter()
                    .write(JSONUtil.toJsonStr(new JSONObject(ApiResponse.ofStatus(status, data), false)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }

    /**
     * 往 response 写出 json
     *
     * @param response  响应
     * @param exception 异常
     */
    public static void renderJson(HttpServletResponse response, String msg) {
        try {
            response.setHeader("Access-Control-Allow-Origin", "*");
            response.setHeader("Access-Control-Allow-Methods", "*");
            response.setContentType("application/json;charset=UTF-8");
            response.setStatus(200);

            // FIXME: hutool 的 BUG：JSONUtil.toJsonStr()
            //  将JSON转为String的时候，忽略null值的时候转成的String存在错误
            response.getWriter()
                    .write(JSONUtil.toJsonStr(new JSONObject(ApiResponse.ofError(msg), false)));
        } catch (IOException e) {
            log.error("Response写出JSON异常，", e);
        }
    }
}