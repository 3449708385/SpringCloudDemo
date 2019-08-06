package com.mgp.common;

import static com.netflix.config.ConfigurationManager.isConfigurationInstalled;

import java.io.IOException;

//import javax.sql.DataSource;

import com.netflix.config.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;

import com.netflix.config.jmx.ConfigJMXManager;
import com.netflix.config.sources.JDBCConfigurationSource;

@Component
@Qualifier("applicationConfig")
public class ApplicationConfig extends Thread {
    static {
        System.setProperty("archaius.configurationSource.defaultFileName", "config.properties");
        System.setProperty("archaius.fixedDelayPollingScheduler.initialDelayMills", "1000");
        System.setProperty("archaius.fixedDelayPollingScheduler.delayMills", "2000");
    }

    //private final DataSource dataSource;

   /* @Autowired
    public ApplicationConfig(DataSource dataSource) {
        this.dataSource = dataSource;
        cascadeDefaultConfiguration();
        DynamicConfiguration jdbcSource = installJdbcSource();
        registerMBean(jdbcSource);
    }*/

    public String getStringProperty(String key, String defaultValue) {
        final DynamicStringProperty property = DynamicPropertyFactory.getInstance().getStringProperty(key,
            defaultValue);
        return property.get();
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    private void registerMBean(DynamicConfiguration jdbcSource) {
        setDaemon(false);
        ConfigJMXManager.registerConfigMbean(jdbcSource);
    }

    private void cascadeDefaultConfiguration() {
        try {
            ConfigurationManager.loadCascadedPropertiesFromResources("config");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private DynamicConfiguration installJdbcSource() {
        if (!isConfigurationInstalled()) {
           /* PolledConfigurationSource source = new JDBCConfigurationSource(dataSource,
                "select distinct property_key, property_value from properties", "property_key", "property_value");*/
            PolledConfigurationSource source = new FillConfigurationSource();
            AbstractPollingScheduler scheduler = new FixedDelayPollingScheduler(2,2,false);
            DynamicConfiguration configuration = new DynamicConfiguration(source,scheduler);
            ConfigurationManager.install(configuration);
            return configuration;
        }
        return null;
    }

   /* public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("archaiusContext.xml");
        ApplicationConfig applicationConfig = (ApplicationConfig) applicationContext.getBean("applicationConfig");

        applicationConfig.start();

        while (true) {
            try {
                System.out.println(applicationConfig.getStringProperty("hello.world.message", "default message"));
                System.out.println(applicationConfig.getStringProperty("cascade.property", "default message"));
                System.out.println(applicationConfig.getStringProperty("db.property", "default message"));
                sleep(3000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }*/
}
