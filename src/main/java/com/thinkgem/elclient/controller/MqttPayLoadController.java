package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.MqttPayLoad;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.util.CustomParamUtils;
import com.thinkgem.elclient.service.MqttPayLoadService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/mqttPayLoad")
@Slf4j
public class MqttPayLoadController {

    @Autowired
    MqttPayLoadService mqttPayLoadService;

    @RequestMapping(value="/getListByClientIds", method= RequestMethod.GET)
    public RestResult<List<AggResultEntry>> getListByClientIds(String[] clientIds){
        if(clientIds == null || clientIds.length < 1){
            return RestResult.getFailResult(500, "参数为NULL");
        }
        try{
            QueryEntry queryEntry = CustomParamUtils.getQueryEntry(clientIds, MqttPayLoad.class);
            AggQueryEntry aggQueryEntry = CustomParamUtils.getAggQueryEntry(MqttPayLoad.class);
            return mqttPayLoadService.aggQueryRequest(queryEntry, aggQueryEntry);
        }catch (Exception e){
            return RestResult.getFailResult(500, "参数错误");
        }
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public RestResult save(MqttPayLoad mqttPayLoad){
        return mqttPayLoadService.save(mqttPayLoad);
    }

    @RequestMapping(value="/saveBulk", method= RequestMethod.POST)
    public RestResult saveBulk(List<EsBaseEntity> list){
        return mqttPayLoadService.saveBulk(list);
    }


}
