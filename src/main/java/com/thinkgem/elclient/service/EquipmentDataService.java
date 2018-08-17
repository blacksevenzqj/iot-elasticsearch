package com.thinkgem.elclient.service;

import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.query.EquipmentDataQuery;

public interface EquipmentDataService {

    RestResult termQueryByEquipmentId(EquipmentDataQuery equipmentDataQuery);

}
