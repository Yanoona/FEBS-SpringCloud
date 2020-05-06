package com.yan.febsauth.handle;

import com.yan.febscommon.handle.BaseExceptionHandler;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.handle
 * @Author: Yan
 * @CreateTime: 2020-04-28 17:30
 * @Description: 自定义异常类
 */
@RestControllerAdvice
@Order(value = Ordered.HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends BaseExceptionHandler {
}
