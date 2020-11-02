package com.example.fuzz;

import com.alibaba.fastjson.JSON;
import com.example.fuzz.constant.IndexConstant;
import com.example.fuzz.pojo.User;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2020/11/2 14:18
 */
@SpringBootTest
public class TestSearch {
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    @Test
    void testIndex() throws IOException {
        CreateIndexRequest createIndexRequest = new CreateIndexRequest(IndexConstant.INDEX);
        CreateIndexResponse createIndexResponse = restHighLevelClient.indices().create(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(createIndexResponse);
    }

    @Test
    void testIndexExists() throws IOException {
        GetIndexRequest createIndexRequest = new GetIndexRequest(IndexConstant.INDEX);
        boolean re = restHighLevelClient.indices().exists(createIndexRequest, RequestOptions.DEFAULT);
        System.out.println(re);
    }

    @Test
    void testAddDocument() throws IOException {
        User user = new User("张三", 18);
        IndexRequest request = new IndexRequest(IndexConstant.INDEX);
        request.id("1");
        request.source(JSON.toJSONString(user), XContentType.JSON);
        IndexResponse response = restHighLevelClient.index(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
        System.out.println(response.status());
    }

    @Test
    void testExitsDocument() throws IOException {
        GetRequest getRequest = new GetRequest(IndexConstant.INDEX, "1");
        boolean re = restHighLevelClient.exists(getRequest, RequestOptions.DEFAULT);
        System.out.println(re);
    }

    @Test
    void testGetDocument() throws IOException {
        GetRequest getRequest = new GetRequest(IndexConstant.INDEX, "1");
        GetResponse response = restHighLevelClient.get(getRequest, RequestOptions.DEFAULT);
        System.out.println(response.getSourceAsString());
        System.out.println(response);
    }

    @Test
    void testUpdateDocument() throws IOException {
        User user = new User("张三2", 18);
        UpdateRequest request = new UpdateRequest(IndexConstant.INDEX, "1");
        request.doc(JSON.toJSONString(user), XContentType.JSON);
        UpdateResponse response = restHighLevelClient.update(request, RequestOptions.DEFAULT);
        System.out.println(response.toString());
    }

    @Test
    void testDeleteDocument() throws IOException {
        DeleteRequest request = new DeleteRequest(IndexConstant.INDEX, "1");
        DeleteResponse response = restHighLevelClient.delete(request, RequestOptions.DEFAULT);
        System.out.println(response);
    }

    @Test
    void testBulkAddDocuments() throws IOException {
        List<User> users = new ArrayList<User>();
        users.add(new User("社会主义", 18));
        users.add(new User("社会主义接班人", 18));
        users.add(new User("主义", 18));
        users.add(new User("社会", 18));
        users.add(new User("接班", 18));
        users.add(new User("法老", 18));

        BulkRequest bulkRequest = new BulkRequest();

        for (int i = 0; i < users.size(); i++) {
            IndexRequest request = new IndexRequest(IndexConstant.INDEX);
            request.source(JSON.toJSONString(users.get(i)), XContentType.JSON).id((i + 1) + "");
            bulkRequest.add(request);

        }
        BulkResponse responses = restHighLevelClient.bulk(bulkRequest, RequestOptions.DEFAULT);

        System.out.println(responses);
        System.out.println(responses.hasFailures());
    }

    @Test
    void testSearch() throws IOException {
        SearchRequest request = new SearchRequest(IndexConstant.INDEX);
        SearchSourceBuilder sourceBuilder = new SearchSourceBuilder();
        sourceBuilder.query(QueryBuilders.matchPhraseQuery("name", "社会主义"))
                .highlighter(new HighlightBuilder().field("name").preTags("<span>").postTags("</span>"));

        request.source(sourceBuilder);
        SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

        for (SearchHit hit : response.getHits().getHits()) {
            System.out.println(hit);
        }

    }


}
