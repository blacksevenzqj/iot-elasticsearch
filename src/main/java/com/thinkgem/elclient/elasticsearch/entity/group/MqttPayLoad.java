package com.thinkgem.elclient.elasticsearch.entity.group;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.AggDescEnum;
import com.thinkgem.elclient.elasticsearch.common.QueryDescEnum;
import com.thinkgem.elclient.elasticsearch.common.SortDescEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import lombok.Data;

@Data
@Es6Index(numberOfShards=5, numberOfReplicas=1, indexName="mqttpayload", routingName="mqttpayload")
public class MqttPayLoad extends EsBaseEntity {

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "id")
    public String dbId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "payload")
    public String payLoad;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "type")
    public String type;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "clientid", elQueryType = QueryDescEnum.QUERY_CLIENTS_ID, elAggType = AggDescEnum.GROUP_BY_CLIENT_ID)
    public String clientId;

    @EsFieldData(dataName= EsConfig.El_KEYWORD, elName = "online", elAggType = AggDescEnum.GROUP_BY_ON_LINE)
    public String onLine;

    @EsFieldData(dataName=EsConfig.EL_DATE, elName = "updateDate", elAggType = AggDescEnum.MAX_UPDATE, elSortType = SortDescEnum.QUERY_SORT_UPDATE_DATE_DESC)
    public String updateDate;

}
