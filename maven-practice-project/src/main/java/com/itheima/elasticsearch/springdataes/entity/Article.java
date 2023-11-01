package com.itheima.elasticsearch.springdataes.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * .
 *
 * @author 胡磊
 * @since 2023/10/22 10:05
 */
@Data
@Document(indexName = "spring_data_es_blog")
public class Article {
  @Id
  @Field(type = FieldType.Long, store = true)
  private long id;

  @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
  private String title;

  @Field(type = FieldType.Text, store = true, analyzer = "ik_smart")
  private String content;
}
