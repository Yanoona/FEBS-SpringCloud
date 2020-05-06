package com.yan.febscommon.annotation;

import com.yan.febscommon.configure.FebsOAuth2FeignConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.annotation
 * @Author: Yan
 * @CreateTime: 2020-04-29 09:27
 * @Description: 开启带令牌的Feign请求，避免微服务内部调用出现401异常；
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsOAuth2FeignConfigure.class)
public @interface EnableFebsOauth2FeignClient {
}
