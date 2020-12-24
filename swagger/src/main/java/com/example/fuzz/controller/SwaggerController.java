package com.example.fuzz.controller;

import com.example.fuzz.pojo.SwaggerRequest;
import com.example.fuzz.pojo.SwaggerResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/24 14:19
 */
@RestController
@Api(tags = "演示")
public class SwaggerController {

    @GetMapping("/testGet")
    @ApiOperation("GET请求")
    public String testGet(@ApiParam("get参数") String mobile) {
        return mobile;
    }

    @GetMapping("/testPost")
    @ApiOperation(value = "POST请求", response = SwaggerResponse.class)
    public SwaggerResponse postS(@RequestBody SwaggerRequest swaggerRequest) {
        SwaggerResponse swaggerResponse = new SwaggerResponse();
        BeanUtils.copyProperties(swaggerRequest, swaggerResponse);
        return swaggerResponse;
    }


}
