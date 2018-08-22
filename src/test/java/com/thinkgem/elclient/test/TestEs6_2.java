package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.client.EsClient;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.entity.group.MqttPayLoad;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultAll;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.service.Es6ServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

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
        mqttPayLoad.setDbId("000001cb05b04a9fa18bb83e4079c216");
        mqttPayLoad.setPayLoad("{\"clientid\":\"serverdingshi\",\"username\":\"test\",\"ipaddress\":\"218.63.139.156\",\"clean_sess\":true,\"protocol\":3,\"connack\":0,\"ts\":1531965815}");
        mqttPayLoad.setOnLine("1");
        mqttPayLoad.setClientId("serverdingshi");
        mqttPayLoad.setType("在线情况");
        mqttPayLoad.setUpdateDate("2018-08-21 10:03:33");


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
    public void aggQueryRequest() throws Exception {
//        EsPageInfo esPageInfo = new EsPageInfo();
//        esPageInfo.setPageSize(10);
//        esPageInfo.setPageNum(1);

        Map<String, Object[]> termsMap = new HashMap<>();
        Object[] obj = new Object[2];
        obj[0] = "serverdingshi";
        obj[1] = "eyBITypD";
        termsMap.put("clientid", obj);

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(MqttPayLoad.class);
        queryEntry.setOrderField("updateDate");

        String str = JSON.toJSONString(queryEntry);
        System.out.println(str);

        AggQueryEntry aggQueryEntry = new AggQueryEntry();

        AggQueryEntry.AggQueryEntryType maxByUpdateDate = aggQueryEntry.new AggQueryEntryType();
        maxByUpdateDate.setGroupName("max_by_updateDate");
        maxByUpdateDate.setFieldName("updateDate");
        maxByUpdateDate.setAggType(EsConfig.AggQuery.MAX);

        AggQueryEntry.AggQueryEntryType groupByOnline = aggQueryEntry.new AggQueryEntryType();
        groupByOnline.setGroupName("group_by_online");
        groupByOnline.setFieldName("online");
        groupByOnline.setAggType(EsConfig.AggQuery.TERMS);

        AggQueryEntry.AggQueryEntryType groupByClientid = aggQueryEntry.new AggQueryEntryType();
        groupByClientid.setGroupName("group_by_clientid");
        groupByClientid.setFieldName("clientid");
        groupByClientid.setAggType(EsConfig.AggQuery.TERMS);

        aggQueryEntry.getAggQueryList().add(maxByUpdateDate);
        aggQueryEntry.getAggQueryList().add(groupByOnline);
        aggQueryEntry.getAggQueryList().add(groupByClientid);

//        RestResult<AggResultAll> restResult = es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
//        System.out.println("!!!!!!!!!!!!!!!!!" + restResult.getData());

        RestResult<List<AggResultEntry>> restResult = es6ServiceImpl.aggQueryRequest(queryEntry, aggQueryEntry);
        System.out.println("!!!!!!!!!!!!!!!!!" + restResult.getData());
        List<AggResultEntry> list = restResult.getData();
        for(AggResultEntry aggResultEntry : list){
            System.out.println(aggResultEntry);
        }
    }

}
