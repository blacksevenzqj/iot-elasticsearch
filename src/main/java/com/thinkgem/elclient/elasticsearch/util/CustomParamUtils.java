package com.thinkgem.elclient.elasticsearch.util;

import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

@Slf4j
public class CustomParamUtils {

    public static <T> QueryEntry<T>  getQueryEntry(Class<T> tClass, Map<String, Object> values, Map<String, Object[]> termsValues, Map<String, Object[]> rangeValues, Map<String, Object[]> shouldTermValues) throws Exception{
        QueryEntry<T> queryEntry = new QueryEntry<T>();
        queryEntry.setTClass(tClass);

        Map<String, Object> termMap = null;
        Map<String, Object[]> termsMap = null;
        Map<String, Object[]> rangeMap = null;
        Map<String, Object[]> shouldMap = null;
        if(values != null && !values.isEmpty()){
            termMap = new HashMap<>();
        }else if(termsValues != null && !termsValues.isEmpty()){
            termsMap = new HashMap<>();
        }else if(rangeValues != null && !rangeValues.isEmpty()){
            rangeMap = new HashMap<>();
        }else if(shouldTermValues != null && !shouldTermValues.isEmpty()){
            shouldMap = new HashMap<>();
        }

        Field[] filds = tClass.getFields();
        for(int i = 0; i < filds.length; i++){
            if(EsConfig.FIELD_QUERY_TYPE.SORT_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elSortType().getSortType())){
                String elFieldOrderName = getElFieldName(filds[i]);
                queryEntry.setSortField(elFieldOrderName);
                queryEntry.setSortType(filds[i].getAnnotation(EsFieldData.class).elSortType().getSortDesc());
            }else if(EsConfig.FIELD_QUERY_TYPE.QUERY_TERM_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elQueryType().getQueryType()) &&
                    values != null && !values.isEmpty()){
                setMapValue(filds[i], values, termMap);
            }else if(EsConfig.FIELD_QUERY_TYPE.QUERY_TERMS_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elQueryType().getQueryType()) &&
                    termsValues != null && !termsValues.isEmpty()){
                setMapValue(filds[i], termsValues, termsMap);
            }else if(EsConfig.FIELD_QUERY_TYPE.QUERY_RANGE_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elQueryType().getQueryType()) &&
                    rangeValues != null && !rangeValues.isEmpty()){
                setMapValue(filds[i], rangeValues, rangeMap);
            }else if(EsConfig.FIELD_QUERY_TYPE.QUERY_SHOULD_TERM_TYPE.equalsIgnoreCase(filds[i].getAnnotation(EsFieldData.class).elQueryType().getQueryType()) &&
                    shouldTermValues != null && !shouldTermValues.isEmpty()){
                setMapValue(filds[i], shouldTermValues, shouldMap);
            }
        }

        if(values != null && !values.isEmpty()){
            queryEntry.setTerm(termMap);
        }else if(termsValues != null && !termsValues.isEmpty()){
            queryEntry.setTerms(termsMap);
        }else if(rangeValues != null && !rangeValues.isEmpty()){
            queryEntry.setRange(rangeMap);
        }else if(shouldTermValues != null && !shouldTermValues.isEmpty()){
            queryEntry.setShouldTerm(shouldMap);
        }

        return queryEntry;
    }

    private static void setMapValue(Field field, Map paramMap, Map resultMap){
        String queryFieldName =  field.getAnnotation(EsFieldData.class).elQueryType().getQueryFieldName();
        Object value = paramMap.get(queryFieldName);
        if(value != null){
            String elFieldName = getElFieldName(field);
            resultMap.put(elFieldName, value);
        }
    }

    private static String getElFieldName(Field field){
        String elFieldName = field.getAnnotation(EsFieldData.class).elName();
        if (StringUtils.isBlank(elFieldName)) {
            elFieldName = field.getName();
        }
        return elFieldName;
    }

// ===============================================================================================================================================================

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
        String elFieldName = field.getAnnotation(EsFieldData.class).elName();
        if (StringUtils.isBlank(elFieldName)) {
            elFieldName = field.getName();
        }
        String groupName = field.getAnnotation(EsFieldData.class).elAggType().getGroupName();
        String aggType = field.getAnnotation(EsFieldData.class).elAggType().getAggType();
        Integer aggOrder = field.getAnnotation(EsFieldData.class).elAggType().getAggOrder();
        aggQueryEntryType.setGroupName(groupName);
        aggQueryEntryType.setFieldName(elFieldName);
        aggQueryEntryType.setAggType(aggType);
        aggQueryEntryType.setAggOrder(aggOrder);
    }



}
