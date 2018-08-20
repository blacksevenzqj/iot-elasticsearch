package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;
import com.thinkgem.elclient.elasticsearch.util.EsUtils;

public class TestFastJson {

    public static void main(String[] args) throws Exception{

        String str = "{\"db_id\":\"61d533a353624e03bcff1aae1a748d5e\",\"equipment_id\":\"8588ceaf5d70499e93fb1f824bc85ba1\",\"create_by\":\"1\",\"update_by\":\"1\",\"create_date\":\"2018-08-06 14:40:07\",\"update_date\":\"2018-08-06 14:40:07\",\"del_flag\":\"0\",\"column1\":\"-*++-.+\",\"column2\":\"-*++-.+\",\"column3\":\"854\",\"column4\":\"0\",\"column5\":\"--*'\",\"column6\":\".(\",\"column7\":\"0.0\",\"column8\":\"0.0\"}";
//       EquipmentData equipmentData = JSON.parseObject(str, new TypeReference<EquipmentData>(){
//        });
        EquipmentData equipmentData = JSON.parseObject(str, EquipmentData.class);
        System.out.println(equipmentData);

        System.out.println(EquipmentData.class.getField("createDate").get(equipmentData));

        String name = EquipmentData.class.getField("equipmentId").getName();
        System.out.println(name);

        EsUtils.Class2Array(equipmentData);

//        Test2 test2 = new Test2();
//        test2.name = "333";
//        test2.setAge("12");
//        System.out.println(test2);
//        System.out.println(test2.getAge());
//        System.out.println(JSON.toJSONString(test2));
    }

}
