package com.et.auditServer.config;

import com.et.auditServer.modules.sys.Interceptor.URLHandlerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class WebAppConfigurer implements WebMvcConfigurer {

//    @Autowired
//    URLHandlerInterceptor urlHandlerInterceptor;



    @Bean
    public URLHandlerInterceptor urlHandlerInterceptor() {
        return new URLHandlerInterceptor();
    }



    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录拦截的管理器
        InterceptorRegistration registration = registry.addInterceptor(urlHandlerInterceptor());     //拦截的对象会进入这个类中进行判断
        registration.addPathPatterns("/**");                    //所有路径都被拦截
        registration.excludePathPatterns("/","/login","/ajaxLogin","/error","/static/**",
                "/captcha","/swagger-ui.html", "/webjars/**", "/swagger-resources/**",
                "/checkPortalLtpaToken2", "/platform/error/checkHttp","/checkPing");       //添加不拦截路径
//        super.addInterceptors(registry);

    }

    /*@Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowCredentials(true)
                .allowedMethods("GET", "POST", "DELETE", "PUT")
                .maxAge(3600);
    }*/

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //需要配置1：----------- 需要告知系统，这是要被当成静态文件的！
        //第一个方法设置访问路径前缀，第二个方法设置资源路径
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/static/");
    }

}
