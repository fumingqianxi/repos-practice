package com.itheima.Java业务开发常见错误100例.a08判等问题.intandstringequal;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author {胡磊}
 * @since 2022/8/25 17:48
 */
@RestController
@Slf4j
@RequestMapping("intandstringequal")
public class IntAndStringEqualController {

  List<String> list = new ArrayList<>();

  @GetMapping("internperformance")
  public int internperformance(@RequestParam(value = "size", defaultValue = "10000000") int size) {
    //-XX:+PrintStringTableStatistics
    //-XX:StringTableSize=10000000
    long begin = System.currentTimeMillis();
    list = IntStream.rangeClosed(1, size)
        .mapToObj(i -> String.valueOf(i).intern())
        .collect(Collectors.toList());
    log.info("size:{} took:{}", size, System.currentTimeMillis() - begin);
    return list.size();
   }

  @GetMapping("stringcompare")
  public void stringcomare() {
    String a = "1";
    String b = "1";
    log.info("\nString a = \"1\";\n" +
                 "String b = \"1\";\n" +
                 "a == b ? {}", a == b); //true

    String c = new String("2");
    String d = new String("2");
    log.info("\nString c = new String(\"2\");\n" +
                 "String d = new String(\"2\");" +
                 "c == d ? {}", c == d); //false

    String e = new String("3").intern();
    String f = new String("3").intern();
    log.info("\nString e = new String(\"3\").intern();\n" +
                 "String f = new String(\"3\").intern();\n" +
                 "e == f ? {}", e == f); //true

    String g = new String("4");
    String h = new String("4");
    log.info("\nString g = new String(\"4\");\n" +
                 "String h = new String(\"4\");\n" +
                 "g == h ? {}", g.equals(h)); //true
  }

  @GetMapping("intcompare")
  public void intcompare() {

    Integer a = 127;
    Integer b = 127;
    log.info("\nInteger a = 127;\n" + "Integer b = 127;\n" + "a ==b ? {}", a == b);

    Integer c = 128; //Integer.valueOf(128)
    Integer d = 128; //Integer.valueOf(128)
    log.info("\nInteger c = 128;\n" +
                 "Integer d = 128;\n" +
                 "c == d ? {}", c == d);   //false
    //设置-XX:AutoBoxCacheMax=1000再试试

    Integer e = 127; //Integer.valueOf(127)
    Integer f = new Integer(127); //new instance
    log.info("\nInteger e = 127;\n" +
                 "Integer f = new Integer(127);\n" +
                 "e == f ? {}", e == f);   //false

    Integer g = new Integer(127); //new instance
    Integer h = new Integer(127); //new instance
    log.info("\nInteger g = new Integer(127);\n" +
                 "Integer h = new Integer(127);\n" +
                 "g == h ? {}", g == h);  //false

    Integer i = 128; //unbox
    int j = 128;
    log.info("\nInteger i = 128;\n" +
                 "int j = 128;\n" +
                 "i == j ? {}", i == j); //true
  }
}
