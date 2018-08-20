package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.client.EsClient;
import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.service.Es6ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEs6 {

    @Autowired
    EsClient esClient;

    @Autowired
    Es6ServiceImpl es6ServiceImpl;


    @Test
    public void getById() throws Exception {
        RestResult<EquipmentData> restResult = es6ServiceImpl.getById(EquipmentData.class, "61d533a353624e03bcff1aae1a748d5e");
        EquipmentData equipmentData = restResult.getData();
        System.out.println(equipmentData);
//        if(equipmentData.getColumn10() == null){
//            System.out.println("Column10 is null");
//        }
    }

    @Test
    public void getByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchTermByFiled(EquipmentData.class,
                "equipment_id", "8588ceaf5d70499e93fb1f824bc85ba1",
                new EsPageInfo(), null, null);
        System.out.println(restResult.getData());
    }

    @Test
    public void getMatchByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchMatchByField(EquipmentData.class,
                "remarks", "seven go",
                new EsPageInfo(), null, null);
        System.out.println(restResult.getData());
    }

    @Test
    public void save() throws Exception {
        EquipmentData equipmentData = new EquipmentData();
        equipmentData.setDbId("222");
        equipmentData.setCreateBy("2");
        equipmentData.setUpdateBy("2");
        equipmentData.setCreateDate("2018-08-09 13:30:45");
        equipmentData.setUpdateDate("2018-08-09 15:40:50");
        equipmentData.setEquipmentId("8588ceaf5d70499e93fb1f824bc85ba1");
        equipmentData.setEquipmentCode("8588ceaf5d70499e93fb1f824bc85ba1");
        equipmentData.setColumn1("kkk2");
        equipmentData.setColumn1("mmm2");
        es6ServiceImpl.createIndexDoc(EquipmentData.class, equipmentData);
        Thread.currentThread().sleep(1000);
    }
    @Test
    public void update() throws Exception {
        EquipmentData equipmentData = new EquipmentData();
        equipmentData.setDbId("000");
        equipmentData.setCreateBy("001");
        equipmentData.setUpdateBy("001");
        equipmentData.setCreateDate("2018-08-16 13:30:45");
        equipmentData.setUpdateDate("2018-08-16 15:40:50");
        equipmentData.setEquipmentId("8588ceaf5d70499e93fb1f824bc85ba1");
        equipmentData.setEquipmentCode("8588ceaf5d70499e93fb1f824bc85ba1");
        equipmentData.setColumn1("kkk");
        equipmentData.setColumn1("mmm");

        equipmentData.setEsId("WgCOTGUBr_Mjdtnuyij5");
        es6ServiceImpl.upDateIndexDoc(EquipmentData.class, equipmentData);
        Thread.currentThread().sleep(1000);
    }
    @Test
    public void delete() throws Exception {
        es6ServiceImpl.deleteIndexDoc(EquipmentData.class, "WwC_TGUBr_MjdtnuNyhG");
        Thread.currentThread().sleep(1000);
    }
    @Test
    public void processDocBulk() throws Exception {
        List<EsBaseEntity> updateList = new ArrayList();
        EquipmentData equipmentData = new EquipmentData();
        equipmentData.setDbId("002");
        equipmentData.setCreateBy("002");
        equipmentData.setUpdateBy("002");
        equipmentData.setCreateDate("2018-08-16 13:30:45");
        equipmentData.setUpdateDate("2018-08-16 15:40:50");
        equipmentData.setEquipmentId("eeeeeeeeeeeeeeeeee");
        equipmentData.setEquipmentCode("eeeeeeeeeeeeeeeeee");
        equipmentData.setColumn1("kkk");
        equipmentData.setColumn1("mmm");
        equipmentData.setEsId("WgCOTGUBr_Mjdtnuyij5");
        updateList.add(equipmentData);

        List<String> deleteList = new ArrayList();
        deleteList.add("XADWTGUBr_Mjdtnu1yjf");

        es6ServiceImpl.processDocBulk(EquipmentData.class, null, updateList, deleteList);
        Thread.currentThread().sleep(1000);
    }


    @Test
    public void pageQueryRequest() throws Exception {
        EsPageInfo esPageInfo = new EsPageInfo();
        esPageInfo.setPageSize(10);
        esPageInfo.setPageNum(1);

        Map<String, Object> termMap = new HashMap<>();
        termMap.put("equipment_id", "8588ceaf5d70499e93fb1f824bc85ba1");

        Map<String, Object[]> rangeMap = new HashMap<>();
        Object[] obj = new Object[2];
//        obj[0] = "2018-08-06";
        obj[0] = "2018-08-06 14:40:07";
        obj[1] = "2018-08-17";
        rangeMap.put("create_date", obj);

        Map<String, Object[]> shouldMap = new HashMap<>();
        Object[] objShould = new Object[2];
        objShould[0] = "2018-08-06";
        objShould[1] = "2018-08-09";
        shouldMap.put("create_date", objShould);

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(EquipmentData.class);
        queryEntry.setEsPageInfo(esPageInfo);
        queryEntry.setRange(rangeMap);
        queryEntry.setTerm(termMap);
        queryEntry.setShouldTerm(shouldMap);

        String str = JSON.toJSONString(queryEntry);
        System.out.println(str);

        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.pageQueryRequest(queryEntry);
        System.out.println(restResult.getData().size() + "___" + restResult.getData());
    }


    @Test
    public void searchMatchScrollByField() throws Exception {
        RestResult<List<EquipmentData>> restResult = es6ServiceImpl.searchMatchScrollByField(EquipmentData.class,
                "remarks", "go", 1);

        System.out.println(restResult.getData().size() + "___" + restResult.getData());
    }


}
