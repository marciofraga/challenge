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

/** represent enable and configurations about swagger ui doc. */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    /** field that recover authserver keycloak from application.yml */
    @Value("${keycloak.auth-server-url}")
    private String authServer;
    /** field that recover realm keycloak from application.yml */
    @Value("${keycloak.realm}")
    private String realm;
    /** field that recover clientsecret keycloak from application.yml */
    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    /**
     * method to define a bean api swagger
     * @return
     */
    @Bean
    public Docket api() {
        // parameter field that include a input field on swagger ui to put on authorization token
        Parameter parameter = new ParameterBuilder()
            .name("autorization")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();
        
            return new Docket(DocumentationType.SWAGGER_2)
                .select()
                //define package where find all controllers
                .apis(RequestHandlerSelectors.basePackage("com.reclameaqui.challenge.controller"))
                .paths(PathSelectors.any())
                .build()
                //define possible responses of api for GET request
                .globalResponseMessage(RequestMethod.GET, responseMessageGET())
                //define possible responses of api for POST request
                .globalResponseMessage(RequestMethod.POST, responseMessagePOST())
                //define possible responses of api for PUT request
                .globalResponseMessage(RequestMethod.PUT, responseMessagePUT())
                //define possible responses of api for DELETE request
                .globalResponseMessage(RequestMethod.DELETE, responseMessageDELETE())
                //define title, description and version rest API
                .apiInfo(apiInfo())
                //define an strategy security to access private endpoints
                .securitySchemes(Arrays.asList(securitySchemes()))
                .securityContexts(Arrays.asList(securityContext()))
                //applying field authorization on endpoints
                .globalOperationParameters(Arrays.asList(parameter));
    }

    /**
     * method to document a title, description and version of rest API
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
        .title("Complaint Rest API")
        .description("service to ingest complaints and get some data about its geolocation.")
        .version("0.1")
        .build();
    }

    /**
     * method to define all possible HTTP responses for request GET
     * @return
     */
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

    /**
     * method to define all possible HTTP responses for request POST
     * @return
     */
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

    /**
     * method to define all possible HTTP responses for request PUT
     * @return
     */
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

    /**
     * method to define all possible HTTP responses for request DELETE
     * @return
     */
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

    /**
     * method to define endpoint to authentication on keycloak using oauth scheme.
     * In case first access, the user will redirect for authentication keycloak page.
     * In another cases, the swagger only will refresh authorization token.
     * @return
     */
    private SecurityScheme securitySchemes() {
        //define endpoint to authentication and refresh token with authserver, realm and client id
        GrantType grantType = new AuthorizationCodeGrantBuilder()
            .tokenEndpoint(new TokenEndpoint(authServer + "/realms/" + realm + "/protocol/openid-connect/token", "challenge-api"))
            .tokenRequestEndpoint(
                new TokenRequestEndpoint(authServer + "/realms/" + realm + "/protocol/openid-connect/auth", "challenge-api", clientSecret)
            ).build();

        // applying oauth stategy
        SecurityScheme oauth = new OAuthBuilder().name("oauth2")
            .grantTypes(Arrays.asList(grantType))
            .scopes(Arrays.asList(scopes()))
            .build();
        return oauth;
    }

    /**
     * method to define authorization scope to 'openid'
     * @return
     */
    private AuthorizationScope[] scopes() {
        AuthorizationScope[] scopes = { 
          new AuthorizationScope("openid", "for enable operations")};
        return scopes;
    }

    /**
     * method to applying security context for private endpoints
     */
    private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.ant("/private/**"))
            .forPaths(PathSelectors.regex("Authorization"))
            .build();
    }

    /**
     * method to define a authorization scope to 'global'
     * @return
     */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[]{authorizationScope};
        return Arrays.asList(new SecurityReference("Authorization", authorizationScopes));
    }

    /**
     * method to define a security bean used by swagger
     * @return
     */
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
