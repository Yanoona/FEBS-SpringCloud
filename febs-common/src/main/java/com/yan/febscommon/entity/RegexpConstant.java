package com.yan.febscommon.entity;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.entity
 * @Author: Yan
 * @CreateTime: 2020-04-30 16:30
 * @Description: 正则表达式常量
 */
public class RegexpConstant {

    // 简单手机号正则（这里只是简单校验是否为 11位，实际规则更复杂）
    public static final String MOBILE_REG = "[1]\\d{10}";
}
