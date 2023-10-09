package controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class StaticResourceConfiguration implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/content/**").addResourceLocations("file:///C://Users//ASUS//Downloads//SchoolManagementSystemWithReactAndSpringBoot-main//SchoolManagementSystemWithReactAndSpringBoot-main//backend//uploads/");
    }
}
