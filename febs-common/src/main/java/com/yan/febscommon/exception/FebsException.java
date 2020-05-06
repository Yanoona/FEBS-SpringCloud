package com.yan.febscommon.exception;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.exception
 * @Author: Yan
 * @CreateTime: 2020-04-30 16:14
 * @Description: 通用业务系统异常处理
 */
public class FebsException extends Exception {

    private static final long serialVersionUID = -813339228504457528L;

    public FebsException(String message){
        super(message);
    }
}
