package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="equipmentdata", routingName="equipment")
public class EquipmentData extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "equipment_id")
    public String equipmentId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "equipment_code")
    public String equipmentCode;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "create_by")
    public String createBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "update_by")
    public String updateBy;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String remarks;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "del_flag")
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


    @Override
    public String toString() {
        return "EquipmentData{" +
                "equipmentId='" + equipmentId + '\'' +
                ", equipmentCode='" + equipmentCode + '\'' +
                ", createBy='" + createBy + '\'' +
                ", updateBy='" + updateBy + '\'' +
                ", remarks='" + remarks + '\'' +
                ", delFlag='" + delFlag + '\'' +
                ", column1='" + column1 + '\'' +
                ", column2='" + column2 + '\'' +
                ", column3='" + column3 + '\'' +
                ", column4='" + column4 + '\'' +
                ", column5='" + column5 + '\'' +
                ", column6='" + column6 + '\'' +
                ", column7='" + column7 + '\'' +
                ", column8='" + column8 + '\'' +
                ", column9='" + column9 + '\'' +
                ", column10='" + column10 + '\'' +
                ", dbId='" + dbId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                '}';
    }

}
