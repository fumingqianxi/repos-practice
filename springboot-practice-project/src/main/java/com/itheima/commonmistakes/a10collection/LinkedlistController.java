package com.itheima.commonmistakes.a10collection;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * LinkedList和ArrayList性能对比示例.
 *
 * @author 胡磊
 * @since 2023/11/6 13:20
 */
@Slf4j
@RestController
@RequestMapping("/linkedlist")
public class LinkedlistController {

  /** http://localhost:45678/linkedlist/test. */
  @GetMapping("/test")
  public void test() throws Exception {
    int elementCount = 100000;
    int loopCount = 100000;
    StopWatch stopWatch = new StopWatch();
    stopWatch.start("linkedListGet");
    linkedListGet(elementCount, loopCount);
    stopWatch.stop();
    stopWatch.start("arrayListGet");
    arrayListGet(elementCount, loopCount);
    stopWatch.stop();
    System.out.println(stopWatch.prettyPrint());

    StopWatch stopWatch2 = new StopWatch();
    stopWatch2.start("linkedListAdd");
    linkedListAdd(elementCount, loopCount);
    stopWatch2.stop();
    stopWatch2.start("arrayListAdd");
    arrayListAdd(elementCount, loopCount);
    stopWatch2.stop();
    System.out.println(stopWatch2.prettyPrint());
  }

  private static void linkedListGet(int elementCount, int loopCount) {
    List<Integer> list =
        IntStream.rangeClosed(1, elementCount)
            .boxed()
            .collect(Collectors.toCollection(LinkedList::new));
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
  }

  private static void arrayListGet(int elementCount, int loopCount) {
    List<Integer> list =
        IntStream.rangeClosed(1, elementCount)
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.get(ThreadLocalRandom.current().nextInt(elementCount)));
  }

  private static void linkedListAdd(int elementCount, int loopCount) {
    List<Integer> list =
        IntStream.rangeClosed(1, elementCount)
            .boxed()
            .collect(Collectors.toCollection(LinkedList::new));
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    // 尾插还是LinkList更快
    //    IntStream.rangeClosed(1, loopCount)
    //        .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount)));
  }

  private static void arrayListAdd(int elementCount, int loopCount) {
    List<Integer> list =
        IntStream.rangeClosed(1, elementCount)
            .boxed()
            .collect(Collectors.toCollection(ArrayList::new));
    IntStream.rangeClosed(1, loopCount)
        .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount), 1));
    // 尾插还是LinkList更快
    //    IntStream.rangeClosed(1, loopCount)
    //        .forEach(i -> list.add(ThreadLocalRandom.current().nextInt(elementCount)));
  }
}
