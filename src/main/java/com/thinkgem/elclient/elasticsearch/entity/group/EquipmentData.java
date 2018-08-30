package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.AggDescEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.common.QueryDescEnum;
import com.thinkgem.elclient.elasticsearch.common.SortDescEnum;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="equipmentdata", routingName="equipment")
public class EquipmentData extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "db_id")
    public String dbId;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "create_date", elSortType = SortDescEnum.QUERY_SORT_CREATE_DATE_DESC)
    public String createDate;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "update_date")
    public String updateDate;


    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "equipment_id", elQueryType = QueryDescEnum.QUERY_EQUIPMENT_ID)
    public String equipmentId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "equipment_code", elQueryType = QueryDescEnum.QUERY_EQUIPMENT_CODE)
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

    @EsFieldData(dataName= EsConfig.El_LONG, elAggType = AggDescEnum.MAX_FIELD)
    public Long count;


    @Override
    public String toString() {
        return "EquipmentData{" +
                "dbId='" + dbId + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", equipmentId='" + equipmentId + '\'' +
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
                ", count=" + count +
                '}';
    }

}
