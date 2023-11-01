package com.itheima.Bean对象访问属性对比

class Car {
  def miles = 0
  final year

  Car(theYear) { year = theYear }
}

JavaCar car = new JavaCar(2008)

println "Year: $car.year"
println "Miles: $car.miles"
println 'Setting miles'
car.miles = 25
println "Miles: $car.miles"
