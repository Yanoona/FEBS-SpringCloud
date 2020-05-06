package com.yan.febsgateway.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsgateway.properties
 * @Author: Yan
 * @CreateTime: 2020-05-06 11:14
 * @Description:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:febs-gateway.properties"})
@ConfigurationProperties(prefix = "febs.gateway")
public class FebsGatewayProperties {
    /**
     * 禁止外部访问的 URI，多个值的话以逗号分隔
     */
    private String forbidRequestUri;
}
