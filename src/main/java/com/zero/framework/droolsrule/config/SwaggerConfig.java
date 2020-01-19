package com.zero.framework.droolsrule.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig {
    /**
     * 创建一个Docket对象
     * 调用select()方法，
     * 生成ApiSelectorBuilder对象实例，该对象负责定义外漏的API入口
     * 通过使用RequestHandlerSelectors和PathSelectors来提供Predicate，在此我们使用any()方法，将所有API都通过Swagger进行文档管理
     *
     * @return
     */
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.zero.framework.droolsrule.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        //标题
                        .title("SpringBoot2.x整合Swagger2 构建api文档")
                        //简介
                        .description("简单优雅的restfun风格，详细信息......")
                        //版本
                        .version("9.0")
                        //作者个人信息
//                        .contact(new Contact("青", "https://gblfy.com", "zero_develop@163.com"))
                        .license("The Apache License")
                        //服务条款
                        .build());
    }
}


