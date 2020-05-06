package com.yan.febsservertest.service;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsservertest.service
 * @Author: Yan
 * @CreateTime: 2020-04-29 09:06
 * @Description: Feign访问服务器失败的回滚处理类
 */
@Slf4j
@Component
public class HelloServiceFallback implements FallbackFactory<IHelloService> {
    @Override
    public IHelloService create(Throwable throwable) {
        return new IHelloService() {
            @Override
            public String hello(String name) {
                log.error("调用febs-server-system服务出错", throwable);
                return "调用出错";
            }
        };
    }
}
