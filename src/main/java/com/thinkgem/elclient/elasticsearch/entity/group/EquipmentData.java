package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="equipmentdata", routingName="equipment")
public class EquipmentData extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String equipmentId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String createBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String updateBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String remarks;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String delFlag;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column1;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column2;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column3;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column4;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column5;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column6;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column7;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column8;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column9;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    private String column10;



}
