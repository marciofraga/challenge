package com.reclameaqui.challenge.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    
    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.reclameaqui.challenge.controller"))
        .paths(PathSelectors.any())
        .build()
        .globalResponseMessage(RequestMethod.GET, responseMessageGET())
        .globalResponseMessage(RequestMethod.POST, responseMessagePOST())
        .globalResponseMessage(RequestMethod.PUT, responseMessagePUT())
        .globalResponseMessage(RequestMethod.DELETE, responseMessageDELETE())
        .apiInfo(apiInfo());
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
        .code(204)
        .build());
        return  messages;
    }
}
