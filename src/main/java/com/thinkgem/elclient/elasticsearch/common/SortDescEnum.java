package com.thinkgem.elclient.elasticsearch.common;

public enum SortDescEnum {
    NULL(null, null, "默认为NULL"),

    QUERY_SORT_CREATE_DATE_ASC(EsConfig.FIELD_QUERY_TYPE.SORT_TYPE, 0, "asc"),
    QUERY_SORT_CREATE_DATE_DESC(EsConfig.FIELD_QUERY_TYPE.SORT_TYPE, 0, "desc"),

    QUERY_SORT_UPDATE_DATE_ASC(EsConfig.FIELD_QUERY_TYPE.SORT_TYPE, 1, "asc"),
    QUERY_SORT_UPDATE_DATE_DESC(EsConfig.FIELD_QUERY_TYPE.SORT_TYPE, 1, "desc");

    private String sortType;
    private Integer sortOrder; // 人参顺序
    private String sortDesc;


    SortDescEnum(String sortType, Integer sortOrder, String sortDesc) {
        this.sortType = sortType;
        this.sortOrder = sortOrder;
        this.sortDesc = sortDesc;
    }

    public String getSortType() {
        return sortType;
    }
    public Integer getSortOrder() {
        return sortOrder;
    }
    public String getSortDesc() {
        return sortDesc;
    }

}
