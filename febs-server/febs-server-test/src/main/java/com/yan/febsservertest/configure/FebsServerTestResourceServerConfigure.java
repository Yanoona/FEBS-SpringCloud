package com.yan.febsservertest.configure;

import com.yan.febscommon.handle.FebsAccessDeniedHandler;
import com.yan.febscommon.handle.FebsAuthExceptionEntryPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsservertest.configure
 * @Author: Yan
 * @CreateTime: 2020-04-27 11:21
 * @Description:
 */
@Configuration
@EnableResourceServer
public class FebsServerTestResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private FebsAccessDeniedHandler febsAccessDeniedHandler;
    @Autowired
    private FebsAuthExceptionEntryPoint febsAuthExceptionEntryPoint;

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf()
                .disable()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**")
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(febsAccessDeniedHandler)
                .authenticationEntryPoint(febsAuthExceptionEntryPoint);
    }
}
