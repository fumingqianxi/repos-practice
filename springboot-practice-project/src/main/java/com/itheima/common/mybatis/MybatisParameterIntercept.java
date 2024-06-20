package com.itheima.common.mybatis;

import com.itheima.common.util.EncryptDecryptUtils;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.PreparedStatement;
import java.util.HashSet;
import java.util.Objects;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;

/**
 * 入参拦截器
 *
 * @author shibinbin
 * @date 2024/1/26 13:45
 */
@Intercepts({
        @Signature(type = ParameterHandler.class, method = "setParameters",
        args = PreparedStatement.class)
})
@ConditionalOnProperty(value = "domain.encrypt", havingValue = "true")
@Slf4j
public class MybatisParameterIntercept implements Interceptor {

  /**
   * 获取参数对象方法名称
   */
  private final static String GET_PARAMETER_OBJECT_METHOD = "getParameterObject";

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    //拦截 ParameterHandler 的 setParameters 方法 动态设置参数
    if (invocation.getTarget() instanceof ParameterHandler) {
      ParameterHandler parameterHandler = (ParameterHandler) invocation.getTarget();
      // 反射获取 参数方法
      Class<? extends ParameterHandler> aClass = parameterHandler.getClass();
      Method getParameterObject = aClass.getDeclaredMethod(GET_PARAMETER_OBJECT_METHOD);
      Object parameterObject = getParameterObject.invoke(parameterHandler);
      if (Objects.nonNull(parameterObject)) {
        //mybatis-plus 框架默认多参数
        if (parameterObject instanceof MapperMethod.ParamMap) {
          MapperMethod.ParamMap paramMap = (MapperMethod.ParamMap) parameterObject;
          //避免相同的key重复执行，使用set对象记录堆栈地址
          HashSet<Integer> stackSet = new HashSet<>();
          for (Object key : paramMap.keySet()) {
            Object value = paramMap.get(key);
            //需要使用hashcode判断，equals()方法可能被重写。
            if (null!= value && stackSet.add(System.identityHashCode(value))) {
              Field[] fields = value.getClass().getDeclaredFields();
              EncryptDecryptUtils.doEncrypt(fields, value);
            }
          }
        } else {
          Field[] fields = parameterObject.getClass().getDeclaredFields();
          EncryptDecryptUtils.doEncrypt(fields, parameterObject);
        }
      }
    }
    return invocation.proceed();
  }

  @Override
  public Object plugin(Object target) {
    return Plugin.wrap(target, this);
  }

  @Override
  public void setProperties(Properties properties) {

  }
}
