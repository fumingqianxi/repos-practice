package com.itheima.循环方式

// 方式1
0.upto(2) { print "$it " }
println ""
// 方式2，默认从0开始
3.times { print "$it " }
println ""
// 方式3，循环并跳过一些值
0.step(10, 2) { print "$it "}
println ""
// 方式4，传统遍历
for(ch = 'a'; ch < 'd'; ch++) {
  println ch
}
println ""
// 方式5，迭代
String[] greetings = ["Hello", "Hi", "Howdy"]
for(String greet : greetings) {
  println greet
}

