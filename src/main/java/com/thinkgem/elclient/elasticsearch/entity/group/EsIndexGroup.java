package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.annotation.EsIndex;
import com.thinkgem.elclient.elasticsearch.common.AnalyzerConfigEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@EsIndex(indexName="school", numberOfShards=5, numberOfReplicas=1)
public class EsIndexGroup extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_STRING, analyzerType = AnalyzerConfigEnum.IK, analyzerSearchType = AnalyzerConfigEnum.IK_SEARCH)
    public String title;

    @EsFieldData(dataName= EsConfig.El_KEYWORD)
    public String serviceUrl;

}
