package com.itheima.elasticsearch.springdataes.dao;

import com.itheima.elasticsearch.springdataes.entity.Article;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * .
 *
 * @author 胡磊
 * @since 2023/10/22 11:31
 */
public interface ArticleRepository extends ElasticsearchRepository<Article, Long> {

  List<Article> findByTitle(String title);
  List<Article> findByTitleOrContent(String title, String content);
  List<Article> findByTitleOrContent(String title, String content, Pageable pageable);
}
