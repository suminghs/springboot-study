package com.example.fuzz.service;

import com.alibaba.fastjson.JSON;
import com.example.fuzz.constant.ShopConstant;
import com.example.fuzz.pojo.Goods;
import com.example.fuzz.util.HtmlParseUtil;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/2 16:29
 */
@Service
public class GoodsService {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public boolean parseData(String keywords) throws Exception {
        List<Goods> goodsList = HtmlParseUtil.parseJD(keywords);
        BulkRequest request = new BulkRequest();
        goodsList.forEach(goods -> {
            request.add(new IndexRequest(ShopConstant.GOODS).source(JSON.toJSONString(goods), XContentType.JSON));
        });
        BulkResponse bulk = restHighLevelClient.bulk(request, RequestOptions.DEFAULT);
        return !bulk.hasFailures();
    }

    public List<Goods> searchByKeywords(String keywords, Integer page) {
        List<Goods> goodsList = new ArrayList<Goods>();
        int size = 15;
        if (page == null || page < 1) {
            page = 1;
        }
        int from = (page - 1) * 10;
        try {
            SearchRequest request = new SearchRequest();
            request.source(new SearchSourceBuilder()
                    .query(QueryBuilders.matchQuery("title", keywords))
                    .highlighter(new HighlightBuilder()
                                    .field("title")
                                    .preTags("<span style='color:red'>")
                                    .postTags("</span>")).from(from).size(size));
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

            for (SearchHit hit : response.getHits().getHits()) {
                Map<String, Object> map = hit.getSourceAsMap();
                Goods goods = new Goods(map.get("title").toString(), map.get("img").toString(), map.get("price").toString());

                Map<String, HighlightField> fields = hit.getHighlightFields();
                HighlightField title = fields.get("title");

                if (title != null) {
                    Text[] fragments = title.fragments();
                    String remark = "";
                    for (Text fragment : fragments) {
                        remark += fragment;
                    }
                    goods.setTitle(remark);
                }

                goodsList.add(goods);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return goodsList;
    }

}
