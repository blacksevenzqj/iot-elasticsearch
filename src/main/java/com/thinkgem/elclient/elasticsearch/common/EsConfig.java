package com.thinkgem.elclient.elasticsearch.common;

public interface EsConfig {

    String NUMBER_OF_SHARDS = "index.number_of_shards";

    String NUMBER_OF_REPLICAS = "index.number_of_replicas";

    String PROPERTIES = "properties";

    String EL_TYPE = "type";

    String El_STRING = "text";

    String El_KEYWORD = "keyword";

    String El_INTEGER = "integer";

    String El_LONG = "long";

    String El_DOUBLE = "double";

    String EL_BOOLEAN = "boolean";

    String EL_DATE = "date";

    interface EsSearchConfig{
        String SEARCH_TITLE = "title";
    }

    interface EsTimeFormatConfig{
        String DEFAULT_FORMAT = "yyyy-MM-dd";
    }

    interface AnalyzerConfig{
        String ANALYZER = "analyzer";
        String SEARCH_ANALYZER = "search_analyzer";
    }

    interface BoolQuery{
        String MUST = "must";
        String MUST_NOT = "must_not";
        String FILTER = "filter";
        String SHOULD = "should";
    }

    interface FIELD_QUERY_TYPE{
        String DEFAULT_TYPE = "default_type"; //默认
        String SORT_TYPE = "sort_type"; //排序
        String QUERY_TERM_TYPE = "query_term_type"; //查询：精确
        String QUERY_TERMS_TYPE = "query_terms_type"; //查询：精确
        String QUERY_RANGE_TYPE = "query_range_type"; //查询：范围
        String QUERY_SHOULD_TERM_TYPE = "query_should_term_type"; //查询：OR
        String OTHER_TYPE = "other_type"; //其他
    }

    interface AggQuery{
        String TERMS = "terms";
        String MAX = "max";

        interface CustomizeGroupName{
            String ON_LINE = "1";
            String OFF_LINE = "0";

            String MAX_UPDATE = "max_by_updateDate";
            String GROUP_ON_LINE = "group_by_online";
            String GROUP_BY_CLIENT_ID = "group_by_clientid";

            String MAX_FIELD = "max_by_field";
        }
    }



}
