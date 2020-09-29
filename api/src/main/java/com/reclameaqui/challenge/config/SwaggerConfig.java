package com.reclameaqui.challenge.config;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.service.Parameter;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.AuthorizationCodeGrantBuilder;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.service.TokenEndpoint;
import springfox.documentation.service.TokenRequestEndpoint;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SecurityConfiguration;
import springfox.documentation.swagger.web.SecurityConfigurationBuilder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Value("${keycloak.auth-server-url}")
    private String authServer;
    @Value("${keycloak.realm}")
    private String realm;
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    @Bean
    public Docket api() {
        Parameter parameter = new ParameterBuilder()
            .name("autorization")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();
        
            return new Docket(DocumentationType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.reclameaqui.challenge.controller"))
            .paths(PathSelectors.any())
            .build()
            .globalResponseMessage(RequestMethod.GET, responseMessageGET())
            .globalResponseMessage(RequestMethod.POST, responseMessagePOST())
            .globalResponseMessage(RequestMethod.PUT, responseMessagePUT())
            .globalResponseMessage(RequestMethod.DELETE, responseMessageDELETE())
            .apiInfo(apiInfo())
            .securitySchemes(Arrays.asList(securitySchemes()))
            .securityContexts(Arrays.asList(securityContext()))
            .globalOperationParameters(Arrays.asList(parameter));
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        .title("Complaint Rest API")
        .description("service to ingest complaints and get some data about its geolocation.")
        .version("0.1")
        .build();
    }

    private List<ResponseMessage> responseMessageGET() {
        List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
        messages.add(new ResponseMessageBuilder()   
        .code(500)
        .responseModel(new ModelRef("Error"))
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(404)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(401)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(200)
        .build());
        return  messages;
    }

    private List<ResponseMessage> responseMessagePOST() {
        List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
        messages.add(new ResponseMessageBuilder()   
        .code(500)
        .responseModel(new ModelRef("Error"))
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(422)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(401)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(201)
        .build());
        return  messages;
    }

    private List<ResponseMessage> responseMessagePUT() {
        List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
        messages.add(new ResponseMessageBuilder()   
        .code(500)
        .responseModel(new ModelRef("Error"))
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(422)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(401)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(200)
        .build());
        return  messages;
    }

    private List<ResponseMessage> responseMessageDELETE() {
        List<ResponseMessage> messages = new ArrayList<ResponseMessage>();
        messages.add(new ResponseMessageBuilder()   
        .code(500)
        .responseModel(new ModelRef("Error"))
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(404)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(401)
        .build());
        messages.add(new ResponseMessageBuilder() 
        .code(204)
        .build());
        return  messages;
    }

    private SecurityScheme securitySchemes() {
        GrantType grantType = new AuthorizationCodeGrantBuilder()
            .tokenEndpoint(new TokenEndpoint(authServer + "/realms/" + realm + "/protocol/openid-connect/token", "challenge-api"))
            .tokenRequestEndpoint(
                new TokenRequestEndpoint(authServer + "/realms/" + realm + "/protocol/openid-connect/auth", "challenge-api", clientSecret)
            ).build();

        SecurityScheme oauth = new OAuthBuilder().name("oauth2")
            .grantTypes(Arrays.asList(grantType))
            .scopes(Arrays.asList(scopes()))
            .build();
        return oauth;
    }

    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = { 
          new AuthorizationScope("openid", "for enable operations")};
        return scopes;
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.ant("/private/**"))
            .forPaths(PathSelectors.regex("Authorization"))
            .build();
    }

    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    @Bean
    public SecurityConfiguration security() {
        return SecurityConfigurationBuilder.builder()
            .realm(realm)
            .clientId("challenge-api")
            .clientSecret(clientSecret)     
            .scopeSeparator(",")
            .useBasicAuthenticationWithAccessCodeGrant(false)
            .build();
    }
}
