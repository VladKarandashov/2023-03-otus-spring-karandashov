package ru.otus.hw03testingapp.props;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;

@Data
@ConfigurationProperties(prefix = "application")
public class ApplicationProps {

    private final Locale locale;

}
