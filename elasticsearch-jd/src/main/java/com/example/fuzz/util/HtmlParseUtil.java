package com.example.fuzz.util;

import com.example.fuzz.pojo.Goods;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/2 16:45
 */
public class HtmlParseUtil {

    public static List<Goods> parseJD(String keywords) throws Exception {
        String url = "https://search.jd.com/Search?keyword=" + keywords + "&enc=utf-8";
        Document document = Jsoup.parse(new URL(url), 30000);
//        System.out.println(document.html());

        List<Goods> goodsList = new ArrayList<Goods>();
        Element element = document.getElementById("J_goodsList");
        Elements elements = element.getElementsByTag("li");
        for (Element el : elements) {
            String title = el.getElementsByClass("p-name").first().text();
            String img = el.getElementsByTag("img").first().attr("data-lazy-img");
            String price = el.getElementsByClass("p-price").first().getElementsByTag("i").first().text();
            Goods goods = new Goods(title, img, price);
            goodsList.add(goods);
            System.out.println(goods);
        }

        return goodsList;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(parseJD("java"));
    }

}
