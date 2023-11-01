package com.itheima.Groovy程序设计测试代码.第3章.静态类型检查

def shoutString(String str) {
  println str.shout()
}

str = 'hello'
str.metaClass.shout = {-> toUpperCase() }
shoutString(str)
