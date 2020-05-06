package com.yan.febscommon.configure;

import com.yan.febscommon.interceptor.FebsServerProtectInterceptor;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.configure
 * @Author: Yan
 * @CreateTime: 2020-04-29 10:23
 * @Description:
 */
public class FebsServerProtectConfigure implements WebMvcConfigurer {

    /**
     * 因为各个微服务系统里可以自定义自个儿的PasswordEncoder，
     * 所以这里使用了@ConditionalOnMissingBean(value = PasswordEncoder.class)注解标注，
     * 表示当IOC容器里没有PasswordEncoder类型的Bean的时候，则将BCryptPasswordEncoder注册到IOC容器中。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(value = PasswordEncoder.class)
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public HandlerInterceptor febsServerProtectInterceptor() {
        return new FebsServerProtectInterceptor();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(febsServerProtectInterceptor());
    }
}
