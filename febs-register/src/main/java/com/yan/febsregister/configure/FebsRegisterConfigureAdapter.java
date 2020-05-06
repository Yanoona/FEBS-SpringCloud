package com.yan.febsregister.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsregister.configure
 * @Author: Yan
 * @CreateTime: 2020-04-26 15:15
 * @Description: 该配置类用于开启Eureka服务端端点保护。
 */
@EnableWebSecurity
public class FebsRegisterConfigureAdapter extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().ignoringAntMatchers("/eureka/**")
                .and()
                .authorizeRequests().antMatchers("/actuator/**").permitAll();
        super.configure(httpSecurity);
    }
}
