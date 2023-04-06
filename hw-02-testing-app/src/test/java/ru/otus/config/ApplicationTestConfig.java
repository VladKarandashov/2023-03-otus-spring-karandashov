package ru.otus.config;

import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@PropertySource("classpath:applicationTest.properties")
public class ApplicationTestConfig {
}
