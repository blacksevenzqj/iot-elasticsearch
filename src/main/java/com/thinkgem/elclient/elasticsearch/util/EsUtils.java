package com.thinkgem.elclient.elasticsearch.util;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.search.sort.FieldSortBuilder;
import org.elasticsearch.search.sort.ScoreSortBuilder;
import org.elasticsearch.search.sort.SortBuilder;
import org.elasticsearch.search.sort.SortOrder;

import java.lang.reflect.Field;

@Slf4j
public class EsUtils {

    public static String[] Class2Array(Object obj) throws Exception{
        Field[] filds = obj.getClass().getFields();
        String[] strs = new String[filds.length * 2];
        for(int i=0,j=0; i<filds.length; i++,j++){
            strs[j] = filds[i].getName();
            strs[++j] = String.valueOf(filds[i].get(obj));
        }
        return strs;
    }

    public static <T> SortBuilder createSortBuilder(Class<T> tClass, String orderField, String orderType){
        SortBuilder sortBuilder;
        SortOrder sortOrder;
        if("asc".equalsIgnoreCase(orderType)){
            sortOrder = SortOrder.ASC;
        }else{
            sortOrder = SortOrder.DESC;
        }
        try {
            if (StringUtils.isBlank(orderField)) {
                sortBuilder = new FieldSortBuilder("create_date").order(sortOrder);
            } else {
                sortBuilder = new FieldSortBuilder(orderField).order(sortOrder);
            }
        }catch (Exception e){
            log.error(e.getMessage());
            sortBuilder = new ScoreSortBuilder().order(sortOrder);
        }
        return sortBuilder;
    }

}
