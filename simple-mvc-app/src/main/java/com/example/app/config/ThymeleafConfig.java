package com.example.app.config;

import com.example.ui.tag.MyDialect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.TemplateEngine;

/**
 * Created by naga on 15/04/02.
 */
@Configuration
public class ThymeleafConfig {

    @Autowired
    public TemplateEngine templateEngine;

    @Bean
    public TemplateEngine addDialect() {
        templateEngine.addDialect(
                new MyDialect(templateEngine.getConfiguration()));
        return templateEngine;
    }
}
