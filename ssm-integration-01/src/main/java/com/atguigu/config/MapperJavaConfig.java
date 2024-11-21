package com.atguigu.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

/**
 * Author: 牛耀辉
 * Date: 2024/11/20 20:43
 * Description: 持久层配置类：连接池，sqlSessionFactory，Mapper代理对象
 *
 * 方式1：保留外部配置文件
 * TODO: 如果将datasource和mybatis的组件配置到一起，触发@Value注解不生效的问题
 *      原因：mybatis的组件优先加载，@Value还没有读取
 *      解决：分开配置，写道不同的配置类即可
 */
@Configuration
public class MapperJavaConfig {

    // sqlSessionFactory 加入ioc容器
    // mybatis -> sqlSessionFactoryBean [ioc] -> getObject() -> sqlSessionFactory
    // 方式1：外部指定mybatis-config.xml [mybatis的配置 除了 连接池,mapper指定]
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 指定配置文件等信息
        // 指定数据库连接池对象
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 指定外部的mybatis配置文件
        Resource resource = new ClassPathResource("mybatis-config.xml");
        sqlSessionFactoryBean.setConfigLocation(resource);

        return sqlSessionFactoryBean;
    }


    // mapper代理对象加入到ioc容器
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        // Mapper代理对象的factoryBean -> 指定一个包 -> mapper接口  -> sqlSessionFactory -> sqlSession -> getMapper -> mapper代理对象 -> ioc
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setBasePackage("com.atguigu.mapper"); //mapper接口和mapperxml所在的共同包
        return mapperScannerConfigurer;
    }

}
