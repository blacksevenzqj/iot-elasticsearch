package com.thinkgem.elclient.service;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.utils.PageUtils;

import java.util.List;

public interface EquipmentDataService {

   RestResult<PageUtils<EquipmentData>> pageQueryRequest(QueryEntry<EquipmentData> queryEntry);

    RestResult save(EquipmentData equipmentData);

    RestResult saveBulk(List<EquipmentData> addList);

    RestResult<List<AggResultEntry>> aggQueryRequest(QueryEntry<EquipmentData> queryEntry, AggQueryEntry aggQueryEntry);

}
