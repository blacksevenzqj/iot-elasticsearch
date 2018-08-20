package com.thinkgem.elclient.test;

import com.thinkgem.elclient.elasticsearch.client.EsClient;
import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.EsHotNew;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.service.EsServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestEs {

    @Autowired
    EsClient esClient;

    @Autowired
    EsServiceImpl esServiceImpl;

    @Test
    public void createIndexMapping() throws Exception{
//        esClient.createIndexMapping(EsHotNew.class);
        esServiceImpl.createIndexMapping(EsHotNew.class);

        Thread.currentThread().sleep(10000L);
    }

    @Test
    public void save() throws Exception{
        EsHotNew obj = new EsHotNew();
        obj.setTitle("feiji f35");
        obj.setDbId("444");
        obj.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
//        obj.setCreateDate("2018-03-20");
        obj.setServiceUrl("http://www.baidu.com");
        esServiceImpl.createIndexDoc(EsHotNew.class, obj);

        Thread.currentThread().sleep(3000L);
    }
    @Test
    public void upDate() throws Exception{
        EsHotNew obj = new EsHotNew();
        obj.setTitle("taideng");
        obj.setDbId("111");
        obj.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        obj.setServiceUrl("http://www.aiqiyi.com");
        esServiceImpl.upDateIndexDoc(EsHotNew.class, obj);

        Thread.currentThread().sleep(3000L);
    }
    @Test
    public void deleteIndexDoc() throws Exception{
        EsHotNew obj = new EsHotNew();
        obj.setDbId("222");
        esServiceImpl.deleteIndexDoc(EsHotNew.class, obj);

        Thread.currentThread().sleep(3000L);
    }

    @Test
    public void processDocBulk() throws Exception{
        List<EsBaseEntity> list = new ArrayList<>();
        EsHotNew obj = new EsHotNew();
        obj.setTitle("feiji");
        obj.setDbId("111");
        obj.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        obj.setServiceUrl("http://www.baidu.com");
        EsHotNew obj2 = new EsHotNew();
        obj2.setTitle("dapao");
        obj2.setDbId("222");
        obj2.setCreateDate(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        obj2.setServiceUrl("http://www.google.com");
        list.add(obj);
        list.add(obj2);
        esServiceImpl.processDocBulk(EsHotNew.class, list, null, null);

        Thread.currentThread().sleep(3000L);
    }

    @Test
    public void getById() throws Exception {
//        RestResult<EsHotNew> restResult = esServiceImpl.getById(EsHotNew.class, "111");
//        System.out.println(restResult.getData());

        RestResult<EquipmentData> restResult = esServiceImpl.getById(EquipmentData.class, "61d533a353624e03bcff1aae1a748d5e");
        System.out.println(restResult.getData());
    }
    @Test
    public void searchMatchByTitle() throws Exception {
        RestResult<List<EsHotNew>> restResult = esServiceImpl.searchMatchByTitle(EsHotNew.class, "feiji",
                0, 5, null, null);
        for(EsHotNew obj : restResult.getData()){
            System.out.println(obj);
        }
    }
    @Test
    public void searchMatchScrollByTitle() throws Exception {
        RestResult<List<EsHotNew>> restResult = esServiceImpl.searchMatchScrollByTitle(EsHotNew.class, "feiji", 1);
        for(EsHotNew obj : restResult.getData()){
            System.out.println(obj);
        }
    }

}
