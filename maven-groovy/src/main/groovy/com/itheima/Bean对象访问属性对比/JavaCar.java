package com.itheima.Bean对象访问属性对比;

/**
 * @author 胡磊
 * @since 2022/12/2 16:45
 */
public class JavaCar {
  private int miles;
  private final int year;

  public JavaCar(int theYear) { year = theYear; }
  public int getMiles() { return miles; }
  public void setMiles(int theMiles) { miles = theMiles; }

  public int getYear() { return year; }

  public static void main(String[] args) {
    JavaCar car = new JavaCar(2008);

    System.out.println("Year: " + car.getYear());
    System.out.println("Miles: " + car.getMiles());
    System.out.println("Setting miles");
    car.setMiles(25);
    System.out.println("Miles: " + car.getMiles());
  }
}
