package com.itheima.多方法

import com.itheima.多方法.java.Employee
import com.itheima.多方法.java.Executive

// 在多方法中groovy可以执行到executive类的参数是BigDecimal的方法，而在java代码中只能执行参数为Number的方法
void giveRaise(Employee employee) {
  employee.raise(new BigDecimal(10000.00))
  // 和下面这条语句效果相同
//  employee.raise(10000.00)
}

giveRaise new Employee()
giveRaise new Executive()
