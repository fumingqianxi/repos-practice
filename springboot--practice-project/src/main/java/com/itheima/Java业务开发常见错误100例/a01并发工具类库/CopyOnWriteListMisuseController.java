package com.itheima.Java业务开发常见错误100例.a01并发工具类库;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * CopyOnWriteArrayList错误使用示例.
 *
 * @author 胡磊
 * @since 2023/10/23 17:45
 */
@Slf4j
@RestController
@RequestMapping("/copy-on-write-list-misuse")
public class CopyOnWriteListMisuseController {

  /**
   * http://localhost:45678/copy-on-write-list-misuse/write.
   *
   * @return .
   */
  @GetMapping("/write")
  public Map<String, Integer> testWrite() {
    List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
    // 并发写情况下会报错
//    List<Integer> synchronizedList = new ArrayList<>();
    List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    StopWatch stopWatch = new StopWatch();
    int loopCount = 100000;
    stopWatch.start("Write:copyOnWriteArrayList");
    IntStream.rangeClosed(1, loopCount)
        .parallel()
        .forEach(__ -> copyOnWriteArrayList.add(ThreadLocalRandom.current().nextInt(loopCount)));
    stopWatch.stop();
    stopWatch.start("Write:synchronizedList");
    IntStream.rangeClosed(1, loopCount)
        .parallel()
        .forEach(__ -> synchronizedList.add(ThreadLocalRandom.current().nextInt(loopCount)));
    stopWatch.stop();
    log.info(stopWatch.prettyPrint());
    Map<String, Integer> result = new HashMap<>();
    result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
    result.put("synchronizedList", synchronizedList.size());
    return result;
  }

  /**
   * http://localhost:45678/copy-on-write-list-misuse/read.
   *
   * @return .
   */
  @GetMapping("/read")
  public Map<String, Integer> testRead() {
    List<Integer> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
//    List<Integer> synchronizedList = new ArrayList<>();
    List<Integer> synchronizedList = Collections.synchronizedList(new ArrayList<>());
    addAll(copyOnWriteArrayList);
    addAll(synchronizedList);
    StopWatch stopWatch = new StopWatch();
    int loopCount = 1000000;
    int count = copyOnWriteArrayList.size();
    stopWatch.start("Read:copyOnWriteArrayList");
    IntStream.rangeClosed(1, loopCount)
        .parallel()
        .forEach(__ -> copyOnWriteArrayList.get(ThreadLocalRandom.current().nextInt(count)));
    stopWatch.stop();
    stopWatch.start("Read:synchronizedList");
    IntStream.range(0, loopCount)
        .parallel()
        .forEach(__ -> synchronizedList.get(ThreadLocalRandom.current().nextInt(count)));
    stopWatch.stop();
    log.info(stopWatch.prettyPrint());
    Map<String, Integer> result = new HashMap<>();
    result.put("copyOnWriteArrayList", copyOnWriteArrayList.size());
    result.put("synchronizedList", synchronizedList.size());
    return result;
  }

  private void addAll(List<Integer> list) {
    list.addAll(IntStream.rangeClosed(1, 1000000).boxed().collect(Collectors.toList()));
  }
}
