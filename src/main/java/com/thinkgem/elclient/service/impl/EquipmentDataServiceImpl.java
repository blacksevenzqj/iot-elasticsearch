package com.thinkgem.elclient.service.impl;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.query.EquipmentDataQuery;
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
    public RestResult<List<EquipmentData>> termQueryByEquipmentId(EquipmentDataQuery equipmentDataQuery) {
        return es6ServiceImpl.searchTermByFiled(EquipmentData.class, equipmentDataQuery);
    }

    @Override
    public RestResult save(EquipmentData equipmentData) {
        return es6ServiceImpl.createIndexDoc(EquipmentData.class, equipmentData);
    }
}
