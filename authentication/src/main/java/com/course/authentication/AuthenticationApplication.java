package com.course.authentication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
public class AuthenticationApplication {

  /*  public static String  tempPath="D://upfile/";
    @Bean
    MultipartConfigElement multipartConfigElement(){
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setLocation(tempPath);
        return factory.createMultipartConfig();

    }*/


    public static void main(String[] args) {

        SpringApplication.run(AuthenticationApplication.class, args);
    }

}
