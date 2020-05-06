package com.yan.febscommon.entity;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.entity
 * @Author: Yan
 * @CreateTime: 2020-04-29 10:06
 * @Description: 常量类
 */
public class FebsConstant {
    /**
     * Zuul请求头TOKEN名称（不要有空格）
     */
    public static final String ZUUL_TOKEN_HEADER = "ZuulToken";
    /**
     * Zuul请求头TOKEN值
     */
    public static final String ZUUL_TOKEN_VALUE = "febs:zuul:123456";
    /**
     * gif类型
     */
    public static final String GIF = "gif";
    /**
     * png类型
     */
    public static final String PNG = "png";

    /**
     * 验证码 key前缀
     */
    public static final String CODE_PREFIX = "febs.captcha.";
}
