package com.jamie.apidoc.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 应用模块名称：Swagger2配置类
 *
 * @author xuanweiyao
 * @since 2019/11/25 13:48
 */
@Configuration
@EnableSwagger2
@EnableKnife4j
public class Swagger2 {
    /**
     * 配置 swagger2 核心配置 docket </br>
     * http://localhost:8080/swagger-ui.html </br>
     * http://localhost:8080/doc.html </br>
     *
     * @author xuanweiyao
     * @date 2019/11/25 13:59
     */
    @Bean
    public Docket createRestApi() {
        // 指定API类型为 swagger2
        return new Docket(DocumentationType.SWAGGER_2)
                // 不适用默认http状态码
                .useDefaultResponseMessages(false)
                // 定义API文档汇总信息
                .apiInfo(apiInfo())
                // 设置扫描包(controller)的信息
                .select().apis(RequestHandlerSelectors.basePackage("com.jamie.apidoc"))
                // 所有controller
                .paths(PathSelectors.any()).build();
    }

    private ApiInfo apiInfo() {

        return new ApiInfoBuilder()
                // 文档页标题
                .title("业扩接口文档")
                // 联系人信息
                .contact(new Contact("", "", ""))
                // 详细信息
                .description("掌厅微服务")
                // 文档版本号
                .version("1.0.0")
                // 网站信息
                .termsOfServiceUrl("").build();

    }
}
