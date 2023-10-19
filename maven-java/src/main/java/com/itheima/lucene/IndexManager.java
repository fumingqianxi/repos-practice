package com.itheima.lucene;

import java.io.File;
import java.io.IOException;
import lombok.extern.slf4j.Slf4j;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.LongPoint;
import org.apache.lucene.document.StoredField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.junit.Before;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * 索引库维护，增删改查等操作.
 *
 * @author 胡磊
 * @since 2023/10/19 15:44
 */
@Slf4j
public class IndexManager {

  private IndexWriter indexWriter;
  private IndexReader indexReader;
  private IndexSearcher indexSearcher;

  @Before
  public void init() throws IOException {
    indexWriter = new IndexWriter(
        FSDirectory.open(new File("D:\\Test\\lucene\\index").toPath()),
        new IndexWriterConfig(new IKAnalyzer()));
    indexReader =
        DirectoryReader.open(FSDirectory.open(new File("D:\\Test\\lucene\\index").toPath()));
    indexSearcher = new IndexSearcher(indexReader);
  }

  @Test
  public void addDocument() throws IOException {
    Document document = new Document();
    document.add(new TextField("fileName", "新添加的文件", Field.Store.YES));
    document.add(new TextField("content", "新添加的文件内容", Field.Store.NO));
    document.add(new StoredField("path", "D:\\Test\\lucene\\searchsource"));
    indexWriter.addDocument(document);
    indexWriter.close();
  }

  @Test
  public void deleteAll() throws IOException {
    indexWriter.deleteAll();
    indexWriter.close();
  }

  @Test
  public void deleteByQuery() throws IOException {
    indexWriter.deleteDocuments(new Term("name", "apache"));
    indexWriter.close();
  }

  @Test
  public void updateDoc() throws IOException {
    Document document = new Document();
    document.add(new TextField("name", "更新之后的文档", Field.Store.YES));
    document.add(new TextField("name2", "更新之后的文档2", Field.Store.YES));
    document.add(new TextField("name3", "更新之后的文档3", Field.Store.YES));
    indexWriter.updateDocument(new Term("name", "spring"), document);
    indexWriter.close();
  }

  @Test
  public void rangeQuery() throws IOException {
    Query query = LongPoint.newRangeQuery("size", 0, 100);
    printResult(query);
  }

  /**
   * 将查询条件分词再查询，与浏览器输入内容查询效果相似.
   *
   * @throws IOException .
   * @throws ParseException .
   */
  @Test
  public void queryParser() throws IOException, ParseException {
    QueryParser queryParser = new QueryParser("name", new IKAnalyzer());
    Query query = queryParser.parse("lucene是一个Java开发的全文检索工具包");
    printResult(query);
  }

  private void printResult(Query query) throws IOException {
    TopDocs topDocs = indexSearcher.search(query, 10);
    // 取查询结果的总记录数
    log.info("查询总记录数：{}", topDocs.totalHits);
    // 取文档列表
    ScoreDoc[] scoreDocs = topDocs.scoreDocs;
    // 打印文档中的内容
    for (ScoreDoc scoreDoc : scoreDocs) {
      Document document = indexSearcher.doc(scoreDoc.doc);
      log.info("文件名称：{}", document.get("name"));
      log.info("文件路径：{}", document.get("path"));
      log.info("文件大小：{}", document.get("size"));
      log.info("文件内容：{}", document.get("content"));
      log.info("-----------寂寞的分割线-----------");
    }
    indexReader.close();
  }
}
