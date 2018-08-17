package com.thinkgem.elclient.test;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.entity.group.EquipmentData;

import java.lang.reflect.Field;

public class TestFastJson {

    public static void main(String[] args){
        String str = "{\"db_id\":\"61d533a353624e03bcff1aae1a748d5e\",\"equipment_id\":\"8588ceaf5d70499e93fb1f824bc85ba1\",\"create_by\":\"1\",\"update_by\":\"1\",\"create_date\":\"2018-08-06 14:40:07\",\"update_date\":\"2018-08-06 14:40:07\",\"del_flag\":\"0\",\"column1\":\"-*++-.+\",\"column2\":\"-*++-.+\",\"column3\":\"854\",\"column4\":\"0\",\"column5\":\"--*'\",\"column6\":\".(\",\"column7\":\"0.0\",\"column8\":\"0.0\"}";
//        EquipmentData equipmentData = JSON.parseObject(str, new TypeReference<EquipmentData>(){
//        });
        EquipmentData equipmentData = JSON.parseObject(str, EquipmentData.class);
        System.out.println(equipmentData);

        EquipmentData sss = new EquipmentData();
        System.out.println(sss);

        Field[] fields = EquipmentData.class.getFields();
        for(Field field : fields) {
            System.out.println(field.getAnnotation(EsFieldData.class).analyzerType().getKey());
        }
    }

}
