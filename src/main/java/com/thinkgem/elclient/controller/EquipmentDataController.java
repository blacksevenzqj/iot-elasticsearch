package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.elasticsearch.common.QueryDescEnum;
import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.util.CustomParamUtils;
import com.thinkgem.elclient.service.EquipmentDataService;
import com.thinkgem.elclient.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value="/equipmentData")
@Slf4j
public class EquipmentDataController {

    @Autowired
    EquipmentDataService equipmentDataService;

    @RequestMapping(value="/getListByValue", method= RequestMethod.GET)
    public RestResult<PageUtils<EquipmentData>> getListByValue(String equipmentId){
        try{
            Map<String, Object> ids = new HashMap();
            ids.put(QueryDescEnum.QUERY_EQUIPMENT_ID.getQueryFieldName(), equipmentId);
            ids.put(QueryDescEnum.QUERY_EQUIPMENT_CODE.getQueryFieldName(), equipmentId);
            QueryEntry<EquipmentData> queryEntry = CustomParamUtils.getQueryEntry(EquipmentData.class, ids, null, null, null);
            queryEntry.setEsPageInfo(new EsPageInfo());
            return equipmentDataService.pageQueryRequest(queryEntry);
        }catch (Exception e){
            return RestResult.getFailResult(500, "参数错误");
        }
    }

    @RequestMapping(value="/getListByKv", method= RequestMethod.POST)
    public RestResult<PageUtils<EquipmentData>> getListByKv(QueryEntry<EquipmentData> queryEntry){
        return equipmentDataService.pageQueryRequest(queryEntry);
    }

    @RequestMapping(value="/getListByJson", method= RequestMethod.POST)
    public RestResult<PageUtils<EquipmentData>> getListByJson(@RequestBody  QueryEntry<EquipmentData> queryEntry){
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


    @RequestMapping(value="/getMaxCount", method= RequestMethod.GET)
    public RestResult<List<AggResultEntry>> getMaxCount(String equipmentCode){
        try{
            Map<String, Object> codeMap = new HashMap();
            codeMap.put(QueryDescEnum.QUERY_EQUIPMENT_CODE.getQueryFieldName(), equipmentCode);
            QueryEntry<EquipmentData> queryEntry = CustomParamUtils.getQueryEntry(EquipmentData.class, codeMap, null, null, null);
            AggQueryEntry aggQueryEntry = CustomParamUtils.getAggQueryEntry(EquipmentData.class);
            return equipmentDataService.aggQueryRequest(queryEntry, aggQueryEntry);
        }catch (Exception e){
            return RestResult.getFailResult(500, "参数错误");
        }
    }

}
