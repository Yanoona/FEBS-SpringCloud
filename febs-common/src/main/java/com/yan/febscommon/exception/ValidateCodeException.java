package com.yan.febscommon.exception;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.exception
 * @Author: Yan
 * @CreateTime: 2020-04-30 10:27
 * @Description: 验证码异常处理类
 */
public class ValidateCodeException extends Exception {
    private static final long serialVersionUID = -2300648764255708879L;

    public ValidateCodeException(String message){
        super(message);
    }
}
