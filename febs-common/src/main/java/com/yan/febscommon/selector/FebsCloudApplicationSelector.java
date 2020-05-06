package com.yan.febscommon.selector;

import com.yan.febscommon.configure.FebsAuthExceptionConfigure;
import com.yan.febscommon.configure.FebsLettuceRedisConfigure;
import com.yan.febscommon.configure.FebsOAuth2FeignConfigure;
import com.yan.febscommon.configure.FebsServerProtectConfigure;
import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.selector
 * @Author: Yan
 * @CreateTime: 2020-04-29 10:35
 * @Description: 将多个配置类一次性都注册到IOC容器中
 */
public class FebsCloudApplicationSelector implements ImportSelector {

    @Override
    public String[] selectImports(AnnotationMetadata annotationMetadata) {
        return new String[]{
                FebsAuthExceptionConfigure.class.getName(),
                FebsOAuth2FeignConfigure.class.getName(),
                FebsServerProtectConfigure.class.getName(),
                FebsLettuceRedisConfigure.class.getName()
        };
    }
}
