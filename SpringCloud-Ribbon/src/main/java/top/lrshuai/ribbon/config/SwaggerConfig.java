package top.lrshuai.ribbon.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig implements WebMvcConfigurer {
    @Bean
    public Docket ProductApi() {
        List<Parameter> aParameters = new ArrayList<Parameter>();
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(productApiInfo())
                .groupName("v1").select()
                .apis(RequestHandlerSelectors.basePackage("top.lrshuai"))
//                .paths(PathSelectors.ant("/mail/**"))
                .build().globalOperationParameters(aParameters);
    }

    private ApiInfo productApiInfo() {
        ApiInfo apiInfo = new ApiInfoBuilder()
                .title("接口文档")
                .version("1.0.0")
                .build();
        return apiInfo;
    }



     /**
     * 防止@EnableMvc把默认的静态资源路径覆盖了，手动设置的方式
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
        registry.addResourceHandler("/**")
                .addResourceLocations("classpath:/static/");
    }
}
