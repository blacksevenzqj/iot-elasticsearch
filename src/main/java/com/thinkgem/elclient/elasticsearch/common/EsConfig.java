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

    interface AggQuery{
        String TERMS = "terms";
        String MAX = "max";

        interface CustomizeGroupName{
            String ON_LINE = "1";
            String OFF_LINE = "0";
            String MAX_UPDATE = "max_by_updateDate";
        }
    }



}
