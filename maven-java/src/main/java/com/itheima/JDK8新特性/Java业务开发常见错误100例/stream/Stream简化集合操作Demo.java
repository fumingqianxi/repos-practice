package com.itheima.JDK8新特性.Java业务开发常见错误100例.stream;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;

import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;
import org.junit.Test;

/**
 * @author 胡磊
 * @since 2022/8/5 16:29
 */
public class Stream简化集合操作Demo {

  public static void main(String[] args) {
    List<Integer> ints = new ArrayList<>();
    ints.add(5);
    ints.add(6);
    ints.add(9);
    System.out.println(calc(ints) == calc2(ints));
  }

  private static double calc(List<Integer> ints) {
    //临时中间集合
    List<Point2D> point2DList = new ArrayList<>();
    for (Integer i : ints) {
      point2DList.add(new Point2D.Double((double) i % 3, (double) i / 3));
    }
    //临时变量，纯粹是为了获得最后结果需要的中间变量
    double total = 0;
    int count = 0;

    for (Point2D point2D : point2DList) {
      //过滤
      if (point2D.getY() > 1) {
        //算距离
        double distance = point2D.distance(0, 0);
        total += distance;
        count++;
      }
    }
    //注意count可能为0的可能
    return count >0 ? total / count : 0;
  }

  private static double calc2(List<Integer> ints) {
    return ints.stream().
        map(i -> new Point2D.Double((double) i % 3, (double) i / 3))
        .filter(point -> point.getY() > 1)
        .mapToDouble(point -> point.distance(0, 0))
        .average()
        .orElse(0);
  }


  @Test(expected = IllegalArgumentException.class)
  public void optional() {
    //通过get方法获取Optional中的实际值
    assertThat(Optional.of(1).get(), is(1));
    //通过ofNullable来初始化一个null，通过orElse方法实现Optional中无数据的时候返回一个默认值
    assertThat(Optional.ofNullable(null).orElse("A"), is("A"));
    //OptionalDouble是基本类型double的Optional对象，isPresent判断有无数据
    assertFalse(OptionalDouble.empty().isPresent());
    //通过map方法可以对Optional对象进行级联转换，不会出现空指针，转换后还是一个Optional
    assertThat(Optional.of(1).map(Math::incrementExact).get(), is(2));
    //通过filter实现Optional中数据的过滤，得到一个Optional，然后级联使用orElse提供默认值
    assertThat(Optional.of(1).filter(integer -> integer % 2 == 0).orElse(null), is(nullValue()));
    //通过orElseThrow实现无数据时抛出异常
    Optional.empty().orElseThrow(IllegalArgumentException::new);
  }
}
