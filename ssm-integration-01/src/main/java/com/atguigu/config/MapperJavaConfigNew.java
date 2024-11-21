package com.atguigu.config;

import com.alibaba.druid.support.logging.SLF4JImpl;
import com.github.pagehelper.PageInterceptor;
import org.apache.ibatis.logging.slf4j.Slf4jImpl;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Author: 牛耀辉
 * Date: 2024/11/20 20:43
 * Description: 持久层配置类：连接池，sqlSessionFactory，Mapper代理对象
 * <p>
 * 方式2：不保留外部配置文件，全部mybatis的属性都在代码中设置
 * TODO: 如果将datasource和mybatis的组件配置到一起，触发@Value注解不生效的问题
 *      原因：mybatis的组件优先加载，@Value还没有读取
 *      解决：分开配置，写道不同的配置类即可
 */
@Configuration
public class MapperJavaConfigNew {

    // sqlSessionFactory 加入ioc容器
    // mybatis -> sqlSessionFactoryBean [ioc] -> getObject() -> sqlSessionFactory
    // 方式1：外部指定mybatis-config.xml [mybatis的配置 除了 连接池,mapper指定]
    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource) {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();

        // 指定配置文件等信息
        // 指定数据库连接池对象
        sqlSessionFactoryBean.setDataSource(dataSource);
        // 指定mybatis配置文件的功能，使用代码的形式
//        Resource resource = new ClassPathResource("mybatis-config.xml");
//        sqlSessionFactoryBean.setConfigLocation(resource);
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);  // 开启驼峰式命名
        configuration.setLogImpl(Slf4jImpl.class); // 配置日志功能
        configuration.setAutoMappingBehavior(AutoMappingBehavior.FULL);  // 开启resultMap多层嵌套结果自动映射

        sqlSessionFactoryBean.setConfiguration(configuration);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.atguigu.pojo");
        // 插件
        PageInterceptor pageInterceptor = new PageInterceptor();
        Properties properties = new Properties();
        properties.setProperty("helperDialect", "mysql");
        pageInterceptor.setProperties(properties);
        sqlSessionFactoryBean.addPlugins(pageInterceptor);

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
