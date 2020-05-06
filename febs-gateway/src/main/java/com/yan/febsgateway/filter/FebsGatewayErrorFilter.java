package com.yan.febsgateway.filter;

import com.netflix.zuul.context.RequestContext;
import com.yan.febscommon.entity.FebsResponse;
import com.yan.febscommon.util.FebsUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.http.protocol.RequestContent;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.cloud.netflix.zuul.filters.support.FilterConstants;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.util.ReflectionUtils;

import javax.servlet.http.HttpServletResponse;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsgateway.filter
 * @Author: Yan
 * @CreateTime: 2020-04-28 17:13
 * @Description:
 */
@Slf4j
@Component
public class FebsGatewayErrorFilter extends SendErrorFilter {
    /**
     * 通过RequestContext获取到当前请求上下文，通过请求上下文可以获取到当前
     * 请求的服务名称serviceId和当前请求的异常对象ExceptionHolder等信息。
     * 通过异常对象我们可以继续获取到异常内容，根据不同的异常内容我们可以自定义想要的响应。
     * @return
     */
    @Override
    public Object run() {
        try {
            FebsResponse febsResponse = new FebsResponse();
            RequestContext content = RequestContext.getCurrentContext();
            String serviceId = (String)content.get(FilterConstants.SERVICE_ID_KEY);ExceptionHolder exception =
                    findZuulException(content.getThrowable());

            String errorCause = exception.getErrorCause();
            Throwable throwable = exception.getThrowable();
            String message = throwable.getMessage();
            message = StringUtils.isBlank(message) ? errorCause : message;
            febsResponse = resolveExceptionMessage(message, serviceId, febsResponse);

            HttpServletResponse response = content.getResponse();
            FebsUtil.makeResponse(
                    response, MediaType.APPLICATION_JSON_UTF8_VALUE,
                    HttpServletResponse.SC_INTERNAL_SERVER_ERROR, febsResponse
            );
            log.error("Zull sendError：{}", febsResponse.getMessage());
        } catch (Exception ex) {
            log.error("Zuul sendError", ex);
            ReflectionUtils.rethrowRuntimeException(ex);
        }
        return null;
    }

    private FebsResponse resolveExceptionMessage(String message, String serviceId, FebsResponse febsResponse) {
        if (StringUtils.containsIgnoreCase(message, "time out")) {
            return febsResponse.message("请求" + serviceId + "服务超时");
        }
        if (StringUtils.containsIgnoreCase(message, "forwarding error")) {
            return febsResponse.message(serviceId + "服务不可用");
        }
        return febsResponse.message("Zuul请求" + serviceId + "服务异常");
    }
}
