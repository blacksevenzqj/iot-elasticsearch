package com.thinkgem.elclient.elasticsearch.entity.base;

import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import lombok.Data;

@Data
public class EsBaseEntity {

    private String esId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "db_id")
    public String dbId;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "create_date")
    public String createDate;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "update_date")
    public String updateDate;

}
