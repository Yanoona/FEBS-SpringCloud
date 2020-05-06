package com.yan.febsserversystem.properties;

import lombok.Data;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.properties
 * @Author: Yan
 * @CreateTime: 2020-05-06 09:25
 * @Description: Swagger配置类
 */
@Data
public class FebsSwaggerProperties {
    private String basePackage;
    private String title;
    private String description;
    private String version;
    private String author;
    private String url;
    private String email;
    private String license;
    private String licenseUrl;
    private String grantUrl;
    private String name;
    private String scope;
}
