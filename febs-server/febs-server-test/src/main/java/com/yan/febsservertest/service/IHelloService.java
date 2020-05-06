package com.yan.febsservertest.service;

import com.yan.febscommon.entity.FebsServerConstant;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsservertest.service
 * @Author: Yan
 * @CreateTime: 2020-04-29 08:59
 * @Description:
 */
@FeignClient(value = FebsServerConstant.FEBS_SERVER_SYSTEM, contextId = "helloServerSystem", fallbackFactory =
        HelloServiceFallback.class)
public interface IHelloService {
    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}
