package com.yan.febscommon.configure;

import com.yan.febscommon.handle.FebsAccessDeniedHandler;
import com.yan.febscommon.handle.FebsAuthExceptionEntryPoint;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.configure
 * @Author: Yan
 * @CreateTime: 2020-04-28 17:00
 * @Description: 因为febs-common模块是一个普通的maven项目，并不是一个Spring Boot项目，
 * 所以即使在这两个类上使用@Component注解标注，它们也不能被成功注册到各个微服务子系统的Spring IOC容器中。
 * 这里使用@Enable模块驱动的方式来解决这个问题。
 */
public class FebsAuthExceptionConfigure {
    /**
     * *@ConditionalOnMissingBean注解的意思是，当IOC容器中没有指定名称或类型的Bean的时候，就注册它。
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "accessDeniedHandler")
    public FebsAccessDeniedHandler accessDeniedHandler() {
        return new FebsAccessDeniedHandler();
    }

    @Bean
    @ConditionalOnMissingBean(name = "authenticationEntryPoint")
    public FebsAuthExceptionEntryPoint authenticationEntryPoint() {
        return new FebsAuthExceptionEntryPoint();
    }
}
