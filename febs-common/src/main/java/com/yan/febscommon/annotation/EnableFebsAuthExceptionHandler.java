package com.yan.febscommon.annotation;

import com.yan.febscommon.configure.FebsAuthExceptionConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.annotation
 * @Author: Yan
 * @CreateTime: 2020-04-28 17:04
 * @Description: 认证类型异常翻译。
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsAuthExceptionConfigure.class)
public @interface EnableFebsAuthExceptionHandler {

}
