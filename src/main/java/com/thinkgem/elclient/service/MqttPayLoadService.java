package com.thinkgem.elclient.service;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.MqttPayLoad;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;

import java.util.List;

public interface MqttPayLoadService {

    RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<MqttPayLoad> queryEntry, AggQueryEntry aggQueryEntry);

    RestResult save(MqttPayLoad mqttPayLoad);

    RestResult saveBulk(List<MqttPayLoad> addList);

}
