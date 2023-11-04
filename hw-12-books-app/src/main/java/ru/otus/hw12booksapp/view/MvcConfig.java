package ru.otus.hw12booksapp.view;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/admin/library").setViewName("adminArea/editBooks");
        registry.addViewController("/client/library").setViewName("clientArea/listBooks");
        registry.addViewController("/anonymous").setViewName("anonymous");
    }
}