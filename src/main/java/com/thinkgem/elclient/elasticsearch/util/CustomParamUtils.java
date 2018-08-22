package com.thinkgem.elclient.elasticsearch.util;

import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class CustomParamUtils {

    public static <T> QueryEntry<T>  getQueryEntry(String[] clientIds, Class<T> tClass) throws Exception{
        Map<String, Object[]> termsMap = new HashMap<>();
        Object[] obj = new Object[clientIds.length];
        for(int i = 0; i< clientIds.length; i ++){
            obj[i] = clientIds[i];
        }

        QueryEntry queryEntry = new QueryEntry();
        queryEntry.setTClass(tClass);
        Field[] filds = tClass.getFields();
        for(int i = 0; i < filds.length; i++){
            if(EsConfig.FIELD_QUERY_TYPE.SORT_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elQueryDesc())){
                String elFieldOrderName = filds[i].getAnnotation(EsFieldData.class).elName();
                queryEntry.setOrderField(elFieldOrderName);
            }else if(EsConfig.FIELD_QUERY_TYPE.QUERY_TERM_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elQueryDesc())){
                String elFieldName = filds[i].getAnnotation(EsFieldData.class).elName();
                termsMap.put(elFieldName, obj);
                queryEntry.setTerms(termsMap);
            }
        }
        return queryEntry;
    }

    public static <T> AggQueryEntry getAggQueryEntry(Class<T> tClass) throws Exception{
        AggQueryEntry aggQueryEntry = new AggQueryEntry();
        Field[] filds = tClass.getFields();
        for(int i = 0; i < filds.length; i++){
            if(EsConfig.AggQuery.MAX.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elAggType().getAggType())){
                AggQueryEntry.AggQueryEntryType maxField = aggQueryEntry.new AggQueryEntryType();
                createAggQueryEntryType(filds[i], maxField);
                aggQueryEntry.getAggQueryList().add(maxField);
            }else if(EsConfig.AggQuery.TERMS.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elAggType().getAggType())){
                AggQueryEntry.AggQueryEntryType groupField = aggQueryEntry.new AggQueryEntryType();
                createAggQueryEntryType(filds[i], groupField);
                aggQueryEntry.getAggQueryList().add(groupField);
            }
        }
        Collections.sort(aggQueryEntry.getAggQueryList());
        return aggQueryEntry;
    }

    private static void createAggQueryEntryType(Field field, AggQueryEntry.AggQueryEntryType aggQueryEntryType){
        String elFieldGroupName = field.getAnnotation(EsFieldData.class).elName();
        String groupName = field.getAnnotation(EsFieldData.class).elAggType().getGroupName();
        String aggType = field.getAnnotation(EsFieldData.class).elAggType().getAggType();
        Integer aggOrder = field.getAnnotation(EsFieldData.class).elAggType().getAggOrder();
        aggQueryEntryType.setGroupName(groupName);
        aggQueryEntryType.setFieldName(elFieldGroupName);
        aggQueryEntryType.setAggType(aggType);
        aggQueryEntryType.setAggOrder(aggOrder);
    }



}
