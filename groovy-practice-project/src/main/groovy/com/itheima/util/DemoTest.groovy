package com.itheima.util

/**
 * @author {胡磊}
 * @since 2022/6/2 17:08
 */
class DemoTest {
  static void main(String[] args) {
    List a = [1, 2, 3, 4]
    List b = [2, 3, 4, 5]
    print(a - (a - b))
  }
}

