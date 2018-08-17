package com.thinkgem.elclient.elasticsearch.entity.query;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class EquipmentDataQuery {

    @NotNull(message="查询字段名称必填")
    private String fieldName;

    @NotNull(message="查询字段值必填")
    private String field;

    private int pageNum = 0;

    private int pageSize = 10;

    private String orderField;

    private String orderType;

}
