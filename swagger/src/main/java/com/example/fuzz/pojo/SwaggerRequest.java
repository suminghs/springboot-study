package com.example.fuzz.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/12/24 14:27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "请求对象", description = "")
public class SwaggerRequest {
    @ApiModelProperty("请求参数1")
    private int param1;
    @ApiModelProperty("请求参数2")
    private String param2;
}
