package com.itheima.elasticsearch.client;

import com.alibaba.fastjson.JSON;
import java.io.IOException;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.text.Text;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightBuilder;
import org.elasticsearch.search.fetch.subphase.highlight.HighlightField;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.junit.Before;
import org.junit.Test;

/**
 * 通过Java客户端操作es.
 *
 * @author 胡磊
 * @since 2023/10/21 19:04
 */
@Slf4j
public class ElasticSearchClientTest {

  private TransportClient client;

  @Before
  public void init() throws Exception {
    // 创建一个Settings对象，相当于是一个配置信息，主要配置集群的名称
    Settings settings = Settings.builder()
        .put("cluster.name", "my-elasticsearch")
        .build();
    // 创建一个客户端Client对象
    client = new PreBuiltTransportClient(settings);
    client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9301));
    client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9302));
    client.addTransportAddress(new TransportAddress(InetAddress.getByName("127.0.0.1"), 9303));
  }

  @Test
  public void createIndex() throws Exception {
    // 使用client对象创建一个索引库
    client.admin().indices().prepareCreate("index_hello").get();
    // 关闭client对象
    client.close();
  }

  @Test
  public void setMappings() throws IOException {
    // 创建一个Mappings信息
    /*{
        "article":{
        "properties":{
            "id":{
                "type":"long",
                "store":true
            },
            "title":{
                "type":"text",
                "store":true,
                "index":true,
                "analyzer":"ik_smart"
            },
            "content":{
                "type":"text",
                "store":true,
                "index":true,
                "analyzer":"ik_smart"
            }
        }
    }
    }*/
    XContentBuilder builder = XContentFactory.jsonBuilder()
        .startObject()
        .startObject("properties")
        .startObject("id")
        .field("type", "long")
        .field("store", true)
        .endObject()
        .startObject("title")
        .field("type", "text")
        .field("store", true)
        .field("index", true)
        .field("analyzer", "ik_smart")
        .endObject()
        .startObject("content")
        .field("type", "text")
        .field("store", true)
        .field("index", true)
        .field("analyzer", "ik_smart")
        .endObject()
        .endObject()
        .endObject();
    // 使用client把mapping信息设置到索引库中
    client.admin().indices().preparePutMapping("index_hello").setType("_doc").setSource(builder)
        .get();
    client.close();
  }

  @Test
  public void addDoc() throws IOException {
    XContentBuilder builder = XContentFactory.jsonBuilder()
        .startObject()
        .field("id", 2l)
        .field("title", "北方入秋速度明显加快 多地降温幅度最多可达10度22222")
        .field("content", "阿联酋一架客机在纽约机场被隔离 10名乘客病倒")
        .endObject();
    client.prepareIndex()
        .setIndex("index_hello")
        .setType("_doc")
        .setSource(builder)
        .get();
    client.close();
  }

  @Test
  public void addDoc2() {
    Article article = new Article();
    //设置对象的属性
    article.setId(3l);
    article.setTitle("MH370坠毁在柬埔寨密林?中国一公司调十颗卫星去拍摄");
    article.setContent("警惕荒唐的死亡游戏!俄15岁少年输掉游戏后用电锯自杀");
    String jsonStr = JSON.toJSONString(article);
    client.prepareIndex("index_hello", "_doc", "3")
        .setSource(jsonStr, XContentType.JSON)
        .get();
    client.close();
  }

  @Test
  public void addDoc3() {
    for (int i = 4; i < 100; i++) {
      //创建一个Article对象
      Article article = new Article();
      //设置对象的属性
      article.setId(i);
      article.setTitle("女护士路遇昏迷男子跪地抢救：救人是职责更是本能" + i);
      article.setContent("江西变质营养餐事件已致24人就医 多名官员被调查" + i);
      String jsonStr = JSON.toJSONString(article);
      //使用client对象把文档写入索引库
      client.prepareIndex("index_hello", "_doc", i + "")
          .setSource(jsonStr, XContentType.JSON)
          .get();
    }
    //关闭客户端
    client.close();
  }

  @Test
  public void searchById() {
    QueryBuilder idsQueryBuilder = QueryBuilders.idsQuery().addIds("3", "2");
    search(idsQueryBuilder);
  }

  @Test
  public void queryByTerm() {
    //创建一个QueryBuilder对象
    //参数1：要搜索的字段
    //参数2：要搜索的关键词
    QueryBuilder queryBuilder = QueryBuilders.termQuery("title", "北方");
    search(queryBuilder);
  }

  @Test
  public void searchByQueryString() {
    QueryBuilder queryBuilder = QueryBuilders.queryStringQuery("女护士").defaultField("title");
    search(queryBuilder, "title");
  }

  private void search(QueryBuilder queryBuilder) {
    // 执行查询
    SearchResponse searchResponse = client.prepareSearch("index_hello")
        .setTypes("_doc")
        .setQuery(queryBuilder)
        .setFrom(0)
        .setSize(5)
        .get();
    // 取查询结果
    SearchHits searchHits = searchResponse.getHits();
    // 取查询结果的总记录数
    log.info("查询结果总记录数：{}", searchHits.getTotalHits());
    // 查询结果列表
    Iterator<SearchHit> iterator = searchHits.iterator();
    while (iterator.hasNext()) {
      SearchHit searchHit = iterator.next();
      // 打印文档对象，以json格式输出
      log.info(searchHit.getSourceAsString());
      // 取文档的属性
      log.info("---------文档的属性---------");
      Map<String, Object> doc = searchHit.getSourceAsMap();
      log.info(doc.get("id") + "");
      log.info(doc.get("title") + "");
      log.info(doc.get("content") + "");
    }
    // 关闭client
    client.close();
  }

  private void search(QueryBuilder queryBuilder, String highlightField) {
    HighlightBuilder highlightBuilder = new HighlightBuilder();
    //高亮显示的字段
    highlightBuilder.field(highlightField);
    highlightBuilder.preTags("<em>");
    highlightBuilder.postTags("</em>");
    // 执行查询
    SearchResponse searchResponse = client.prepareSearch("index_hello")
        .setTypes("_doc")
        .setQuery(queryBuilder)
        .setFrom(0)
        .setSize(5)
        .highlighter(highlightBuilder)
        .get();
    // 取查询结果
    SearchHits searchHits = searchResponse.getHits();
    // 取查询结果的总记录数
    log.info("查询结果总记录数：{}", searchHits.getTotalHits());
    // 查询结果列表
    Iterator<SearchHit> iterator = searchHits.iterator();
    while (iterator.hasNext()) {
      SearchHit searchHit = iterator.next();
      // 打印文档对象，以json格式输出
      log.info(searchHit.getSourceAsString());
      // 取文档的属性
      log.info("---------文档的属性---------");
      Map<String, Object> doc = searchHit.getSourceAsMap();
      log.info(doc.get("id") + "");
      log.info(doc.get("title") + "");
      log.info(doc.get("content") + "");
      log.info("---------高亮结果---------");
      Map<String, HighlightField> highlightFields = searchHit.getHighlightFields();
      log.info(highlightFields.toString());
      //取title高亮显示的结果
      HighlightField field = highlightFields.get(highlightField);
      Text[] fragments = field.getFragments();
      if (fragments != null) {
        String title = fragments[0].toString();
        log.info(title);
      }
    }
    // 关闭client
    client.close();
  }
}
