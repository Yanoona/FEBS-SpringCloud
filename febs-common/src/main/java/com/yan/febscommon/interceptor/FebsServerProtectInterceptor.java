package com.yan.febscommon.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.yan.febscommon.entity.FebsConstant;
import com.yan.febscommon.entity.FebsResponse;
import org.springframework.http.MediaType;
import org.springframework.util.Base64Utils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.interceptor
 * @Author: Yan
 * @CreateTime: 2020-04-29 10:08
 * @Description:
 */
public class FebsServerProtectInterceptor implements HandlerInterceptor {
    /**
     * 该拦截器可以拦截所有Web请求.
     * 方法中，我们通过HttpServletRequest获取请求头中的Zuul Token，
     * 并校验其正确性，当校验不通过的时候返回403错误。
     * @param request
     * @param response
     * @param handler
     * @return 403
     * @throws IOException
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException {
        // 从请求头中获取 Zuul Token
        String token = request.getHeader(FebsConstant.ZUUL_TOKEN_HEADER);
        byte[] bytes = Base64Utils.encode((FebsConstant.ZUUL_TOKEN_VALUE).getBytes());
        String zuulToken = new String(bytes);
        // 校验 Zuul Token的正确性
        if (zuulToken.equals(token)) {
            return true;
        } else {
            FebsResponse febsResponse = new FebsResponse();
            response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response.getWriter().write(JSONObject.toJSONString(febsResponse.message("请通过网关获取资源")));
            return false;
        }
    }
}
