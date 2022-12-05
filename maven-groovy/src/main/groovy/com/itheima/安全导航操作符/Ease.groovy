package com.itheima.安全导航操作符

// 使用?.在空引用上调用reverse()，其结果是产生了一个null，而没有抛出空指针异常（NullPointerException）
def foo(str) {
  //if (str != null) { str.reverse() }
  str?.reverse()
}

println foo('evil')
println foo(null)
