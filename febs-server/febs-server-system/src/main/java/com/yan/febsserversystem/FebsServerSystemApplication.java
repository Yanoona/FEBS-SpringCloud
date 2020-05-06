package com.yan.febsserversystem;

import com.yan.febscommon.annotation.FebsCloudApplication;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableGlobalMethodSecurity(prePostEnabled = true)
@SpringBootApplication
@EnableDiscoveryClient
@FebsCloudApplication
@MapperScan("com.yan.febsserversystem.mapper")
@EnableTransactionManagement
public class FebsServerSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(FebsServerSystemApplication.class, args);
    }

}
