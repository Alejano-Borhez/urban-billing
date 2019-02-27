package com.urban.billingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket urbanApiSwaggerDocConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .tags(new Tag("User Entity", "HATEOAS User"),
                        new Tag("Vendor Entity", "HATEOAS Vendor"))
                .apiInfo(new ApiInfo("Urban Billing API", "REST API", "v001", null, null, null, null));
    }
}
