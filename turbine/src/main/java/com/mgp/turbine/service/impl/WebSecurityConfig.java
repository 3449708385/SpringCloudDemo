/*
package com.mgp.turbine.service.impl;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    public void configure(WebSecurity web) throws Exception {
        super.configure(web);
        web.ignoring().antMatchers("/hystrix.stream", "turbine.stream");
        // spring.secruity.ignored:
        // /hystrix.stream
        // /turbine.stream
    }

    @Resource
    public void configGloabl(AuthenticationManagerBuilder auth)throws Exception{
        auth.inMemoryAuthentication().withUser("wendy").password("wendy").roles("USER")
                .and().withUser("admin").password("hello").roles("USER", "ADMIN");

    }

	*/
/*security:
		  sessions: stateless
		  basic:
		    enabled: true #启用SpringSecurity的安全配置
		  user:
		     name: zemel #认证用户名
		     password: 123456 # 认证密码
		     role:   # 授权角色
		     - USER *//*


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 表示所有的访问都必须认证，认证处理后才可以正常进行
        http.httpBasic().and().authorizeRequests().anyRequest().fullyAuthenticated();
        // 所有的rest服务一定要设置为无状态，以提升操作效率和性能
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

}
*/
