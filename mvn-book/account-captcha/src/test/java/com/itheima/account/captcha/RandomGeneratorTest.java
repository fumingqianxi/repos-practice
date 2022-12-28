package com.itheima.account.captcha;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.HashSet;
import java.util.Set;
import org.junit.Test;

/**
 * @author 胡磊
 * @since 2022/12/27 22:28
 */
public class RandomGeneratorTest {

  @Test
  public void testGetRandomString() {
    Set<String> randoms = new HashSet<String>(100);
    for (int i = 0; i < 100; i++) {
      String random = RandomGenerator.getRandomString();
      assertFalse(randoms.contains(random));
      randoms.add(random);
      assertTrue(randoms.contains(random));
    }
  }
}
