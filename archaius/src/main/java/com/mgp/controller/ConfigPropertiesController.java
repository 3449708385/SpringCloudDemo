package com.mgp.controller;

import com.mgp.common.ApplicationConfig;
import com.netflix.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ConfigPropertiesController {
    @Autowired(required = false)@Qualifier("applicationConfig")
    private ApplicationConfig applicationConfig;

    @GetMapping("/properties-from-dynamic")
    public Map<String, String> getPropertiesFromDynamic() {
        Map<String, String> properties = new HashMap<>();
        properties.put("springcloud.archaius.properties.one", applicationConfig.getStringProperty("springcloud.archaius.properties.one",""));
        properties.put("springcloud.archaius.properties.two", applicationConfig.getStringProperty("springcloud.archaius.properties.two",""));
        properties.put("springcloud.archaius.properties.three", applicationConfig.getStringProperty("springcloud.archaius.properties.three",""));
        properties.put("springcloud.archaius.properties.four", applicationConfig.getStringProperty("springcloud.archaius.properties.four",""));
        properties.put("springcloud.archaius.properties.five", applicationConfig.getStringProperty("springcloud.archaius.properties.five",""));
        return properties;
    }
}
