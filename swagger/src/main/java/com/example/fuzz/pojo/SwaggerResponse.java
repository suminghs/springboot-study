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
@ApiModel(value="响应对象", description="")
public class SwaggerResponse {
    @ApiModelProperty("返回参数1")
    private int param1;
    @ApiModelProperty("返回参数2")
    private String param2;
}
