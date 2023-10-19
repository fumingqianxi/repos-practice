package com.itheima.elasticsearch;

import com.itheima.elasticsearch.springdataes.dao.ArticleRepository;
import com.itheima.elasticsearch.springdataes.entity.Article;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * es测试.
 *
 * @author 胡磊
 * @since 2023/10/22 10:37
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:elasticsearchContext.xml")
@Slf4j
public class SpringDataElasticSearchTest {

  @Autowired
  private ArticleRepository articleRepository;
  @Autowired
  private ElasticsearchTemplate template;

  @Test
  public void createIndex() {
    // 创建索引，并配置映射关系，由于启动时会自动创建索引，所以执行该测试会失败，提示索引已经存在
    template.indexOps(Article.class).createWithMapping();
    // 配置映射关系
    //    template.putMapping(Article.class);
  }

  @Test
  public void addDoc() {
    for (int i = 1; i < 20; i++) {
      // 创建一个Article对象
      Article article = new Article();
      article.setId(i);
      article.setTitle("【图解】习近平寄语中俄青少年" + i);
      article.setContent("盛会再携手—各国政要高度评价东博会和商务与投资峰会");
      // 把文档写入索引库
      articleRepository.save(article);
    }
  }

  @Test
  public void deleteById() {
    articleRepository.deleteById(0L);
  }

  @Test
  public void updateDoc() {
    Article article = new Article();
    article.setId(1);
    article.setTitle("更新【图解】习近平寄语中俄青少年");
    article.setContent("更新盛会再携手—各国政要高度评价东博会和商务与投资峰会");
    // 更新文档
    articleRepository.save(article);
  }

  @Test
  public void findAll() {
    Iterable<Article> all = articleRepository.findAll();
    all.forEach(a -> log.info(a.toString()));
  }

  @Test
  public void findById() {
    Optional<Article> optional = articleRepository.findById(1L);
    Article article = optional.get();
    log.info(article.toString());
  }

  @Test
  public void findByTitle() {
    // 将查询条件分词，并且是分词后是and查询
    List<Article> list = articleRepository.findByTitle("习近平寄语中国");
    list.forEach(a -> log.info(a.toString()));
  }

  @Test
  public void findByTitleOrContent() {
    articleRepository.findByTitleOrContent("图解", "盛会再携手")
        .forEach(a -> log.info(a.toString()));
  }

  @Test
  public void findByTitleOrContent2() {
    Pageable pageable = PageRequest.of(0, 5);
    articleRepository.findByTitleOrContent("图解", "盛会再携手", pageable)
        .forEach(a -> log.info(a.toString()));
  }

  @Test
  public void nativeSearchQuery() {
    // 创建一个查询对象
    NativeSearchQuery query = new NativeSearchQueryBuilder()
        .withQuery(QueryBuilders.queryStringQuery("习近平寄语中国").defaultField("title"))
        .withPageable(PageRequest.of(0, 5))
        .build();
    // 执行查询
    SearchHits<Article> search = template.search(query, Article.class);
    Iterator<SearchHit<Article>> iterator = search.iterator();
    while (iterator.hasNext()) {
      SearchHit<Article> next = iterator.next();
      // 打印文档对象，以json格式输出
      log.info(next.getContent().toString());
    }
  }
}
