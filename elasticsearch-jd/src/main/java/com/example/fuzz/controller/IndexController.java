package com.example.fuzz.controller;

import com.example.fuzz.pojo.Goods;
import com.example.fuzz.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/2 16:39
 */
@Controller
public class IndexController {

    @Autowired
    private GoodsService goodsService;

    @GetMapping("/parseData/{keywords}")
    @ResponseBody
    public boolean parse(@PathVariable("keywords") String keywords) throws Exception {
        return goodsService.parseData(keywords);
    }

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @ResponseBody
    @GetMapping("/search/{keywords}")
    public List<Goods> selectGoods(@PathVariable("keywords") String keywords) {
        return goodsService.searchByKeywords(keywords);
    }


}
