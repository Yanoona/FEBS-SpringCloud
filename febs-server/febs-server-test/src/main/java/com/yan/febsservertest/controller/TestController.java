package com.yan.febsservertest.controller;

import com.yan.febsservertest.service.IHelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsservertest.controller
 * @Author: Yan
 * @CreateTime: 2020-04-27 11:25
 * @Description:
 */
@Slf4j
@RestController
public class TestController {
    @Autowired
    private IHelloService iHelloService;

    @GetMapping("test1")
    @PreAuthorize("hasAnyAuthority('user:add')")
    public String test1() {
        return "拥有'user:add'权限";
    }

    @GetMapping("test2")
    @PreAuthorize("hasAnyAuthority('user:update')")
    public String test2() {
        return "拥有'user:update'权限";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @GetMapping("hello")
    public String hello(String name){
        log.info("Feign调用febs-server-system的/hello服务: "+name);
        return this.iHelloService.hello(name);
    }
}
