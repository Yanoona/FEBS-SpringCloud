package com.yan.febsauth.filter;

import com.yan.febsauth.service.ValidateCodeService;
import com.yan.febscommon.entity.FebsResponse;
import com.yan.febscommon.exception.ValidateCodeException;
import com.yan.febscommon.util.FebsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.filter
 * @Author: Yan
 * @CreateTime: 2020-04-30 11:15
 * @Description: 验证码过滤器
 */
@Slf4j
@Component
public class ValidateCodeFilter extends OncePerRequestFilter {
    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 验证码校验逻辑：当拦截的请求URI为/oauth/token，
     * 请求方法为POST并且请求参数grant_type为password的时候（对应密码模式获取令牌请求），需要进行验证码校验。
     * @param request
     * @param response
     * @param filterChain
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String clientId = getClientId(header, request);

        RequestMatcher matcher = new AntPathRequestMatcher("/oauth/token", HttpMethod.POST.toString());
        if (matcher.matches(request)
                && StringUtils.equalsIgnoreCase(request.getParameter("grant_type"), "password")
//                判断ClientId是否为swagger，是的话无需进行图形验证码校验。
                && !StringUtils.equalsAnyIgnoreCase(clientId,"swagger")) {
            try {
                validateCode(request);
//                让过滤器链继续往下执行，校验不通过时直接返回错误响应。
                filterChain.doFilter(request, response);
            } catch (ValidateCodeException e) {
                FebsResponse febsResponse = new FebsResponse();
                FebsUtil.makeResponse(response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                        HttpServletResponse.SC_INTERNAL_SERVER_ERROR, febsResponse.message(e.getMessage()));
                log.error(e.getMessage(), e);
            }
        } else {
            filterChain.doFilter(request, response);
        }
    }

    private void validateCode(HttpServletRequest httpServletRequest) throws ValidateCodeException {
        String code = httpServletRequest.getParameter("code");
        String key = httpServletRequest.getParameter("key");
        validateCodeService.check(key, code);
    }

    /**
     * 用于从请求头部获取ClientId信息, 此处代码源于 Spring Cloud OAuth2源码
     * @param header
     * @param request
     * @return
     */
    private String getClientId(String header, HttpServletRequest request) {
        String clientId = "";
        try {
            byte[] base64Token = header.substring(6).getBytes(StandardCharsets.UTF_8);
            byte[] decoded;
            decoded = Base64.getDecoder().decode(base64Token);

            String token = new String(decoded, StandardCharsets.UTF_8);
            int delim = token.indexOf(":");
            if (delim != -1) {
                clientId = new String[]{token.substring(0, delim), token.substring(delim + 1)}[0];
            }
        } catch (Exception ignore) {
        }
        return clientId;
    }
}
