package com.shareduck.shareduck.common.config;

import static org.springframework.security.config.Elements.JWT;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI(@Value("${springdoc.version}") String appVersion) {

        Info info = new Info()
            .title("ShareDuck Server Swagger")
            .version(appVersion)
            .description("[USER] : 사용자 권한 User <br> [ALL] 비로그인 사용자");

        String securitySchemeName = HttpHeaders.AUTHORIZATION;
        SecurityRequirement securityRequirement = new SecurityRequirement().addList(securitySchemeName);
        Components components = new Components()
            .addSecuritySchemes(securitySchemeName, new SecurityScheme()
                .name(securitySchemeName)
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat(JWT));

        return new OpenAPI()
            .info(info)
            .addSecurityItem(securityRequirement)
            .components(components);
    }

    @Bean
    public GroupedOpenApi apiGroup() {
        String[] pathToMatch = {
            "/api/v1/**",
            "/api/**"
        };
        String[] pathToExclude = {};
        return GroupedOpenApi.builder()
            .group("All")
            .pathsToMatch(pathToMatch)
            .pathsToExclude(pathToExclude)
            .build();
    }

    @Bean
    public GroupedOpenApi apiUserGroup() {
        String[] pathToMatch = {
            "/api/v1/users/**"
        };
        String[] pathToExclude = {};
        return GroupedOpenApi.builder()
            .group("_USER")
            .pathsToMatch(pathToMatch)
            .pathsToExclude(pathToExclude)
            .build();
    }

    @Bean
    public GroupedOpenApi apiCategoryGroup() {
        String[] pathToMatch = {
            "/api/categories/**"
        };
        String[] pathToExclude = {};
        return GroupedOpenApi.builder()
            .group("_CATEGORY")
            .pathsToMatch(pathToMatch)
            .pathsToExclude(pathToExclude)
            .build();
    }

    @Bean
    public GroupedOpenApi apiMemoGroup() {
        String[] pathToMatch = {
            "/api/memos/**"
        };
        String[] pathToExclude = {};
        return GroupedOpenApi.builder()
            .group("_MEMO")
            .pathsToMatch(pathToMatch)
            .pathsToExclude(pathToExclude)
            .build();
    }

    @Bean
    public GroupedOpenApi apiPostGroup() {
        String[] pathToMatch = {
            "/api/posts/**"
        };
        String[] pathToExclude = {};
        return GroupedOpenApi.builder()
            .group("_POST")
            .pathsToMatch(pathToMatch)
            .pathsToExclude(pathToExclude)
            .build();
    }
    @Bean
    public GroupedOpenApi apiUploadGroup() {
        String[] pathToMatch = {
            "/api/upload/**"
        };
        String[] pathToExclude = {};
        return GroupedOpenApi.builder()
            .group("_UPLOAD")
            .pathsToMatch(pathToMatch)
            .pathsToExclude(pathToExclude)
            .build();
    }

}
