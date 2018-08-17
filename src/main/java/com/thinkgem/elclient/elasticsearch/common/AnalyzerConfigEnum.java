package com.thinkgem.elclient.elasticsearch.common;

public enum AnalyzerConfigEnum {

    IK("ik", "ik_max_word", "存储"), IK_SEARCH("ik_search", "ik_max_word", "搜索");

    private String key;
    private String value;
    private String desc;

    AnalyzerConfigEnum(String key, String value, String desc) {
        this.key = key;
        this.value = value;
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }
    public String getValue() {
        return value;
    }
    public String getDesc() {
        return desc;
    }

}
