package com.atguigu.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

/**
 * Author: 牛耀辉
 * Date: 2024/11/20 20:19
 * Description: 控制层的配置类（controller，springmvc）
 * <p>
 * 1. controller
 * 2. 全局异常处理
 * 3. handlerMapping  handlerAdapter  ->  @EnableWebMvc
 * 4. 静态资源处理  ->  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer){}
 * 5. jsp 视图解析器前后缀  ->  public void configureViewResolvers(ViewResolverRegistry registry){}
 * 6. json转化器 ->  @EnableWebMvc
 * 7. 拦截器
 */
@Configuration
@ComponentScan({"com.atguigu.controller", "com.atguigu.exceptionhandler"})
@EnableWebMvc
public class WebMvcJavaConfig implements WebMvcConfigurer {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        registry.jsp("/WEB-INF/views/", ".jsp");
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // registry.addInterceptor(new x()).addPathPatterns().excludePathPatterns()
    }
}
