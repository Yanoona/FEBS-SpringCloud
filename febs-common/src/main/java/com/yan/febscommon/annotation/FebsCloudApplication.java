package com.yan.febscommon.annotation;

import com.yan.febscommon.selector.FebsCloudApplicationSelector;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.annotation
 * @Author: Yan
 * @CreateTime: 2020-04-29 10:37
 * @Description: 将FebsCloudApplicationSelector注册到IOC容器中, 代替 @EnableFebsAuthExceptionHandler
 * 、@EnableFebsOauthFeignClient、@EnableFebsServerProtect注解
 */
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Import(value = FebsCloudApplicationSelector.class)
public @interface FebsCloudApplication {
}
