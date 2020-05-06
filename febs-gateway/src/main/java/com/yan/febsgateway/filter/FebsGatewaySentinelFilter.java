package com.yan.febsgateway.filter;

import com.alibaba.csp.sentinel.adapter.gateway.common.SentinelGatewayConstants;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiDefinition;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPathPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.ApiPredicateItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.api.GatewayApiDefinitionManager;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayFlowRule;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayParamFlowItem;
import com.alibaba.csp.sentinel.adapter.gateway.common.rule.GatewayRuleManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackManager;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulErrorFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPostFilter;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.filters.SentinelZuulPreFilter;
import com.netflix.zuul.ZuulFilter;
import com.yan.febsgateway.fallback.FebsGatewayBlockFallbackProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsgateway.filter
 * @Author: Yan
 * @CreateTime: 2020-04-30 14:24
 * @Description: Sentinel验证码限流过滤器
 */
@Slf4j
@Component
public class FebsGatewaySentinelFilter {
    @Bean
    public ZuulFilter sentinelZuulPreFilter() {
        return new SentinelZuulPreFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulPostFilter() {
        return new SentinelZuulPostFilter();
    }

    @Bean
    public ZuulFilter sentinelZuulErrorFilter() {
        return new SentinelZuulErrorFilter();
    }

    @PostConstruct
    public void doInit() {
//        设置限流异常处理回报
        ZuulBlockFallbackManager.registerProvider(new FebsGatewayBlockFallbackProvider());
        initGatewayRules();
    }

    /**
     * 定义验证码请求限流，
     * 限流规则：60秒内同一个IP，同一个 key最多访问 10次
     * 限流路径：/auth/captcha
     */
    private void initGatewayRules() {
        Set<ApiDefinition> definitions = new HashSet<>();
        Set<ApiPredicateItem> predicateItems = new HashSet<>();

        predicateItems.add(new ApiPathPredicateItem().setPattern("/auth/captcha"));
        ApiDefinition definition = new ApiDefinition("captcha")
                .setPredicateItems(predicateItems);
        definitions.add(definition);
        GatewayApiDefinitionManager.loadApiDefinitions(definitions);

        Set<GatewayFlowRule> rules = new HashSet<>();

        rules.add(new GatewayFlowRule("captcha")
//                规则是针对 API Gateway 的 route（RESOURCE_MODE_ROUTE_ID）
//                还是用户在 Sentinel 中定义的 API 分组（RESOURCE_MODE_CUSTOM_API_NAME），默认是 route。
                        .setResourceMode(SentinelGatewayConstants.RESOURCE_MODE_CUSTOM_API_NAME)
//                参数限流配置
                        .setParamItem(
                                new GatewayParamFlowItem()
//                                从请求中提取参数的策略
                                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_URL_PARAM)
//                                若提取策略选择 Header 模式或 URL 参数模式，则需要指定对应的 header 名称或 URL 参数名称。
                                        .setFieldName("key")
//                                参数值的匹配策略，目前支持
//                                精确匹配（PARAM_MATCH_STRATEGY_EXACT）、
//                                子串匹配（PARAM_MATCH_STRATEGY_CONTAINS）和
//                                正则匹配（PARAM_MATCH_STRATEGY_REGEX）
                                        .setMatchStrategy(SentinelGatewayConstants.PARAM_MATCH_STRATEGY_EXACT)
                                        .setParseStrategy(SentinelGatewayConstants.PARAM_PARSE_STRATEGY_CLIENT_IP)
                        )
                        .setCount(10)
                        .setIntervalSec(60)
        );
        GatewayRuleManager.loadRules(rules);
    }

}
