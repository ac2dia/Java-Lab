package io.springfox.springfoxswagger2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;


@Configuration
@EnableSwagger2WebMvc
@Import(BeanValidatorPluginsConfiguration.class)
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean // 필수항목
    public Docket customImplementation() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)  // 기존적인 응답메시지 미사용
                .apiInfo(getApiInfo())
                .select()                   // return ApiSelectoorBuilder(화면 관리)
                .apis(RequestHandlerSelectors.basePackage("io.springfox")) // Swagger를 적용할 패키지
                .paths(PathSelectors.any()) // url path 지정(예를들면 PathSelectors.ant("/home/**")인 경우 /home/ path를 가진 url만 공개하겠다는 의미. any인 경우 전체 url
                .build();                   // selector build
    }

    // 선택항목(Swagger UI에서 보여지는 정보)
    public ApiInfo getApiInfo() {
        return new ApiInfoBuilder()
                .title("Service REST API Documentation")
                .description("REST Api Documentation")
                .version("1.0")
                .license("Apache 2.0")
                .build();
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
}

