package com.example.fuzz;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.RandomUtil;
import com.example.fuzz.model.Article;
import com.example.fuzz.repository.ArticleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/5/28 15:44
 */
@SpringBootTest
class MongoTest {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Test
    void testInsert() {
        Article article = new Article(1L, RandomUtil.randomString(20), RandomUtil.randomString(150), DateUtil.date(), DateUtil.date(), 0L, 0L, Arrays.asList("red", "blue"));
        articleRepository.insert(article);
        System.out.println("插入成功");
    }


    @Test
    void testUpdate() {
        Article article = new Article();
        article.setId(1L);
        article.setTitle("更新后的标题");
        articleRepository.save(article);
        System.out.println("更新成功");
    }

    @Test
    void testUpdate2() {
        // 查询后如果有  更新
        articleRepository.findById(1L).ifPresent(article -> {
            article.setCreateTime(new Date());
            article.setContent("ccccc");
            articleRepository.save(article);
        });
    }

    @Test
    void testFind() {
        Query query = new Query();
        query.addCriteria(Criteria.where("title").is("更新后的标题1"));

        Article article = mongoTemplate.findOne(query, Article.class);

        System.out.println(article);
    }

    @Test
    void testPage() {
        Query query = new Query();
//        query.addCriteria(Criteria.where("title").lt("更新后的标题"));
        query.with(Sort.by("id").descending());
        query.skip(0).limit(2);
        List<Article> articles = mongoTemplate.find(query, Article.class);
        System.out.println(articles);
    }

}
