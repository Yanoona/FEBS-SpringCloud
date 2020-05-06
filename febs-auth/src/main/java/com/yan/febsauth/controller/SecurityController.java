package com.yan.febsauth.controller;

import com.yan.febsauth.service.ValidateCodeService;
import com.yan.febscommon.entity.FebsResponse;
import com.yan.febscommon.exception.FebsAuthException;
import com.yan.febscommon.exception.ValidateCodeException;
import com.yan.febscommon.handle.BaseExceptionHandler;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.provider.token.ConsumerTokenServices;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.Principal;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsauth.controller
 * @Author: Yan
 * @CreateTime: 2020-04-26 16:15
 * @Description:
 */
@RestController
public class SecurityController {
    @Autowired
    private ConsumerTokenServices consumerTokenServices;
    @Autowired
    private ValidateCodeService validateCodeService;

    /**
     * 校验码生成
     * @param request
     * @param response
     * @throws IOException
     * @throws ValidateCodeException
     */
    @GetMapping("captcha")
    public void captcha(HttpServletRequest request, HttpServletResponse response) throws IOException, ValidateCodeException {
        validateCodeService.create(request, response);
    }

    @GetMapping("oauth/test")
    public String testOauth() {
        return "oauth";
    }

    @GetMapping("user")
    public Principal currentUser(Principal principal) {
        return principal;
    }

    @DeleteMapping("signout")
    public FebsResponse signout(HttpServletRequest request) throws FebsAuthException {
        String authorization = request.getHeader("Authorization");
        String token = StringUtils.replace(authorization, "bearer ", "");
        FebsResponse febsResponse = new FebsResponse();
        if (!consumerTokenServices.revokeToken(token)) {
            throw new FebsAuthException("退出登录失败");
        }
        return febsResponse.message("退出登录成功");
    }
}
