package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="equipmentdata", routingName="equipment")
public class EquipmentData extends EsBaseEntity {

    @NotNull(message="设备ID字段必填")
    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String equipmentId;

    @NotNull(message="设备CODE字段必填")
    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String equipmentCode;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String createBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String updateBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String remarks;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String delFlag;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column1;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column2;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column3;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column4;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column5;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column6;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column7;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column8;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column9;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String column10;



}
