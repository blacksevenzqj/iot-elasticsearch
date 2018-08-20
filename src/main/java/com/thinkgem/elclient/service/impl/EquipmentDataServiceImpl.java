package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.service.Es6ServiceImpl;
import com.thinkgem.elclient.service.EquipmentDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "equipmentDataService")
public class EquipmentDataServiceImpl implements EquipmentDataService {

    @Autowired
    Es6ServiceImpl es6ServiceImpl;

    @Override
    public RestResult<List<EquipmentData>> pageQueryRequest(QueryEntry<EquipmentData> queryEntry) {
        queryEntry.setTClass(EquipmentData.class);
        return es6ServiceImpl.pageQueryRequest(queryEntry);
    }

    @Override
    public RestResult save(EquipmentData equipmentData) {
        return es6ServiceImpl.createIndexDoc(EquipmentData.class, equipmentData);
    }
}
