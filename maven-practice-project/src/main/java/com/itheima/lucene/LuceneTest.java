package com.itheima.lucene;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.TokenStream;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.analysis.tokenattributes.CharTermAttribute;
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
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.junit.Test;
import org.wltea.analyzer.lucene.IKAnalyzer;

/**
 * Lucene测试类.
 *
 * @author 胡磊
 * @since 2023/10/19 9:22
 */
@Slf4j
public class LuceneTest {

  @Test
  public void createIndex() throws IOException {
    try (
        // 1、创建一个Director对象，指定索引库保存的位置
        // 把索引库保存在内存中
        //    Directory directory = new RAMDirectory();
        // 把索引库保存在磁盘
        Directory directory = FSDirectory.open(new File("D:\\Test\\lucene\\index").toPath());
        // 2、基于Directory对象创建一个IndexWriter对象
//        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig())
        IndexWriter indexWriter = new IndexWriter(directory, new IndexWriterConfig(new IKAnalyzer()))
    ) {
      // 3、读取磁盘上的文件，对应每个文件创建一个文档对象
      File dir = new File("D:\\Test\\lucene\\searchsource");
      File[] files = dir.listFiles();
      for (File f : files) {
        // 文件名
        String fileName = f.getName();
        // 文件路径
        String filePath = f.getPath();
        // 文件内容
        String fileContent = FileUtils.readFileToString(f, StandardCharsets.UTF_8);
        // 文件大小
        long fileSize = FileUtils.sizeOf(f);
        // 创建域，参数1：域的名称，参数2：域的内容，参数3：是否存储
        Field fieldName = new TextField("name", fileName, Field.Store.YES);
//        Field fieldPath = new TextField("path", filePath, Field.Store.YES);
        Field fieldPath = new StoredField("path", filePath);
        Field fieldContent = new TextField("content", fileContent, Field.Store.YES);
//        Field fieldSize = new TextField("size", fileSize + "", Field.Store.YES);
        // 分词和索引，但是不存储
        Field fieldSize = new LongPoint("size", fileSize);
        // 单独存储下文件大小，才能取出数据
        Field fieldSizeStore = new StoredField("size", fileSize);
        // 创建文档
        Document document = new Document();
        // 4、向文档中添加域
        document.add(fieldName);
        document.add(fieldPath);
        document.add(fieldContent);
        document.add(fieldSize);
        document.add(fieldSizeStore);
        // 5、把文档对象写入索引库
        indexWriter.addDocument(document);
      }
      // 6、关闭资源，已使用try-with-resources，省略该步骤
    }
  }

  @Test
  public void searchIndex() throws IOException {
    try (
        // 1、创建一个Directory对象，指定索引库的位置
        Directory directory = FSDirectory.open(new File("D:\\Test\\lucene\\index").toPath());
        // 2、创建一个IndexReader对象
        IndexReader indexReader = DirectoryReader.open(directory)
    ) {
      // 3、创建一个IndexSearcher对象，构造方法中的参数indexReader对象
      IndexSearcher indexSearcher = new IndexSearcher(indexReader);
      // 4、创建一个Query对象，TermQuery
      Query query = new TermQuery(new Term("content", "spring"));
      // 5、执行查询，得到一个TopDocs对象，参数1：查询对象 参数2：最大记录数
      TopDocs topDocs = indexSearcher.search(query, 10);
      // 6、取查询结果的总记录数
      log.info("查询总记录数：{}", topDocs.totalHits);
      // 7、取文档列表
      ScoreDoc[] scoreDocs = topDocs.scoreDocs;
      // 8、打印文档中的内容
      for (ScoreDoc scoreDoc : scoreDocs) {
        Document document = indexSearcher.doc(scoreDoc.doc);
        log.info("文件名称：{}", document.get("name"));
        log.info("文件路径：{}", document.get("path"));
        log.info("文件大小：{}", document.get("size"));
        log.info("文件内容：{}", document.get("content"));
        log.info("-----------寂寞的分割线-----------");
      }
      // 关闭资源，已使用try-with-resources，省略该步骤
    }
  }

  /**
   * 查看分词器的分词结果.
   *
   * @throws IOException 异常
   */
  @Test
  public void testTokenStream() throws IOException {
    // 1、创建一个Analyzer对象，StandardAnalyzer对象
//    Analyzer analyzer = new StandardAnalyzer();
    Analyzer analyzer = new IKAnalyzer();
    // 2、使用分析器对象的tokenStream方法获得一个TokenStream对象
    //    TokenStream tokenStream = analyzer.tokenStream("",
    //                                                   "The Spring Framework provides a
    //                                                   comprehensive"
    //                                                       + " programming and configuration
    //                                                       model.");
    TokenStream tokenStream = analyzer.tokenStream(
        "",
        "2017年12月14日 - 传智播客Lucene概述公安局Lucene是一款高性能的、可扩展的信息检索(IR)"
            + "工具库。信息检索是指文档搜索、文档内信息搜索或者文档相关的元数据搜索等操作。");
    // 3、向TokenStream对象中设置一个引用，相当于数一个指针
    CharTermAttribute charTermAttribute = tokenStream.addAttribute(CharTermAttribute.class);
    // 4、调用TokenStream对象的rest方法。如果不调用抛异常
    tokenStream.reset();
    // 5、使用while循环遍历TokenStream对象
    while (tokenStream.incrementToken()) {
      log.info(charTermAttribute.toString());
    }
    // 6、关闭TokenStream对象
    tokenStream.close();
  }
}
