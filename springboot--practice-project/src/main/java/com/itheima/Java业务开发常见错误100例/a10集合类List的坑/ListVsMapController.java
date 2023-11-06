package com.itheima.Java业务开发常见错误100例.a10集合类List的坑;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import jdk.nashorn.internal.ir.debug.ObjectSizeCalculator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * List和Map性能对比示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/list-vs-map")
public class ListVsMapController {

  /** http://localhost:45678/list-vs-map/test. */
  @GetMapping("/test")
  public void test() throws Exception {
    int elementCount = 1000000;
    int loopCount = 1000;
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("listSearch");
    Object list = listSearch(elementCount, loopCount);
    System.out.println(ObjectSizeCalculator.getObjectSize(list));
    stopWatch.stop();
    stopWatch.start("mapSearch");
    Object map = mapSearch(elementCount, loopCount);
    stopWatch.stop();
    System.out.println(ObjectSizeCalculator.getObjectSize(map));
    System.out.println(stopWatch.prettyPrint());
    TimeUnit.HOURS.sleep(1);
  }

  private Object listSearch(int elementCount, int loopCount) {
    List<Order> list =
        IntStream.rangeClosed(1, elementCount)
            .mapToObj(i -> new Order(i))
            .collect(Collectors.toList());
    IntStream.rangeClosed(1, loopCount)
        .forEach(
            i -> {
              int search = ThreadLocalRandom.current().nextInt(elementCount);
              Order result =
                  list.stream()
                      .filter(order -> order.getOrderId() == search)
                      .findFirst()
                      .orElse(null);
              Assert.assertTrue(result != null && result.getOrderId() == search);
            });
    return list;
  }

  private Object mapSearch(int elementCount, int loopCount) {
    Map<Integer, Order> map =
        IntStream.rangeClosed(1, elementCount)
            .boxed()
            .collect(Collectors.toMap(Function.identity(), i -> new Order(i)));
    IntStream.rangeClosed(1, loopCount)
        .forEach(
            i -> {
              int search = ThreadLocalRandom.current().nextInt(elementCount);
              Order result = map.get(search);
              Assert.assertTrue(result != null && result.getOrderId() == search);
            });
    return map;
  }

  @Data
  @NoArgsConstructor
  @AllArgsConstructor
  class Order {
    private int orderId;
  }
}
