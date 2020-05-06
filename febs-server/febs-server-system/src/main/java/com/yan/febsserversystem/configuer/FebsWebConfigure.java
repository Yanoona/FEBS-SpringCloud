package com.yan.febsserversystem.configuer;

import com.baomidou.mybatisplus.core.parser.ISqlParser;
import com.baomidou.mybatisplus.extension.parsers.BlockAttackSqlParser;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.yan.febsserversystem.properties.FebsServerSystemProperties;
import com.yan.febsserversystem.properties.FebsSwaggerProperties;
import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.OAuthBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.configuer
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:15
 * @Description: MyBatis Plus分页插件配置
 */
@Configuration
@EnableSwagger2
public class FebsWebConfigure {

    @Autowired
    FebsServerSystemProperties febsServerSystemProperties;

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        List<ISqlParser> sqlParserList = new ArrayList<>();
        sqlParserList.add(new BlockAttackSqlParser());
        paginationInterceptor.setSqlParserList(sqlParserList);
        return paginationInterceptor;
    }

    @Bean
    public Docket swaggerApi() {
        FebsSwaggerProperties swagger = febsServerSystemProperties.getSwagger();
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(swagger.getBasePackage()))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(swagger));
    }

    /**
     * 定义一些API页面信息，比如作者名称，邮箱，网站链接，开源协议等等。
     *
     * @return
     */
    public ApiInfo apiInfo(FebsSwaggerProperties swaggerProperties) {
        return new ApiInfo(
                swaggerProperties.getTitle(),
                swaggerProperties.getDescription(),
                swaggerProperties.getVersion(),
                null,
                new Contact(swaggerProperties.getAuthor(), swaggerProperties.getUrl(), swaggerProperties.getEmail()),
                swaggerProperties.getLicense(), swaggerProperties.getLicenseUrl(), Collections.emptyList()
        );
    }

    /**
     * 配置安全策略，比如配置认证模型，scope等内容
     *
     * @param properties
     * @return
     */
    private SecurityScheme securityScheme(FebsSwaggerProperties properties) {
//        认证类型为ResourceOwnerPasswordCredentialsGrant（即密码模式）
//        认证地址为http://localhost:8301/auth/oauth/token（即通过网关转发到认证服务器）
        GrantType grantType = new ResourceOwnerPasswordCredentialsGrant(properties.getGrantUrl());
//        将这个安全策略命名为febs_oauth_swagger
        return new OAuthBuilder().name(properties.getName())
                .grantTypes(Collections.singletonList(grantType))
                .scopes(Arrays.asList(scopes(properties)))
                .build();
    }

    /**
     * 配置安全上下文，只有配置了安全上下文的接口才能使用令牌获取资源
     *
     * @param properties
     * @return
     */
    private SecurityContext securityContext(FebsSwaggerProperties properties) {
        return SecurityContext.builder()
                .securityReferences(Collections.singletonList(new SecurityReference(properties.getName(),
                        scopes(properties))))
//                设置所有API接口都用这个安全上下文
                .forPaths(PathSelectors.any())
                .build();
    }

    private AuthorizationScope[] scopes(FebsSwaggerProperties properties) {
//        scope为test，和febs-auth模块里定义的一致
        return new AuthorizationScope[]{
                new AuthorizationScope(properties.getScope(), "")
        };
    }
}
