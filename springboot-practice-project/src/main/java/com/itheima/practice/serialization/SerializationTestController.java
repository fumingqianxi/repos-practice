package com.itheima.practice.serialization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 序列化测试.
 *
 * @author 胡磊
 * @since 2024/5/4 13:20
 */
@Slf4j
@RestController
@RequestMapping("/serialization/test")
public class SerializationTestController {

  /**
   * http://localhost:45678/serialization/test/serialize.
   */
  @GetMapping("serialize")
  public void serialize() throws IOException {
    SerializationStudent student = new SerializationStudent();
    student.setName("CodeSheep");
    student.setAge(18);
    student.setScore(1000);

    String jsonStr = JSON.toJSONString(student);
    Files.write(Paths.get("student-json.txt"), jsonStr.getBytes(StandardCharsets.UTF_8));
    System.out.println("序列化成功！已经生成student-json.txt文件");
    System.out.println("==============================================");

    ObjectOutputStream objectOutputStream =
        new ObjectOutputStream(new FileOutputStream(new File("student.txt")));
    objectOutputStream.writeObject(student);
    objectOutputStream.close();
    System.out.println("序列化成功！已经生成student.txt文件");
  }

  /**
   * http://localhost:45678/serialization/test/deserialize.
   */
  @GetMapping("deserialize")
  public void deserialize() throws IOException, ClassNotFoundException {
    List<String> list = Files.readAllLines(Paths.get("student-json.txt"), StandardCharsets.UTF_8);
    SerializationStudent studentJson = JSON.parseObject(list.get(0), SerializationStudent.class);
    System.out.println("JSON反序列化结果为：");
    System.out.println(studentJson);
    System.out.println("==============================================");

    ObjectInputStream objectInputStream =
        new ObjectInputStream(new FileInputStream(new File("student.txt")));
    SerializationStudent student = (SerializationStudent) objectInputStream.readObject();
    objectInputStream.close();
    System.out.println("反序列化结果为：");
    System.out.println(student);
  }

  /**
   * http://localhost:45678/serialization/test/json-str.
   */
  @GetMapping("json-str")
  public void jsonStr() throws IOException {
    SerializationStudent student = new SerializationStudent();
    student.setName("CodeSheep");
    student.setAge(18);
    student.setScore(1000);
    JSONObject jsonObject = new JSONObject();
    jsonObject.put("jsonStr", JSON.toJSONString(student));
    System.out.println("单层嵌套结果为：");
    System.out.println(jsonObject.toJSONString());
    Files.write(Paths.get("json.txt"), jsonObject.toJSONString().getBytes(StandardCharsets.UTF_8));
    System.out.println("==============================================");
    JSONObject jsonObject2 = new JSONObject();
    jsonObject2.put("jsonStr", jsonObject.toJSONString());
    System.out.println("双层嵌套结果为：");
    System.out.println(jsonObject2.toJSONString());
    Files.write(
        Paths.get("json2.txt"), jsonObject2.toJSONString().getBytes(StandardCharsets.UTF_8));
    System.out.println("==============================================");
    JSONObject jsonObject3 = new JSONObject();
    jsonObject3.put("jsonStr", jsonObject2.toJSONString());
    System.out.println("三层嵌套结果为：");
    System.out.println(jsonObject3.toJSONString());
    Files.write(
        Paths.get("json3.txt"), jsonObject3.toJSONString().getBytes(StandardCharsets.UTF_8));
    System.out.println("==============================================");
    JSONObject jsonObject4 = new JSONObject();
    jsonObject4.put("jsonStr", jsonObject3.toJSONString());
    System.out.println("四层嵌套结果为：");
    System.out.println(jsonObject4.toJSONString());
    Files.write(
        Paths.get("json4.txt"), jsonObject4.toJSONString().getBytes(StandardCharsets.UTF_8));
    System.out.println("==============================================");
  }
}
