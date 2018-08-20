package com.thinkgem.elclient.elasticsearch.entity.search;

import com.thinkgem.elclient.elasticsearch.annotation.EsBoolQuery;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import lombok.Data;

import java.util.Map;

@Data
public class QueryEntry<T> {

    private Class<T> tClass;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.MUST)
    private Map<String, Object> match;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.FILTER)
    private Map<String, Object> term;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.FILTER)
    private Map<String, Object[]> terms;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.FILTER)
    private Map<String, Object[]> range;

    @EsBoolQuery(boolTypeName = EsConfig.BoolQuery.SHOULD)
    private Map<String, Object[]> shouldTerm;

    private String orderField;

    private String orderType;

    private EsPageInfo esPageInfo;

}
