package com.example.fuzz.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/2 16:26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Goods {
    private String title;
    private String img;
    private String price;
}
