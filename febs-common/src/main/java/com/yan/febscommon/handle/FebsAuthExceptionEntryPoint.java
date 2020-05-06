package com.yan.febscommon.handle;

import com.yan.febscommon.entity.FebsResponse;
import com.yan.febscommon.util.FebsUtil;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsgateway.handle
 * @Author: Yan
 * @CreateTime: 2020-04-28 16:34
 * @Description: 用于处理401访问异常
 */
public class FebsAuthExceptionEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                         AuthenticationException e) throws IOException, ServletException {
        FebsResponse response = new FebsResponse();

        FebsUtil.makeResponse(httpServletResponse, MediaType.APPLICATION_JSON_UTF8_VALUE,
                HttpServletResponse.SC_UNAUTHORIZED, response.message("Token无效"));

    }
}
