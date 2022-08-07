package com.itheima.JDK8新特性.Java业务开发常见错误100例.functionalinterface;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;
import org.junit.Test;

/**
 * @author {胡磊}
 * @since 2022/8/5 17:37
 */
public class ConcurrentHashMap对函数式API的增强 {


  private Map<Long, Product> cache = new ConcurrentHashMap<>();

  private Product getProductAndCache(Long id) {
    Product product = null;
    //Key存在，返回Value
    if (cache.containsKey(id)) {
      product = cache.get(id);
    } else {
      //不存在，则获取Value
      //需要遍历数据源查询获得Product
      for (Product p : Product.getData()) {
        if (p.getId().equals(id)) {
          product = p;
          break;
        }
      }
      //加入ConcurrentHashMap
      if (product != null)
        cache.put(id, product);
    }
    return product;
  }

  @Test
  public void notcoolCache() {
    getProductAndCache(1L);
    getProductAndCache(100L);

    System.out.println(cache);
    assertThat(cache.size(), is(2));
    assertTrue(cache.containsKey(1L));
    assertTrue(cache.containsKey(100L));
  }


  private Product getProductAndCacheCool(Long id) {
    return cache.computeIfAbsent(id, i -> //当Key不存在的时候提供一个Function来代表根据Key获取Value的过程
        Product.getData().stream()
            .filter(p -> p.getId().equals(i)) //过滤
            .findFirst() //找第一个，得到Optional<Product>
            .orElse(null)); //如果找不到Product，则使用null
  }

  @Test
  public void test() {
    IntStream.rangeClosed(1, 100).parallel().forEach(i->{
      System.out.println(LocalDateTime.now() + " : " + i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) { }
    });
  }

  @Test
  public void coolCache()
  {
    getProductAndCacheCool(1L);
    getProductAndCacheCool(100L);

    System.out.println(cache);
    assertThat(cache.size(), is(2));
    assertTrue(cache.containsKey(1L));
  }
}

class Product {

  private Long id;

  public Product(Long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  private static List<Product> products = new ArrayList<>();

  static {
    products.add(new Product(1L));
    products.add(new Product(2L));
    products.add(new Product(3L));
    products.add(new Product(100L));
  }

  public static List<Product> getData() {
    return products;
  }
}
