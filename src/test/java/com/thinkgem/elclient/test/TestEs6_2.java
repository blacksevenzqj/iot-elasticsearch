package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.client.EsClient;
import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.group.MqttPayLoad;
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
public class TestEs6_2 {

    @Autowired
    EsClient esClient;

    @Autowired
    Es6ServiceImpl es6ServiceImpl;


    @Test
    public void save() throws Exception {
        MqttPayLoad mqttPayLoad = new MqttPayLoad();
        mqttPayLoad.setId("000001cb05b04a9fa18bb83e4079c215");
        mqttPayLoad.setPayLoad("{\"clientid\":\"serverdingshi\",\"username\":\"test\",\"ipaddress\":\"218.63.139.156\",\"clean_sess\":true,\"protocol\":3,\"connack\":0,\"ts\":1531965815}");
        mqttPayLoad.setOnLine("0");
        mqttPayLoad.setClientId("serverdingshi");
        mqttPayLoad.setType("在线情况");
        mqttPayLoad.setUpdateDate("2018-07-21 10:03:33");


//        mqttPayLoad.setId("0000b7545635424fa06f1ef059f4bd7b");
//        mqttPayLoad.setPayLoad("{\"clientid\":\"eyBITypD\",\"username\":\"eyBITypD\",\"ipaddress\":\"117.132.196.98\",\"clean_sess\":true,\"protocol\":3,\"connack\":0,\"ts\":1532283410}");
//        mqttPayLoad.setOnLine("1");
//        mqttPayLoad.setClientId("eyBITypD");
//        mqttPayLoad.setType("在线情况");
//        mqttPayLoad.setUpdateDate("2018-07-23 02:16:48");

//        mqttPayLoad.setId("0000b7545635424fa06f1ef059f4bd7c");
//        mqttPayLoad.setPayLoad("{\"clientid\":\"eyBITypD\",\"username\":\"eyBITypD\",\"ipaddress\":\"117.132.196.98\",\"clean_sess\":true,\"protocol\":3,\"connack\":0,\"ts\":1532283410}");
//        mqttPayLoad.setOnLine("1");
//        mqttPayLoad.setClientId("eyBITypD");
//        mqttPayLoad.setType("在线情况");
//        mqttPayLoad.setUpdateDate("2018-07-24 01:16:48");

        es6ServiceImpl.createIndexDoc(MqttPayLoad.class, mqttPayLoad);
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
    public void aggQueryRequest() throws Exception {
//        EsPageInfo esPageInfo = new EsPageInfo();
//        esPageInfo.setPageSize(10);
//        esPageInfo.setPageNum(1);

//        Map<String, Object[]> termsMap = new HashMap<>();
//        Object[] obj = new Object[2];
//        obj[0] = "serverdingshi";
//        obj[1] = "eyBITypD";
//        termsMap.put("clientid", obj);

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(MqttPayLoad.class);
        queryEntry.setOrderField("updateDate");

        String str = JSON.toJSONString(queryEntry);
        System.out.println(str);

        RestResult<List<MqttPayLoad>> restResult = es6ServiceImpl.aggQueryRequest(queryEntry);
        System.out.println(restResult.getData().size() + "___" + restResult.getData());
    }

}