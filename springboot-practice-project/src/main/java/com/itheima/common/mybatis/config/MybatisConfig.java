package com.itheima.common.mybatis.config;

import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.itheima.common.mybatis.MybatisParameterIntercept;
import com.itheima.common.mybatis.MybatisResultSetIntercept;
import com.itheima.common.util.CheckEmptyUtil;
import com.itheima.common.util.EncryptDecryptUtils;
import com.itheima.common.util.TokenThreadUtil;
import java.util.Date;
import java.util.Properties;
import javax.sql.DataSource;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

/**
 * @author 蔡立杰
 * @since 2021/11/1 11:07
 */
@Configuration
public class MybatisConfig {

  @Autowired private ApplicationContext applicationContext;

  /**
   * 创建sqlSessionFactory.
   *
   * @param dataSource .
   * @return .
   * @throws Exception .
   */
  @Bean("sqlSessionFactory")
  public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
    MybatisConfiguration configuration = new MybatisConfiguration();
    // 设置下划线转驼峰
    configuration.setMapUnderscoreToCamelCase(true);
    // 设置日志实现
    configuration.setLogImpl(Slf4jImpl.class);
    // 设置本地缓存范围
    configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
    configuration.setCallSettersOnNulls(true);
    configuration.setCacheEnabled(false);
    // 设置全局配置
    GlobalConfig globalConfig = new GlobalConfig();
    globalConfig.setMetaObjectHandler(myMetaObjectHandle());
    globalConfig.setBanner(false);
    MybatisSqlSessionFactoryBean sqlSessionFactoryBean = new MybatisSqlSessionFactoryBean();
    sqlSessionFactoryBean.setGlobalConfig(globalConfig);
    sqlSessionFactoryBean.setConfiguration(configuration);
    sqlSessionFactoryBean.setDataSource(dataSource);
    ResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
    sqlSessionFactoryBean.setMapperLocations(
        resolver.getResources("classpath*:mapper/**/*Mapper.xml"));
    sqlSessionFactoryBean.setTypeAliasesPackage("com.haier.hip.mall.service.*");
    Interceptor[] plugins =
        new Interceptor[] {mybatisPlusInterceptor(), parameterIntercept(), resultSetIntercept()};
    sqlSessionFactoryBean.setPlugins(plugins);

    return sqlSessionFactoryBean.getObject();
  }

  @Bean
  public MybatisPlusInterceptor mybatisPlusInterceptor() {
    MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
    interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
    return interceptor;
  }

  @Bean
  public MetaObjectHandler myMetaObjectHandle() {
    MetaObjectHandler myMetaObjectHandle =
        new MetaObjectHandler() {
          @Override
          public void insertFill(MetaObject metaObject) {
            metaObject.setValue("createTime",
                                CheckEmptyUtil.isNotEmpty(metaObject.getValue("createTime"))
                    ? metaObject.getValue("createTime")
                    : new Date());
            metaObject.setValue("updateTime", new Date());
            metaObject.setValue(
                "createUserCode",
                CheckEmptyUtil.isNotEmpty(metaObject.getValue("createUserCode"))
                    ? metaObject.getValue("createUserCode")
                    : TokenThreadUtil.getUserCode());
            metaObject.setValue(
                "createUserName",
                CheckEmptyUtil.isNotEmpty(metaObject.getValue("createUserName"))
                    ? metaObject.getValue("createUserName")
                    : TokenThreadUtil.getUserName());
            metaObject.setValue(
                "updateUserCode",
                CheckEmptyUtil.isNotEmpty(metaObject.getValue("createUserCode"))
                    ? metaObject.getValue("createUserCode")
                    : TokenThreadUtil.getUserCode());
            metaObject.setValue(
                "updateUserName",
                CheckEmptyUtil.isNotEmpty(metaObject.getValue("createUserName"))
                    ? metaObject.getValue("createUserName")
                    : TokenThreadUtil.getUserName());
            metaObject.setValue("version", 1);
          }

          @Override
          public void updateFill(MetaObject metaObject) {
            metaObject.setValue("updateTime", new Date());
            metaObject.setValue(
                "updateUserCode",
                CheckEmptyUtil.isNotEmpty(metaObject.getValue("updateUserCode"))
                    ? metaObject.getValue("updateUserCode")
                    : TokenThreadUtil.getUserCode());
            metaObject.setValue(
                "updateUserName",
                CheckEmptyUtil.isNotEmpty(metaObject.getValue("updateUserName"))
                    ? metaObject.getValue("updateUserName")
                    : TokenThreadUtil.getUserName());
          }
        };
    return myMetaObjectHandle;
  }

//  /**
//   * 分页拦截器.
//   *
//   * @return .
//   */
//  @Bean
//  public PageInterceptor pageInterceptor() {
//    Properties properties = new Properties();
//    properties.put("helperDialect", "mysql");
//    properties.put("offsetAsPageNum", "true");
//    properties.put("rowBoundsWithCount", "true");
//    properties.put("reasonable", "false");
//    properties.put("supportMethodsArguments", "true");
//    PageInterceptor pageInterceptor = new PageInterceptor();
//    pageInterceptor.setProperties(properties);
//    return pageInterceptor;
//  }

  @Bean
  @ConditionalOnMissingBean(name = "encryptDecryptUtils")
  public EncryptDecryptUtils encryptDecryptUtils() {
    return new EncryptDecryptUtils();
  }

  @Bean
  public MybatisParameterIntercept parameterIntercept() {
    return new MybatisParameterIntercept();
  }

  @Bean
  public MybatisResultSetIntercept resultSetIntercept() {
    return new MybatisResultSetIntercept();
  }
}
