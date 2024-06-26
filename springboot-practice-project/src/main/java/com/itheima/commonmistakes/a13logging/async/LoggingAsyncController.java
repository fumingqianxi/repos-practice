package com.itheima.commonmistakes.a13logging.async;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Marker;
import org.slf4j.MarkerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * 异步日志问题示例.
 *
 * @author 胡磊
 * @since 2024/2/8 9:37
 */
@Slf4j
@RequestMapping("logging")
@RestController
public class LoggingAsyncController {

  @GetMapping("manylog")
  public void manylog(@RequestParam(name = "count", defaultValue = "1000") int count) {
    long begin = System.currentTimeMillis();
    IntStream.rangeClosed(1, count).forEach(i -> log.info("log-{}", i));
    System.out.println("took " + (System.currentTimeMillis() - begin) + " ms");
  }

  @GetMapping("performance")
  public void performance(@RequestParam(name = "count", defaultValue = "10000") int count) {
    long begin = System.currentTimeMillis();
    String payload = IntStream.rangeClosed(1, 1000000)
        .mapToObj(__ -> "a")
        .collect(Collectors.joining("")) + UUID.randomUUID().toString();
    IntStream.rangeClosed(1, count).forEach(i -> log.info("{} {}", i, payload));
    Marker timeMarker = MarkerFactory.getMarker("time");
    log.info(timeMarker, "took {} ms", System.currentTimeMillis() - begin);
  }
}
