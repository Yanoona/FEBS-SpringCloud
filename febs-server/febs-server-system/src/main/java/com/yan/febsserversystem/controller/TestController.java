package com.yan.febsserversystem.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.rmi.runtime.Log;

import java.security.Principal;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.controller
 * @Author: Yan
 * @CreateTime: 2020-04-27 11:13
 * @Description:
 */
@Slf4j
@RestController
public class TestController {
    @GetMapping("info")
    public String test() {
        return "febs-server-system";
    }

    @GetMapping("currentUser")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name) {
        log.info("/Hello服务被调用");
        return "hello " + name;
    }
}
