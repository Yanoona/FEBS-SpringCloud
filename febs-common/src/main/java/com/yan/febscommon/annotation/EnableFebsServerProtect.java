package com.yan.febscommon.annotation;

import com.yan.febscommon.interceptor.FebsServerProtectInterceptor;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.annotation
 * @Author: Yan
 * @CreateTime: 2020-04-29 10:28
 * @Description: 开启微服务防护，避免客户端绕过网关直接请求微服务；
 */
@Target(value = ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(value = FebsServerProtectInterceptor.class)
public @interface EnableFebsServerProtect {
}
