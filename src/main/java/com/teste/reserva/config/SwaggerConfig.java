package com.teste.reserva.config;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;


import static springfox.documentation.builders.PathSelectors.regex;

import java.util.ArrayList;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
    public Docket hospedeApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()								   
                .apis(RequestHandlerSelectors.basePackage("com.teste.reserva.controller"))
                //.paths()
                .build()
                .apiInfo(metaInfo());
    }

 

	private ApiInfo metaInfo() {

        ApiInfo apiInfo = new ApiInfo(
                "Cadastro de hospedes API ",
                "API  de cadastro  de hospedes.",
                "1.0",
                "Terms of Service",
                new Contact("eusoubrunohorn@gmail.com","Bruno Horn Jr","https://www.linkedin.com/in/bruno-horn-552b84181/"),
                "Apache License Version 2.0",
                "https://www.apache.org/licesen.html", new ArrayList<VendorExtension>()
        );

        return apiInfo;
    }	

}
