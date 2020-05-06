package com.yan.febsserversystem.configuer;

import com.yan.febscommon.handle.FebsAccessDeniedHandler;
import com.yan.febscommon.handle.FebsAuthExceptionEntryPoint;
import com.yan.febsserversystem.properties.FebsServerSystemProperties;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.configuer
 * @Author: Yan
 * @CreateTime: 2020-04-27 11:05
 * @Description:
 */
@Configuration
@EnableResourceServer
public class FebsServerSystemResourceServerConfigure extends ResourceServerConfigurerAdapter {
    @Autowired
    private FebsAuthExceptionEntryPoint febsAuthExceptionEntryPoint;
    @Autowired
    private FebsAccessDeniedHandler febsAccessDeniedHandler;
    @Autowired
    private FebsServerSystemProperties properties;

    /**
     * 表示所有访问febs-server-system的请求都需要认证，只有通过认证服务器发放的令牌才能进行访问。
     *
     * @param http
     * @throws Exception
     */
    @Override
    public void configure(HttpSecurity http) throws Exception {
        String[] anonURL = StringUtils.splitByWholeSeparatorPreserveAllTokens(properties.getAnonUrl(),",");

        http.csrf().disable()
                .requestMatchers()
                .antMatchers("/**")
                .and()
                .authorizeRequests()
                .antMatchers(anonURL).permitAll()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/**")
                .authenticated();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        resources.authenticationEntryPoint(febsAuthExceptionEntryPoint)
                .accessDeniedHandler(febsAccessDeniedHandler);
    }
}
