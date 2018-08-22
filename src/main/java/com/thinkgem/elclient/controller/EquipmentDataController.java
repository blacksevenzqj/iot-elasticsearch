package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.service.EquipmentDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="/equipmentData")
@Slf4j
public class EquipmentDataController {

    @Autowired
    EquipmentDataService equipmentDataService;

    @RequestMapping(value="/getListByKv", method= RequestMethod.POST)
    public RestResult<List<EquipmentData>> getListByKv(QueryEntry<EquipmentData> queryEntry){
        return equipmentDataService.pageQueryRequest(queryEntry);
    }

    @RequestMapping(value="/getListByJson", method= RequestMethod.POST)
    public RestResult<List<EquipmentData>> getListByJson(@RequestBody  QueryEntry<EquipmentData> queryEntry){
        return equipmentDataService.pageQueryRequest(queryEntry);
    }

    @RequestMapping(value="/save", method= RequestMethod.POST)
    public RestResult save(EquipmentData equipmentData){
        return equipmentDataService.save(equipmentData);
    }

    @RequestMapping(value="/saveBulk", method= RequestMethod.POST)
    public RestResult saveBulk(List<EsBaseEntity> list){
        return equipmentDataService.saveBulk(list);
    }

}
