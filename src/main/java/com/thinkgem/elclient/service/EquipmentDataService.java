package com.thinkgem.elclient.service;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;

import java.util.List;

public interface EquipmentDataService {

    RestResult<List<EquipmentData>> pageQueryRequest(QueryEntry<EquipmentData> queryEntry);

    RestResult save(EquipmentData equipmentData);

    RestResult saveBulk(List<EsBaseEntity> addList);

}
