package com.example.app.conf;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import javax.validation.constraints.NotNull;

/**
 * Created by naga on 2015/01/30.
 */
@Configuration
@ConfigurationProperties(prefix="rel")
public class RelaxedConfig {

    @NotNull
    private String unscoVal;

    private String name;

    public String getUnscoVal() {
        return unscoVal;
    }

    public String getName() {
        return name;
    }

    public void setUnscoVal(String unscoVal) {
        this.unscoVal = unscoVal;
    }

    public void setName(String name) {
        this.name = name;
    }
}
