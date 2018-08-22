package com.thinkgem.elclient.elasticsearch.common;

public enum AggDescEnum {

    NULL("null", "null", null, "默认为NULL"),  MAX_UPDATE(EsConfig.AggQuery.MAX, EsConfig.AggQuery.CustomizeGroupName.MAX_UPDATE, 1, "最大更新时间"),
    GROUP_BY_ON_LINE(EsConfig.AggQuery.TERMS, EsConfig.AggQuery.CustomizeGroupName.GROUP_ON_LINE, 2, "在线分组"),
    GROUP_BY_CLIENT_ID(EsConfig.AggQuery.TERMS, EsConfig.AggQuery.CustomizeGroupName.GROUP_BY_CLIENT_ID, 3, "设备ID分组"),
    ;

    private String aggType;
    private String groupName;
    private Integer aggOrder; // 从最里层 1 ---> 到最外层 3
    private String aggDesc;

    AggDescEnum(String aggType, String groupName, Integer aggOrder, String aggDesc) {
        this.aggType = aggType;
        this.groupName = groupName;
        this.aggOrder = aggOrder;
        this.aggDesc = aggDesc;
    }

    public String getAggType() {
        return aggType;
    }
    public String getGroupName() {
        return groupName;
    }
    public Integer getAggOrder() {
        return aggOrder;
    }
    public String getAggDesc() {
        return aggDesc;
    }

}
