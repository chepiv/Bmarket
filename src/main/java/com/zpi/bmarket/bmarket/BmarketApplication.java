package com.zpi.bmarket.bmarket;

import com.zpi.bmarket.bmarket.services.ContentPathAccessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class BmarketApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(BmarketApplication.class, args);
    }


}

@Configuration
class StaticResourceConfiguration implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/files/**").addResourceLocations("file:///C:/Studia/Semestr 6/ZPI/");
//        registry.addResourceHandler("/files/**").addResourceLocations("file:///var/opt/oss/content");
        registry.addResourceHandler("/files/**").addResourceLocations(ContentPathAccessor.getFilesPath());
    }
}
