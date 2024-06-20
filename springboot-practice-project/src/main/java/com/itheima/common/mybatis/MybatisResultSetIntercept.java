package com.itheima.common.mybatis;

import com.itheima.common.util.CheckEmptyUtil;
import com.itheima.common.util.EncryptDecryptUtils;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.executor.resultset.ResultSetHandler;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * 返回数据拦截器
 *
 * @author shibinbin
 * @date 2024/1/26 13:45
 */
@Intercepts({
    @Signature(type = ResultSetHandler.class, method = "handleResultSets", args = Statement.class)
})
@ConditionalOnProperty(value = "domain.encrypt", havingValue = "true")
@Component
@Slf4j
public class MybatisResultSetIntercept implements Interceptor {

  @Override
  public Object intercept(Invocation invocation) throws Throwable {
    Object result = invocation.proceed();
    if (CheckEmptyUtil.isEmpty(result)) {
      return result;
    }
    if (result instanceof ArrayList) {
      ArrayList resultList = (ArrayList) result;
      if (CheckEmptyUtil.isNotEmpty(resultList)) {
        for (Object obj : resultList) {
          EncryptDecryptUtils.doDecrypt(obj);
        }
      }
    } else {
      EncryptDecryptUtils.doDecrypt(result);
    }
    return result;
  }

  @Override
  public Object plugin(Object o) {
    return Plugin.wrap(o, this);
  }

  @Override
  public void setProperties(Properties properties) {

  }
}
