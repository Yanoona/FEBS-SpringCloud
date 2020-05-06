package com.yan.febscommon.annotation;

import com.yan.febscommon.configure.FebsLettuceRedisConfigure;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.annotation
 * @Author: Yan
 * @CreateTime: 2020-04-30 10:19
 * @Description:
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import(FebsLettuceRedisConfigure.class)
public @interface EnableFebsLettuceRedis {
}
