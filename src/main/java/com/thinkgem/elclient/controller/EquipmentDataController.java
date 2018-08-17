package com.thinkgem.elclient.controller;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.query.EquipmentDataQuery;
import com.thinkgem.elclient.service.EquipmentDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class EquipmentDataController {

    @Autowired
    EquipmentDataService equipmentDataService;

    @RequestMapping(value="/equipmentData",method= RequestMethod.GET)
    public RestResult<List<EquipmentData>> equipmentData(EquipmentDataQuery equipmentDataQuery){
        return equipmentDataService.termQueryByEquipmentId(equipmentDataQuery);
    }

}
