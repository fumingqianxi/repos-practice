package com.itheima.异常处理

def openFile(fileName) {
  new FileInputStream(fileName)
}

openFile("nonexistentfile")

try {
  openFile("nonexistentfile")
} catch(FileNotFoundException ex) {
  // 关于该异常，在这里想做什么就做什么
  println "Oops: " + ex
}
