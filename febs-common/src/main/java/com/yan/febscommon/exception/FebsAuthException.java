package com.yan.febscommon.exception;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.exception
 * @Author: Yan
 * @CreateTime: 2020-04-26 16:19
 * @Description:
 */
public class FebsAuthException extends Exception{
    private static final long serialVersionUID = -6167898251869435236L;

    public FebsAuthException(String message){
        super(message);
    }
}
