package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.service.Es6ServiceImpl;
import com.thinkgem.elclient.service.EquipmentDataService;
import com.thinkgem.elclient.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "equipmentDataService")
public class EquipmentDataServiceImpl implements EquipmentDataService {

    @Autowired
    Es6ServiceImpl es6ServiceImpl;

    @Override
    public RestResult<PageUtils<EquipmentData>> pageQueryRequest(QueryEntry<EquipmentData> queryEntry) {
        if(queryEntry.getTClass() == null){
            queryEntry.setTClass(EquipmentData.class);
        }
        return es6ServiceImpl.pageQueryRequest(queryEntry);
    }

    @Override
    public RestResult save(EquipmentData equipmentData) {
        return es6ServiceImpl.createIndexDoc(EquipmentData.class, equipmentData);
    }

    @Override
    public RestResult saveBulk(List<EquipmentData> addList) {
        return es6ServiceImpl.processDocBulk(EquipmentData.class, addList, addList, null);
    }

    @Override
    public RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<EquipmentData> queryEntry, AggQueryEntry aggQueryEntry) {
        return es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
    }
}
