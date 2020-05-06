package com.yan.febsmonitoradmin.configure;

import de.codecentric.boot.admin.server.config.AdminServerProperties;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsmonitoradmin.configure
 * @Author: Yan
 * @CreateTime: 2020-05-06 10:37
 * @Description:
 */
@EnableWebSecurity
public class FebsSecurityConfigure extends WebSecurityConfigurerAdapter {
    private final String adminContextPath;


    public FebsSecurityConfigure(AdminServerProperties adminContextPath) {
        this.adminContextPath = adminContextPath.getContextPath();
    }


    /**
     * 配置了免认证路径，比如/assets/**静态资源和/login登录页面；
     * 配置了登录页面为/login，登出页面为/logout
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setTargetUrlParameter("redirectTo");

        http.authorizeRequests()
                .antMatchers(adminContextPath + "/assets/**").permitAll()
                .antMatchers(adminContextPath + "/login").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().loginPage(adminContextPath + "/login").successHandler(successHandler).and()
                .logout().logoutUrl(adminContextPath + "/logout").and()
                .httpBasic().and()
                .csrf().disable();
    }
}
