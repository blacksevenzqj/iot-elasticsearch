package com.thinkgem.elclient.elasticsearch.service;

import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.client.EsClient;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.common.RestResult;
import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
import com.thinkgem.elclient.elasticsearch.entity.base.EsPageInfo;
import com.thinkgem.elclient.elasticsearch.entity.search.AggQueryEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultAll;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultEntry;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.elasticsearch.util.EsUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.*;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.aggregations.AggregationBuilder;
import org.elasticsearch.search.aggregations.AggregationBuilders;
import org.elasticsearch.search.aggregations.bucket.terms.TermsAggregationBuilder;
import org.elasticsearch.search.aggregations.metrics.max.MaxAggregationBuilder;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class Es6ServiceImpl {

    @Autowired
    EsClient esClient;

    /**
     * 新增索引
     */
    // 传入：子类POJO的Class
    public <T> RestResult createIndexMapping(Class<T> tClass) {
        esClient.createIndexMapping(tClass);
        return RestResult.getSuccessResult();
    }

// ***************************************************************************************************

    /**
     * 新增、修改、删除 文档：
     */
    // 新增文档：
    // 传入：子类POJO的Class
    public <T> RestResult createIndexDoc(Class<T> tClass, EsBaseEntity obj){
        try {
            IndexRequest indexRequest = new IndexRequest(
                    tClass.getAnnotation(Es6Index.class).indexName(),
                    tClass.getAnnotation(Es6Index.class).typeName()
            ).source(EsUtils.Class2Array(obj));
            esClient.createIndexDoc(indexRequest);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"新增文档失败");
    }
    // 更新文档：
    // 传入：子类POJO的Class
    public <T> RestResult upDateIndexDoc(Class<T> tClass, EsBaseEntity obj){
        try {
            UpdateRequest updateRequest = new UpdateRequest(
                    tClass.getAnnotation(Es6Index.class).indexName(),
                    tClass.getAnnotation(Es6Index.class).typeName(),
                    obj.getEsId()
            ).doc(EsUtils.Class2Array(obj));
            esClient.upDateIndexDoc(updateRequest);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"更新文档失败");
    }
    // 删除文档：
    // 传入：子类POJO的Class
    public <T> RestResult deleteIndexDoc(Class<T> tClass, String esId) {
        try {
            DeleteRequest request = new DeleteRequest(
                    tClass.getAnnotation(Es6Index.class).indexName(),
                    tClass.getAnnotation(Es6Index.class).typeName(),
                    esId);
            esClient.deleteIndexDoc(request);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"删除文档失败");
    }

    // 批量操作：
    // 传入：子类POJO的Class
    public <T> RestResult processDocBulk(Class<T> tClass, List<EsBaseEntity> createList, List<EsBaseEntity> upDateList, List<String> deleteList){
        BulkRequest request = new BulkRequest();
        try {
            if(createList != null && !createList.isEmpty()) {
                for (EsBaseEntity obj : createList) {
                    IndexRequest indexRequest = new IndexRequest(
                            tClass.getAnnotation(Es6Index.class).indexName(),
                            tClass.getAnnotation(Es6Index.class).typeName()
                    ).source(EsUtils.Class2Array(obj));
                    request.add(indexRequest);
                }
            }
            if(upDateList != null && !upDateList.isEmpty()) {
                for (EsBaseEntity obj : upDateList) {
                    UpdateRequest updateRequest = new UpdateRequest(
                            tClass.getAnnotation(Es6Index.class).indexName(),
                            tClass.getAnnotation(Es6Index.class).typeName(),
                            obj.getEsId()
                    ).doc(EsUtils.Class2Array(obj));
                    request.add(updateRequest);
                }
            }
            if(deleteList != null && !deleteList.isEmpty()) {
                for (String esId : deleteList) {
                    DeleteRequest deleteRequest = new DeleteRequest(
                            tClass.getAnnotation(Es6Index.class).indexName(),
                            tClass.getAnnotation(Es6Index.class).typeName(),
                            esId);
                    request.add(deleteRequest);
                }
            }
            esClient.processDocBulk(request);
            return RestResult.getSuccessResult();
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return RestResult.getFailResult(500,"新增文档失败");
    }

// ***************************************************************************************************

    /**
     * 查询
     */
    // 传入：子类POJO的Class
    public <T> RestResult<T> getById(Class<T> tClass, String id) {
        GetRequest getRequest = new GetRequest(
                tClass.getAnnotation(Es6Index.class).indexName(),
                tClass.getAnnotation(Es6Index.class).typeName(),
                id
        );
        return RestResult.getSuccessResult(esClient.getById(getRequest, tClass));
    }

    // 传入：子类POJO的Class
    public <T> RestResult<List<T>> searchTermByFiled(Class<T> tClass, String fieldName, String field, EsPageInfo esPageInfo, String orderField, String orderType) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(tClass.getAnnotation(Es6Index.class).indexName());
        searchRequest.types(tClass.getAnnotation(Es6Index.class).typeName());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.termQuery(fieldName, field));
        searchSourceBuilder.from(esPageInfo.getPageStart());
        searchSourceBuilder.size(esPageInfo.getPageSize());
        searchSourceBuilder.sort(EsUtils.createSortBuilder(tClass, orderField, orderType));
        searchRequest.source(searchSourceBuilder);
        return RestResult.getSuccessResult(esClient.search(searchRequest, tClass));
    }

    // 传入：子类POJO的Class
    public <T> RestResult<List<T>> searchMatchByField(Class<T> tClass, String fieldName, String field, EsPageInfo esPageInfo, String orderField, String orderType) {
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(tClass.getAnnotation(Es6Index.class).indexName());
        searchRequest.types(tClass.getAnnotation(Es6Index.class).typeName());
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(fieldName, field));
        searchSourceBuilder.from(esPageInfo.getPageStart());
        searchSourceBuilder.size(esPageInfo.getPageSize());
        searchSourceBuilder.sort(EsUtils.createSortBuilder(tClass, orderField, orderType));
        searchRequest.source(searchSourceBuilder);
        return RestResult.getSuccessResult(esClient.search(searchRequest, tClass));
    }

    public  <T> RestResult<List<T>> pageQueryRequest(QueryEntry<T> queryEntry){
        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(queryEntry.getTClass().getAnnotation(Es6Index.class).indexName());
        searchRequest.types(queryEntry.getTClass().getAnnotation(Es6Index.class).typeName());

        // 这个sourcebuilder就类似于查询语句中最外层的部分。包括查询分页的起始，
        // 查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 结果开始处
        if(queryEntry.getEsPageInfo() != null) {
            searchSourceBuilder.from(queryEntry.getEsPageInfo().getPageStart());
            // 查询结果终止处
            searchSourceBuilder.size(queryEntry.getEsPageInfo().getPageSize());
        }
        // 查询的等待时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        // bool，将查询合并
        BoolQueryBuilder boolBuilder = QueryBuilders.boolQuery();

        Map<String, Object> matchMap = queryEntry.getMatch();
        if(matchMap != null && !matchMap.isEmpty()){
            for (Map.Entry<String, Object> entry : matchMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null) {
                    MatchQueryBuilder  matchbuilder = QueryBuilders.matchQuery(entry.getKey(), entry.getValue());
                    boolBuilder.must(matchbuilder);
                }
            }
        }

        Map<String, Object> termMap = queryEntry.getTerm();
        if(termMap != null && !termMap.isEmpty()){
            for (Map.Entry<String, Object> entry : termMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null) {
                    TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(entry.getKey(), entry.getValue());
                    boolBuilder.filter(termQueryBuilder);
                }
            }
        }

        Map<String, Object[]> termsMap = queryEntry.getTerms();
        if(termsMap != null && !termsMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : termsMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    TermsQueryBuilder termsQueryBuilder = QueryBuilders.termsQuery(entry.getKey(), entry.getValue());
                    boolBuilder.filter(termsQueryBuilder);
                }
            }
        }

        Map<String, Object[]> rangeMap = queryEntry.getRange();
        if(rangeMap != null && !rangeMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : rangeMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length == 2) {
                    RangeQueryBuilder rangeQueryBuilder = QueryBuilders.rangeQuery(entry.getKey());
                    rangeQueryBuilder.gte(entry.getValue()[0]);
                    rangeQueryBuilder.lte(entry.getValue()[1]);
                    boolBuilder.filter(rangeQueryBuilder);
                }
            }
        }

        Map<String, Object[]> shouldTermMap = queryEntry.getShouldTerm();
        if(shouldTermMap != null && !shouldTermMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : shouldTermMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    for(int i = 0; i < entry.getValue().length; i++){
                        TermQueryBuilder termQueryBuilder = QueryBuilders.termQuery(entry.getKey(), entry.getValue()[i]);
                        boolBuilder.should(termQueryBuilder);
                        boolBuilder.minimumShouldMatch(1);
                    }
                }
            }
        }

        // 排序
        searchSourceBuilder.sort(EsUtils.createSortBuilder(queryEntry.getTClass(), queryEntry.getOrderField(), queryEntry.getOrderType()));
        searchSourceBuilder.query(boolBuilder);
        log.info(searchSourceBuilder.toString());
        return RestResult.getSuccessResult(esClient.search(searchRequest.source(searchSourceBuilder), queryEntry.getTClass()));
    }


    // 传入：子类POJO的Class
    public <T> RestResult<List<T>> searchMatchScrollByField(Class<T> tClass, String fieldName, String field, int pageSize) {
        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
        SearchRequest searchRequest = new SearchRequest(tClass.getAnnotation(Es6Index.class).indexName());
        searchRequest.scroll(scroll);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchQuery(fieldName, field));
        searchSourceBuilder.size(pageSize == 0 || pageSize < 0 ? 10 : pageSize);
        searchRequest.source(searchSourceBuilder);
        return RestResult.getSuccessResult(esClient.searchScroll(searchRequest, tClass, scroll));
    }



    public <T> RestResult aggQueryRequest(QueryEntry<T> queryEntry, AggQueryEntry aggQueryEntry){

        if(aggQueryEntry == null || aggQueryEntry.getAggQueryList().isEmpty()){
            return RestResult.getFailResult(500, "查询参数为NULL");
        }

        SearchRequest searchRequest = new SearchRequest();
        searchRequest.indices(queryEntry.getTClass().getAnnotation(Es6Index.class).indexName());
        searchRequest.types(queryEntry.getTClass().getAnnotation(Es6Index.class).typeName());

        // 这个sourcebuilder就类似于查询语句中最外层的部分。包括查询分页的起始，
        // 查询语句的核心，查询结果的排序，查询结果截取部分返回等一系列配置
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        if(queryEntry.getEsPageInfo() != null) {
            // 结果开始处
            searchSourceBuilder.from(queryEntry.getEsPageInfo().getPageStart());
            // 查询结果终止处
            searchSourceBuilder.size(queryEntry.getEsPageInfo().getPageSize());
        }
        // 查询的等待时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));

        TermsQueryBuilder termsQueryBuilder = null;
        Map<String, Object[]> termsMap = queryEntry.getTerms();
        if(termsMap != null && !termsMap.isEmpty()){
            for (Map.Entry<String, Object[]> entry : termsMap.entrySet()) {
                log.info("Key = " + entry.getKey() + ", Value = " + entry.getValue());
                if(StringUtils.isNotBlank(entry.getKey()) && entry.getValue() != null && entry.getValue().length > 0) {
                    termsQueryBuilder = QueryBuilders.termsQuery(entry.getKey(), entry.getValue());
                }
            }
        }

//        MaxAggregationBuilder updateDateBuilder = AggregationBuilders.max("max_by_updateDate").field("updateDate");
//        TermsAggregationBuilder onlineBuilder = AggregationBuilders.terms("group_by_online").field("online");
//        onlineBuilder.subAggregation(updateDateBuilder);
//        TermsAggregationBuilder clientidBuilder = AggregationBuilders.terms("group_by_clientid").field("clientid");
//        clientidBuilder.subAggregation(onlineBuilder);

        AggregationBuilder finalAggregationBuilder;
        List<AggregationBuilder> aggregationBuilderList = new ArrayList<>();
        List<AggQueryEntry.AggQueryEntryType> aggQueryList = aggQueryEntry.getAggQueryList();
        for(AggQueryEntry.AggQueryEntryType aggQueryEntryType : aggQueryList){
            if(EsConfig.AggQuery.MAX.equalsIgnoreCase(aggQueryEntryType.getAggType())){
                MaxAggregationBuilder maxDateBuilder = AggregationBuilders.max(aggQueryEntryType.getGroupName()).field(aggQueryEntryType.getFieldName());
                aggregationBuilderList.add(maxDateBuilder);
            }else if(EsConfig.AggQuery.TERMS.equalsIgnoreCase(aggQueryEntryType.getAggType())){
                TermsAggregationBuilder termsBuilder = AggregationBuilders.terms(aggQueryEntryType.getGroupName()).field(aggQueryEntryType.getFieldName());
                aggregationBuilderList.add(termsBuilder);
            }
        }
        if(aggregationBuilderList.size() > 1) {
            for (int i = 0, j = 1; j < aggregationBuilderList.size(); i++,j++) {
                aggregationBuilderList.get(j).subAggregation(aggregationBuilderList.get(i));
            }
            finalAggregationBuilder = aggregationBuilderList.get(aggregationBuilderList.size()-1);
        }else{
            finalAggregationBuilder = aggregationBuilderList.get(0);
        }

        // 排序
        searchSourceBuilder.sort(EsUtils.createSortBuilder(queryEntry.getTClass(), queryEntry.getOrderField(), queryEntry.getOrderType()));
        searchSourceBuilder.query(termsQueryBuilder);
        searchSourceBuilder.aggregation(finalAggregationBuilder);
        searchSourceBuilder.size(0);
        log.info(searchSourceBuilder.toString());

        AggResultAll temp = esClient.aggSearch(searchRequest.source(searchSourceBuilder));
        return RestResult.getSuccessResult(getResultAggResult(temp));
    }

    private List<AggResultEntry> getResultAggResult(AggResultAll temp){
        List<AggResultEntry> aggResultEntryList = new ArrayList<>();
        if(temp != null && !temp.getAgg().isEmpty()){
            List<AggResultAll> list = temp.getAgg();
            for(AggResultAll agg : list){
                if(StringUtils.isNoneBlank(agg.getKeyName())){
                    AggResultEntry aggResultEntry = new AggResultEntry();
                    aggResultEntry.setClientId(agg.getKeyName());
                    aggResultEntry.setClientCount(agg.getKeyCount());
                    aggResultEntryList.add(aggResultEntry);
                    if(!agg.getAgg().isEmpty()){
                        getResultAggSubResult(aggResultEntry, agg.getAgg());
                    }
                }
            }
        }
        return aggResultEntryList;
    }

    private void getResultAggSubResult(AggResultEntry aggResultEntry, List<AggResultAll> list){
        for(AggResultAll agg : list){
            if(EsConfig.AggQuery.CustomizeGroupName.ON_LINE.equalsIgnoreCase(agg.getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOnLine(agg.getKeyCount());
            }else if(EsConfig.AggQuery.CustomizeGroupName.OFF_LINE.equalsIgnoreCase(agg.getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOffLine(agg.getKeyCount());
            }else if(EsConfig.AggQuery.CustomizeGroupName.MAX_UPDATE.equalsIgnoreCase(agg.getKeyName()) &&
                    EsConfig.AggQuery.CustomizeGroupName.ON_LINE.equalsIgnoreCase(agg.getParent().getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOnLineMaxUpDate(agg.getKeyMaxDate());
            }else if(EsConfig.AggQuery.CustomizeGroupName.MAX_UPDATE.equalsIgnoreCase(agg.getKeyName()) &&
                    EsConfig.AggQuery.CustomizeGroupName.OFF_LINE.equalsIgnoreCase(agg.getParent().getKeyName())){
                aggResultEntry.getAggResultSubEntry().setOffLineMaxUpDate(agg.getKeyMaxDate());
            }
            if(!agg.getAgg().isEmpty()){
                getResultAggSubResult(aggResultEntry, agg.getAgg());
            }
        }
    }

}
