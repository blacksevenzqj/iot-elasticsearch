package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.MqttPayLoad;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.service.Es6ServiceImpl;
import com.thinkgem.elclient.service.MqttPayLoadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "mqttPayLoadService")
public class MqttPayLoadServiceImpl implements MqttPayLoadService {

    @Autowired
    Es6ServiceImpl es6ServiceImpl;

    @Override
    public RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<MqttPayLoad> queryEntry, AggQueryEntry aggQueryEntry) {
        return es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
    }

    @Override
    public RestResult save(MqttPayLoad mqttPayLoad) {
        return es6ServiceImpl.createIndexDoc(MqttPayLoad.class, mqttPayLoad);
    }

    @Override
    public RestResult saveBulk(List<EsBaseEntity> addList) {
        return es6ServiceImpl.processDocBulk(MqttPayLoad.class, addList, null, null);
    }

}
