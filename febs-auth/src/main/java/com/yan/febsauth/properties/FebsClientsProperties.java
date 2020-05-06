package com.yan.febsauth.properties;

import lombok.Data;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.properties
 * @Author: Yan
 * @CreateTime: 2020-04-28 15:03
 * @Description:
 */
@Data
public class FebsClientsProperties {
    private String client;
    private String secret;
    //当前令牌支持的认证类型
    private String grantType = "password,authorization_code,refresh_token";
    //认证范围
    private String scope = "all";
}
