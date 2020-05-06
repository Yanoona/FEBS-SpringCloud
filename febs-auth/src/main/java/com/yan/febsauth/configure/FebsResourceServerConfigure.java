package com.yan.febsauth.configure;

import com.yan.febsauth.properties.FebsAuthProperties;
import com.yan.febscommon.handle.FebsAccessDeniedHandler;
import com.yan.febscommon.handle.FebsAuthExceptionEntryPoint;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.configure
 * @Author: Yan
 * @CreateTime: 2020-04-26 15:50
 * @Description:
 */
@Configuration
@EnableResourceServer
public class FebsResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private FebsAccessDeniedHandler febsAccessDeniedHandler;
    @Autowired
    private FebsAuthExceptionEntryPoint febsAuthExceptionEntryPoint;
    @Autowired
    private FebsAuthProperties properties;


    @Override
    public void configure(HttpSecurity http) throws Exception {
//        anonUrls为免认证资源数组，是从FebsAuthProperties配置中读取出来的值经过逗号分隔后的结果。
        String[] anonUrls = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(), ",");

        http.csrf().disable()
                .requestMatchers().antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonUrls).permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**").authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.accessDeniedHandler(febsAccessDeniedHandler)
                .authenticationEntryPoint(febsAuthExceptionEntryPoint);
    }
}
