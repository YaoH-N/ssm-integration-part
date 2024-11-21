package com.atguigu.config;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 * Author: 牛耀辉
 * Date: 2024/11/20 21:25
 * Description: spring的初始化类
 */
public class SpringIoCInit extends AbstractAnnotationConfigDispatcherServletInitializer {

    // rootioc容器的配置类
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{DataSourceJavaConfig.class, MapperJavaConfigNew.class, ServiceJavaConfig.class};
    }

    //webioc容器的配置类
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{WebMvcJavaConfig.class};
    }

    //dispatcherServlet的拦截路径
    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }
}
