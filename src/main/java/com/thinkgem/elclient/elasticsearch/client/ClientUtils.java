package com.thinkgem.elclient.elasticsearch.client;

import lombok.extern.slf4j.Slf4j;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;

@Slf4j
public class ClientUtils {

    public static void responseProcess(DocWriteResponse docWriteResponse){
        String index = docWriteResponse.getIndex();
        String type = docWriteResponse.getType();
        String id = docWriteResponse.getId();
        long version = docWriteResponse.getVersion();
        if (docWriteResponse.getResult() == DocWriteResponse.Result.CREATED) {
            log.info("CREATED1" + ":" + index + ":" + type + ":" + id + ":" + version);
        } else if (docWriteResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            log.info("UPDATED" + ":" + index + ":" + type + ":" + id + ":" + version);
        }else if(docWriteResponse.getResult() == DocWriteResponse.Result.DELETED){
            log.info("DELETE" + ":" + index + ":" + type + ":" + id + ":" + version);
        }else if(docWriteResponse.getResult() == DocWriteResponse.Result.NOT_FOUND){
            log.info("NOT_FOUND" + ":" + index + ":" + type + ":" + id + ":" + version);
        }else if(docWriteResponse.getResult() == DocWriteResponse.Result.NOOP){
            log.info("NOOP" + ":" + index + ":" + type + ":" + id + ":" + version);
        }
        ReplicationResponse.ShardInfo shardInfo = docWriteResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            log.info("shardInfo.getTotal() != shardInfo.getSuccessful()" + ":" + index + ":" + type + ":" + id + ":" + version);
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure : shardInfo.getFailures()) {
                String reason = failure.reason();
                log.error(reason + ":" + index + ":" + type + ":" + id + ":" + version);
            }
        }
    }

}
