/*
package com.mgp.common;

import com.netflix.config.DynamicConfiguration;
import com.netflix.config.FixedDelayPollingScheduler;
import com.netflix.config.PolledConfigurationSource;
import com.netflix.config.sources.URLConfigurationSource;
import org.apache.commons.configuration.AbstractConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

*/
/**
 * 多配置文件：作用被 @next=secondConfig.properties 替换
 *//*

@Configuration
public class ApplicationPropertiesConfigurations {
    @Bean
    public AbstractConfiguration addApplicationPropertiesSource() {
        PolledConfigurationSource source = new URLConfigurationSource("classpath:other-config.properties");
        return new DynamicConfiguration(source, new FixedDelayPollingScheduler());
    }
}
*/
