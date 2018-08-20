package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="mqttpayload", routingName="mqttpayload")
public class MqttPayLoad extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "id")
    public String id;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "payload")
    public String payLoad;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "type")
    public String type;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "clientid")
    public String clientId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "online")
    public String onLine;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "updateDate")
    public String updateDate;

}
