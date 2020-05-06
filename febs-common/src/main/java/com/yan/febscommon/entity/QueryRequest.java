package com.yan.febscommon.entity;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @BelongsProject: febs-cloud
 * @BelongsPackage: com.yan.febscommon.entity
 * @Author: Yan
 * @CreateTime: 2020-04-30 15:37
 * @Description: 通用查询类--分页
 */
@Data
@ToString
public class QueryRequest implements Serializable {

    private static final long serialVersionUID = -2842942379283461045L;

    /**
     * 当前页面数据量
     */
    private int pageSize = 10;
    /**
     * 当前页码
     */
    private int pageNum = 1;
    /**
     * 排序字段
     */
    private String field;
    /**
     * 排序规则，asc升序，desc降序
     */
    private String order;
}
