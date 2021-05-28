package com.example.fuzz.repository;

import com.example.fuzz.model.Article;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * <p>
 *
 * </p>
 *
 * @author fuzz
 * @since 2021/5/28 15:41
 */
public interface ArticleRepository extends MongoRepository<Article, Long> {
}
