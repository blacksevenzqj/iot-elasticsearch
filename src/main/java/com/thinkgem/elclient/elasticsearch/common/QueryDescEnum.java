package com.thinkgem.elclient.elasticsearch.common;

public enum QueryDescEnum {
    NULL(null, null, "默认为NULL"),

    QUERY_EQUIPMENT_ID(EsConfig.FIELD_QUERY_TYPE.QUERY_TERM_TYPE, "equipmentId", "equipmentId查询"),
    QUERY_EQUIPMENT_CODE(EsConfig.FIELD_QUERY_TYPE.QUERY_TERM_TYPE, "equipmentCode", "equipmentCode查询"),

    QUERY_CLIENTS_ID(EsConfig.FIELD_QUERY_TYPE.QUERY_TERMS_TYPE, "clientId", "clientId查询");

    private String queryType;
    private String queryFieldName; // JAVA中的字段名
    private String queryDesc;

    QueryDescEnum(String queryType, String queryFieldName, String queryDesc) {
        this.queryType = queryType;
        this.queryFieldName = queryFieldName;
        this.queryDesc = queryDesc;
    }

    public String getQueryType() {
        return queryType;
    }
    public String getQueryFieldName() {
        return queryFieldName;
    }
    public String getQueryDesc() {
        return queryDesc;
    }

}
