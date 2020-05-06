package com.yan.febsserversystem.properties;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.properties
 * @Author: Yan
 * @CreateTime: 2020-05-06 09:26
 * @Description:
 */
@Data
@SpringBootConfiguration
@PropertySource(value = {"classpath:febs-server-system.properties"})
@ConfigurationProperties(prefix = "febs.server.system")
public class FebsServerSystemProperties {
    private FebsSwaggerProperties swagger = new FebsSwaggerProperties();

//    免认证路径
    private String anonUrl;
}
