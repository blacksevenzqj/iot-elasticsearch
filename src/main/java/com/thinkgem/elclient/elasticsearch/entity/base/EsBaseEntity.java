package com.thinkgem.elclient.elasticsearch.entity.base;

import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import lombok.Data;

@Data
public class EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String dbId;

    @EsFieldData(dataName= EsConfig.EL_DATE)
    public String createDate;

    @EsFieldData(dataName=EsConfig.EL_DATE)
    public String updateDate;

}
