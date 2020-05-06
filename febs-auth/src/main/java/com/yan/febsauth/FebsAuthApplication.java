package com.yan.febsauth;

import com.yan.febscommon.annotation.EnableFebsAuthExceptionHandler;
import com.yan.febscommon.annotation.EnableFebsServerProtect;
import com.yan.febscommon.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@FebsCloudApplication
@MapperScan("com.yan.febsauth.mapper")
public class FebsAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsAuthApplication.class, args);
    }

}
