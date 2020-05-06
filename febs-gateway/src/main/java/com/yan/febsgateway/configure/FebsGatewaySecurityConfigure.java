package com.yan.febsgateway.configure;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsgateway.configure
 * @Author: Yan
 * @CreateTime: 2020-04-27 09:42
 * @Description:
 */
@EnableWebSecurity
public class FebsGatewaySecurityConfigure extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
    }
}
