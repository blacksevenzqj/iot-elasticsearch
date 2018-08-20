//package com.thinkgem.elclient.elasticsearch.service;
//
//import com.thinkgem.elclient.elasticsearch.annotation.EsIndex;
//import com.thinkgem.elclient.elasticsearch.annotation.EsType;
//import com.thinkgem.elclient.elasticsearch.client.EsClient;
//import com.thinkgem.elclient.elasticsearch.common.EsConfig;
//import com.thinkgem.elclient.elasticsearch.common.RestResult;
//import com.thinkgem.elclient.elasticsearch.entity.base.EsBaseEntity;
//import com.thinkgem.elclient.elasticsearch.util.EsUtils;
//import lombok.extern.slf4j.Slf4j;
//import org.elasticsearch.action.bulk.BulkRequest;
//import org.elasticsearch.action.delete.DeleteRequest;
//import org.elasticsearch.action.get.GetRequest;
//import org.elasticsearch.action.index.IndexRequest;
//import org.elasticsearch.action.search.SearchRequest;
//import org.elasticsearch.action.update.UpdateRequest;
//import org.elasticsearch.common.unit.TimeValue;
//import org.elasticsearch.index.query.QueryBuilders;
//import org.elasticsearch.search.Scroll;
//import org.elasticsearch.search.builder.SearchSourceBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
//@Component
//@Slf4j
//public class EsServiceImpl {
//
//    @Autowired
//    EsClient esClient;
//
//    /**
//     * 新增索引
//     */
//    // 传入：子类POJO的Class
//    public <T> RestResult createIndexMapping(Class<T> tClass) {
//        esClient.createIndexMapping(tClass);
//        return RestResult.getSuccessResult();
//    }
//
//// ***************************************************************************************************
//
//    /**
//     * 新增、修改、删除 文档：
//     */
//    // 新增文档：
//    // 传入：子类POJO的Class
//    public <T> RestResult createIndexDoc(Class<T> tClass, EsBaseEntity obj){
//        try {
//            IndexRequest indexRequest = new IndexRequest(
//                    tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                    tClass.getAnnotation(EsType.class).typeName(),
//                    obj.getDbId()
//            ).source(EsUtils.Class2Array(obj));
//            esClient.createIndexDoc(indexRequest);
//            return RestResult.getSuccessResult();
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//        return RestResult.getFailResult(500,"新增文档失败");
//    }
//    // 更新文档：
//    // 传入：子类POJO的Class
//    public <T> RestResult upDateIndexDoc(Class<T> tClass, EsBaseEntity obj){
//        try {
//            UpdateRequest updateRequest = new UpdateRequest(
//                    tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                    tClass.getAnnotation(EsType.class).typeName(),
//                    obj.getDbId()
//            ).doc(EsUtils.Class2Array(obj));
//            esClient.upDateIndexDoc(updateRequest);
//            return RestResult.getSuccessResult();
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//        return RestResult.getFailResult(500,"更新文档失败");
//    }
//    // 删除文档：
//    // 传入：子类POJO的Class
//    public <T> RestResult deleteIndexDoc(Class<T> tClass, EsBaseEntity obj) {
//        try {
//            DeleteRequest request = new DeleteRequest(
//                    tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                    tClass.getAnnotation(EsType.class).typeName(),
//                    obj.getDbId());
//            esClient.deleteIndexDoc(request);
//            return RestResult.getSuccessResult();
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//        return RestResult.getFailResult(500,"删除文档失败");
//    }
//
//    // 批量操作：
//    // 传入：子类POJO的Class
//    public <T> RestResult processDocBulk(Class<T> tClass, List<EsBaseEntity> createList, List<EsBaseEntity> upDateList, List<EsBaseEntity> deleteList){
//        BulkRequest request = new BulkRequest();
//        try {
//            if(createList != null) {
//                for (EsBaseEntity obj : createList) {
//                    IndexRequest indexRequest = new IndexRequest(
//                            tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                            tClass.getAnnotation(EsType.class).typeName(),
//                            obj.getDbId()
//                    ).source(EsUtils.Class2Array(obj));
//                    request.add(indexRequest);
//                }
//            }
//            if(upDateList != null) {
//                for (EsBaseEntity obj : upDateList) {
//                    UpdateRequest updateRequest = new UpdateRequest(tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                            tClass.getAnnotation(EsType.class).typeName(),
//                            obj.getDbId()
//                    ).doc(EsUtils.Class2Array(obj));
//                    request.add(updateRequest);
//                }
//            }
//            if(deleteList != null) {
//                for (EsBaseEntity obj : deleteList) {
//                    DeleteRequest deleteRequest = new DeleteRequest(
//                            tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                            tClass.getAnnotation(EsType.class).typeName(),
//                            obj.getDbId());
//                    request.add(deleteRequest);
//                }
//            }
//            esClient.processDocBulk(request);
//            return RestResult.getSuccessResult();
//        }catch (Exception e){
//            log.error(e.getMessage());
//        }
//        return RestResult.getFailResult(500,"新增文档失败");
//    }
//
//// ***************************************************************************************************
//
//    /**
//     * 查询
//     */
//    // 传入：子类POJO的Class
//    public <T> RestResult<T> getById(Class<T> tClass, String id) {
//        GetRequest getRequest = new GetRequest(
//                tClass.getSuperclass().getAnnotation(EsIndex.class).indexName(),
//                tClass.getAnnotation(EsType.class).typeName(),
//                id
//        );
//        return RestResult.getSuccessResult(esClient.getById(getRequest, tClass));
//    }
//    // 传入：子类POJO的Class
//    public <T> RestResult<List<T>> searchMatchByTitle(Class<T> tClass, String title, int pageNum, int pageSize, String orderField, String orderType) {
//        SearchRequest searchRequest = new SearchRequest();
//        searchRequest.indices(tClass.getSuperclass().getAnnotation(EsIndex.class).indexName());
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery(EsConfig.EsSearchConfig.SEARCH_TITLE, title));
//        searchSourceBuilder.from(pageNum);
//        searchSourceBuilder.size(pageSize == 0 || pageSize < 0 ? 10 : pageSize);
//        searchSourceBuilder.sort(EsUtils.createSortBuilder(tClass, orderField, orderType));
//        searchRequest.source(searchSourceBuilder);
//        return RestResult.getSuccessResult(esClient.search(searchRequest, tClass));
//    }
//    // 传入：子类POJO的Class
//    public <T> RestResult<List<T>> searchMatchScrollByTitle(Class<T> tClass, String title, int pageSize) {
//        final Scroll scroll = new Scroll(TimeValue.timeValueMinutes(1L));
//        SearchRequest searchRequest = new SearchRequest(tClass.getSuperclass().getAnnotation(EsIndex.class).indexName());
//        searchRequest.scroll(scroll);
//        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
//        searchSourceBuilder.query(QueryBuilders.matchQuery(EsConfig.EsSearchConfig.SEARCH_TITLE, title));
//        searchSourceBuilder.size(pageSize == 0 || pageSize < 0 ? 10 : pageSize);
//        searchRequest.source(searchSourceBuilder);
//        return RestResult.getSuccessResult(esClient.searchScroll(searchRequest, tClass, scroll));
//    }
//
//
//}
