package com.thinkgem.elclient.elasticsearch.client;

import com.alibaba.fastjson.JSON;
import com.thinkgem.elclient.elasticsearch.annotation.Es6Index;
import com.thinkgem.elclient.elasticsearch.annotation.EsFieldData;
import com.thinkgem.elclient.elasticsearch.common.AnalyzerConfigEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import com.thinkgem.elclient.elasticsearch.config.ESClientDecorator;
import com.thinkgem.elclient.elasticsearch.entity.search.AggResultAll;
import com.thinkgem.elclient.elasticsearch.entity.search.QueryEntry;
import com.thinkgem.elclient.utils.DateUtils;
import com.thinkgem.elclient.utils.PageUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.ActionListener;
import org.elasticsearch.action.DocWriteRequest;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.admin.indices.create.CreateIndexRequest;
import org.elasticsearch.action.admin.indices.create.CreateIndexResponse;
import org.elasticsearch.action.bulk.BulkItemResponse;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.*;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.rest.RestStatus;
import org.elasticsearch.search.Scroll;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.aggregations.Aggregation;
import org.elasticsearch.search.aggregations.Aggregations;
import org.elasticsearch.search.aggregations.bucket.terms.Terms;
import org.elasticsearch.search.aggregations.metrics.max.Max;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.util.*;

@Component
@Slf4j
public class EsClient {

    @Autowired
    private RestHighLevelClient client;

    /**
     * 传入：子类POJO的Class
     */
    public <T> void createIndexMapping(Class<T> tClass){
        CreateIndexRequest request = new CreateIndexRequest(tClass.getAnnotation(Es6Index.class).indexName());
        request.settings(Settings.builder()
                .put(EsConfig.NUMBER_OF_SHARDS, tClass.getAnnotation(Es6Index.class).numberOfShards())
                .put(EsConfig.NUMBER_OF_REPLICAS, tClass.getAnnotation(Es6Index.class).numberOfReplicas()));

        Map mapField = new HashMap();
        Field[] fields = tClass.getFields();
        for(Field field : fields) {
            if (field.getAnnotation(EsFieldData.class) == null || StringUtils.isBlank(field.getAnnotation(EsFieldData.class).dataName())) {
                mapField.put(field.getName(), ESClientDecorator.getMapType().get(EsConfig.El_STRING));
            } else {
                if(StringUtils.isNotBlank(field.getAnnotation(EsFieldData.class).analyzerType().getKey()) &&
                        field.getAnnotation(EsFieldData.class).analyzerType() != AnalyzerConfigEnum.NULL &&
                        StringUtils.isNotBlank(field.getAnnotation(EsFieldData.class).analyzerSearchType().getKey()) &&
                        field.getAnnotation(EsFieldData.class).analyzerSearchType() != AnalyzerConfigEnum.NULL &&
                        EsConfig.El_STRING.equalsIgnoreCase(field.getAnnotation(EsFieldData.class).dataName())){
                    Map dataMap = ESClientDecorator.getMapType().get(field.getAnnotation(EsFieldData.class).dataName());
                    Map analyzerIkMap = ESClientDecorator.getMapType().get(field.getAnnotation(EsFieldData.class).analyzerType().getKey());
                    Map analyzerIkSearchMap = ESClientDecorator.getMapType().get(field.getAnnotation(EsFieldData.class).analyzerSearchType().getKey());
                    dataMap.putAll(analyzerIkMap);
                    dataMap.putAll(analyzerIkSearchMap);
                    mapField.put(field.getName(), dataMap);
                }else {
                    mapField.put(field.getName(), ESClientDecorator.getMapType().get(field.getAnnotation(EsFieldData.class).dataName()));
                }
            }
        }

        Map mapProperties = new HashMap();
        mapProperties.put(EsConfig.PROPERTIES, mapField);

        Map mapType = new HashMap();
        mapType.put(tClass.getAnnotation(Es6Index.class).typeName(), mapProperties);
        request.mapping(tClass.getAnnotation(Es6Index.class).typeName(), mapType);

        ActionListener<CreateIndexResponse> listener = new ActionListener<CreateIndexResponse>() {
            @Override
            public void onResponse(CreateIndexResponse createIndexResponse) {
                boolean acknowledged = createIndexResponse.isAcknowledged();
                boolean shardsAcknowledged = createIndexResponse.isShardsAcknowledged();
                log.info(String.valueOf(acknowledged) + ":" + String.valueOf(shardsAcknowledged));
            }
            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };
        client.indices().createAsync(request, listener);
    }

// ***************************************************************************************************

