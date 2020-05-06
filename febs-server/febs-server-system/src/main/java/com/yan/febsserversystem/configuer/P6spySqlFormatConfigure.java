package com.yan.febsserversystem.configuer;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;
import com.yan.febscommon.util.DateUtil;
import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febsserversystem.configuer
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:20
 * @Description: p6spy自定义配置类
 */
public class P6spySqlFormatConfigure implements MessageFormattingStrategy {

    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return StringUtils.isNotBlank(sql) ? DateUtil.formatFullTime(LocalDateTime.now(), DateUtil.FULL_TIME_SPLIT_PATTERN)
                + " | 耗时 " + elapsed + " ms | SQL 语句：" + sql.replaceAll("[\\s]+", " ") +
                ";" : StringUtils.EMPTY;
    }
}
