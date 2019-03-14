package com.urban.billingapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket urbanApiSwaggerDocConfig()
    {
        return new Docket( DocumentationType.SWAGGER_2 )
            .securitySchemes( Collections.singletonList( userOAuthScheme() ) ).securityContexts( Collections.singletonList( securityContext() ) )
            .tags( new Tag( "User Entity", "HATEOAS User" ),
                new Tag( "Vendor Entity", "HATEOAS Vendor" ) )
            .apiInfo( new ApiInfo( "Urban Billing API", "REST API", "v001", null, null, null, null ) );
    }

    private OAuth userOAuthScheme() {
        List<AuthorizationScope> authorizationScopeList = new ArrayList<AuthorizationScope>();
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant("oauth/token");
        return new OAuth("oauth2", authorizationScopeList, Collections.singletonList( grantType ) );
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder().securityReferences(defaultAuth()).forPaths( PathSelectors.any()).build();
    }



    private List<SecurityReference> defaultAuth() {
        final AuthorizationScope[] authorizationScopes = new AuthorizationScope[0];
        return Collections.singletonList( new SecurityReference( "oauth2", authorizationScopes ) );
    }
}
