package com.mgp.configclient.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//这里面的属性有可能会更新的，git中的配置中心变化的话就要刷新，没有这个注解内，配置就不能及时更新
@RefreshScope
@RequestMapping("config")
public class ConfigController {

    @Value("${name}")
    private String name;
    @Value("${age}")
    private Integer age;

    @GetMapping("/getConfig")
    public String getConfig(){
        return this.name+this.age;
    }

}