    // 新增文档：如果 _id 相同，则为 更新文档 操作
    public void createIndexDoc(IndexRequest indexRequest){
        ActionListener<IndexResponse> listener = new ActionListener<IndexResponse>(){
            @Override
            public void onResponse(IndexResponse indexResponse) {
                ClientUtils.responseProcess(indexResponse);
            }
            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };
        client.indexAsync(indexRequest, listener);
    }
    // 更新文档：如果 _id 不存在，则为 新增文档 操作
    public void upDateIndexDoc(UpdateRequest updateRequest){
        ActionListener<UpdateResponse> listener = new ActionListener<UpdateResponse>() {
            @Override
            public void onResponse(UpdateResponse updateResponse) {
                ClientUtils.responseProcess(updateResponse);
            }
            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };
        client.updateAsync(updateRequest, listener);
    }
    // 删除文档：
    public void deleteIndexDoc(DeleteRequest deleteRequest){
        ActionListener<DeleteResponse> listener = new ActionListener<DeleteResponse>() {
            @Override
            public void onResponse(DeleteResponse deleteResponse) {
                ClientUtils.responseProcess(deleteResponse);
            }
            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };
        client.deleteAsync(deleteRequest, listener);
    }

    // 批量操作：
    public void processDocBulk(BulkRequest request){
        ActionListener<BulkResponse> listener = new ActionListener<BulkResponse>() {
            @Override
            public void onResponse(BulkResponse bulkResponse) {
                for (BulkItemResponse bulkItemResponse : bulkResponse) {
                    if(bulkItemResponse.isFailed()){
                        BulkItemResponse.Failure failure = bulkItemResponse.getFailure();
                        log.error(failure.toString());
                    }else{
                        DocWriteResponse itemResponse = bulkItemResponse.getResponse();
                        if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.INDEX
                                || bulkItemResponse.getOpType() == DocWriteRequest.OpType.CREATE) {
                            IndexResponse indexResponse = (IndexResponse) itemResponse;
                            ClientUtils.responseProcess(indexResponse);
                        } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.UPDATE) {
                            UpdateResponse updateResponse = (UpdateResponse) itemResponse;
                            ClientUtils.responseProcess(updateResponse);
                        } else if (bulkItemResponse.getOpType() == DocWriteRequest.OpType.DELETE) {
                            DeleteResponse deleteResponse = (DeleteResponse) itemResponse;
                            ClientUtils.responseProcess(deleteResponse);
                        }
                    }
                }
            }
            @Override
            public void onFailure(Exception e) {
                log.error(e.getMessage());
            }
        };
        client.bulkAsync(request, listener);
    }

