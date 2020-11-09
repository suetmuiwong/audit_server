package com.et.auditServer.config;

import io.swagger.annotations.ApiOperation;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.List;

import static com.google.common.collect.Lists.newArrayList;

@Configuration
@EnableSwagger2
@Profile({"dev","test"})
public class SwaggerConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/swagger/**").addResourceLocations("classpath:/static/swagger/");
    }

    @Bean
    public Docket createRestApi() {
        //添加head参数start
        /*List<Parameter> pars = new ArrayList<Parameter>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("Authorization")
                .description("令牌")
                .modelRef(new ModelRef("string"))
                .parameterType("header")
                .required(true)
                .build();*/

//        ParameterBuilder userIdPar = new ParameterBuilder();
//        userIdPar.name("userId")
//                .description("用户ID")
//                .modelRef(new ModelRef("string"))
//                .parameterType("header")
//                .required(false)
//                .build();

        //pars.add(tokenPar.build());
//        pars.add(userIdPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                //.apis(RequestHandlerSelectors.basePackage("io.renren.controller"))
                .paths(PathSelectors.any())
                .build()
               // .globalOperationParameters(pars)
                .securitySchemes(security());
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("hxm", null, null);
        return new ApiInfoBuilder()
                .title("audit-server")
                .description("audit-server文档")
                .contact(contact)
                .version("1.0")
                .build();
    }

    private List<ApiKey> security() {
        return newArrayList(
                new ApiKey("Authorization", "Authorization", "header")
        );
    }

}
