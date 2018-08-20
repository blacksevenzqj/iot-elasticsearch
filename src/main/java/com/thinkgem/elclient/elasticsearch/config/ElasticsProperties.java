package com.thinkgem.elclient.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>elastics配置</p>
 * @date: 2018年01月23日
 */
@ConfigurationProperties(prefix = "elasticsearch.server")
@Data
public class ElasticsProperties {

    /**
     * 名称
     */
    private String clusterName;

    /**
     * 节点
     */
    private String clusterNodes;

    /**
     * 端口号
     */
    private int port;


    private boolean connectTimeConfig;

    private boolean connectNumConfig;

    private int connectTimeOut;

    private int socketTimeOut;

    private int connectionRequestTimeOut;

    private int maxConnectNum;

    private int maxConnectPerRoute;

}