// ***************************************************************************************************

    /**
     * 传入：子类POJO的Class
     */
    public <T> T getById(GetRequest getRequest, Class<T> tClass) {
        log.info(getRequest.index() + ":" + getRequest.type() + ":" + getRequest.id());
        try {
            GetResponse getResponse = client.get(getRequest);
//            String index = getResponse.getIndex();
//            String type = getResponse.getType();
//            String id = getResponse.getId();
            if (getResponse.isExists()) {
                log.info(getResponse.toString());
//                long version = getResponse.getVersion();
//                Map<String, Object> sourceAsMap = getResponse.getSourceAsMap();
//                byte[] sourceAsBytes = getResponse.getSourceAsBytes();
                String sourceAsString = getResponse.getSourceAsString();
                return JSON.parseObject(sourceAsString, tClass);
            }
        }catch (ElasticsearchException e) {
            if (e.status() == RestStatus.NOT_FOUND) {
                log.error("GET Index Not_Exists" + ":" + getRequest.index() + ":" + getRequest.type() + ":" + getRequest.id() + ":" + getRequest.version());
            }else if(e.status() == RestStatus.CONFLICT){
                log.error("GET Version Different " + ":" + getRequest.index() + ":" + getRequest.type() + ":" + getRequest.id() + ":" + getRequest.version());
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return null;
    }
    public <T> List<T> search(SearchRequest request, Class<T> tClass) {
        List<T> list = new ArrayList<>();
        try {
            SearchResponse response = client.search(request);
            if (response.getHits() != null) {
                response.getHits().forEach(item -> list.add(JSON.parseObject(item.getSourceAsString(), tClass)));
            }
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return list;
    }
    public <T> PageUtils<T> search(SearchRequest request, QueryEntry<T> queryEntry) {
        List<T> list = new ArrayList<>();
        PageUtils<T> pageUtils = null;
        try {
            SearchResponse response = client.search(request);
            if (response.getHits() != null) {
                response.getHits().forEach(item -> list.add(JSON.parseObject(item.getSourceAsString(), queryEntry.getTClass())));
            }
            pageUtils = new PageUtils<>(list, response.getHits().getTotalHits(), queryEntry.getEsPageInfo().getPageSize(), queryEntry.getEsPageInfo().getPageNum());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return pageUtils;
    }
    public <T> List<T> searchScroll(SearchRequest searchRequest, Class<T> tClass, Scroll scroll) {
        List<T> list = new ArrayList<>();
        try {
            SearchResponse searchResponse = client.search(searchRequest);
            String scrollId = searchResponse.getScrollId();
            SearchHit[] searchHits = searchResponse.getHits().getHits();
            if(searchHits != null && searchHits.length > 0){
                for(SearchHit searchHit : searchHits){
                    list.add(JSON.parseObject(searchHit.getSourceAsString(), tClass));
                }
            }
            while (searchHits != null && searchHits.length > 0) {
                SearchScrollRequest scrollRequest = new SearchScrollRequest(scrollId);
                scrollRequest.scroll(scroll);
                searchResponse = client.searchScroll(scrollRequest);
                scrollId = searchResponse.getScrollId();
                searchHits = searchResponse.getHits().getHits();
                if(searchHits != null && searchHits.length > 0){
                    for(SearchHit searchHit : searchHits){
                        list.add(JSON.parseObject(searchHit.getSourceAsString(), tClass));
                    }
                }
            }
            ClearScrollRequest clearScrollRequest = new ClearScrollRequest();
            clearScrollRequest.addScrollId(scrollId);
            ClearScrollResponse clearScrollResponse = client.clearScroll(clearScrollRequest);
            boolean succeeded = clearScrollResponse.isSucceeded();
            log.info("clearScrollResponse.isSucceeded() is " + succeeded);
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return list;
    }

    public AggResultAll aggSearch(SearchRequest searchRequest) {
        AggResultAll aggResult = new AggResultAll();
        try {
            SearchResponse searchResponse = client.search(searchRequest);
            Aggregations aggs = searchResponse.getAggregations();
            Map<String, Aggregation> map = aggs.getAsMap();
            getAggregations(map, aggResult);
            log.info(aggResult.toString());
        }catch (Exception e){
            log.error(e.getMessage());
        }
        return aggResult;
    }

    private void getAggregations(Map<String, Aggregation> map,  AggResultAll aggResult){
        for (Map.Entry<String, Aggregation> entry : map.entrySet()) {
            log.info("KeyOne = " + entry.getKey() + ", Value = " + entry.getValue());
            aggResult.setGroupName(entry.getKey());
            if(entry.getValue() instanceof Terms) {
                Terms byStateAggs = (Terms) entry.getValue();
                List<? extends Terms.Bucket> aggList = byStateAggs.getBuckets();//获取bucket数组里所有数据
                aggResult.setGroupCount(aggList.size());
                for (Terms.Bucket bucket : aggList) {
                    log.info("keyTwo:" + bucket.getKeyAsString() + ",docCount:" + bucket.getDocCount());
                    AggResultAll temp = new AggResultAll();
                    temp.setKeyName(bucket.getKeyAsString());
                    temp.setKeyCount(bucket.getDocCount());
                    temp.setParent(aggResult); // 设置父节点
                    aggResult.getAgg().add(temp);
                    Aggregations aggregations = bucket.getAggregations();
                    if (aggregations.getAsMap() != null && !aggregations.getAsMap().isEmpty()) {
                        getSubAggregations(aggregations.getAsMap(), temp);
                    }
                }
            }else if(entry.getValue() instanceof Max){
                Max byStateAggs = (Max)entry.getValue();
                aggResult.setKeyMaxValue(byStateAggs.getValue());
            }
        }
    }

    private void getSubAggregations(Map<String, Aggregation> map,  AggResultAll aggResult){
        for (Map.Entry<String, Aggregation> entry : map.entrySet()) {
            log.info("KeySubOne = " + entry.getKey() + ", Value = " + entry.getValue());
            AggResultAll sub = new AggResultAll();
            sub.setParent(aggResult); // 设置父节点
            aggResult.getAgg().add(sub);
            if(entry.getValue() instanceof Max && EsConfig.AggQuery.CustomizeGroupName.MAX_UPDATE.equalsIgnoreCase(entry.getKey())){
                sub.setKeyName(entry.getKey());
                sub.setKeyCount(Long.valueOf(map.size()));
                Max byStateAggs = (Max)entry.getValue();
                sub.setKeyMaxDate(DateUtils.getDateStrByUtcDouble(byStateAggs.getValue()));
            }else if(entry.getValue() instanceof Max && EsConfig.AggQuery.CustomizeGroupName.MAX_FIELD.equalsIgnoreCase(entry.getKey())){
                sub.setKeyName(entry.getKey());
                sub.setKeyCount(Long.valueOf(map.size()));
                Max byStateAggs = (Max)entry.getValue();
                sub.setKeyMaxValue(byStateAggs.getValue());
            } else if(entry.getValue() instanceof Terms){
                Terms byStateAggs = (Terms)entry.getValue();
                List<? extends Terms.Bucket> aggList = byStateAggs.getBuckets();//获取bucket数组里所有数据
                sub.setGroupName(entry.getKey());
                sub.setGroupCount(aggList.size());
                for (Terms.Bucket bucket : aggList) {
                    log.info("keySubTwo:" + bucket.getKeyAsString()+",docCount:" + bucket.getDocCount());
                    AggResultAll temp = new AggResultAll();
                    temp.setKeyName(bucket.getKeyAsString());
                    temp.setKeyCount(bucket.getDocCount());
                    temp.setParent(sub); // 设置父节点
                    sub.getAgg().add(temp);
                    Aggregations aggregations = bucket.getAggregations();
                    if(aggregations.getAsMap() != null && !aggregations.getAsMap().isEmpty()){
                        getSubAggregations(aggregations.getAsMap(), temp);
                    }
                }
            }
        }
    }


}
