package com.itheima.springinaction.AOP.基于注解的AOP.aspect;

import com.itheima.springinaction.AOP.基于注解的AOP.通过注解AOP添加方法.DefaultEncoreable;
import com.itheima.springinaction.AOP.基于注解的AOP.通过注解AOP添加方法.Encoreable;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;

/**
 * @author 胡磊
 * @since 2023/4/28 14:19
 */
@Aspect
public class EncoreableIntroducer {

  /**
   * @DeclareParents 所标注的静态属性指明了要引入的接口
   *
   * 将接口Encoreable引入到Performance的bean中
   * 1.value指定哪种类型的bean要引入改接口，本例中即实现了Performance的类型（“+”表示Performance的所有子类，而不是其本身）
   * 2.defaultImpl表示为被引入接口的实现类
   */
  @DeclareParents(value = "com.itheima.springinaction.AOP.model.Performance+",
      defaultImpl = DefaultEncoreable.class)
  public static Encoreable encoreable;
}
