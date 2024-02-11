package com.itheima.commonmistakes.a27clientdata.trustclientuserid;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface LoginRequired {
  String sessionKey() default "currentUser";
}