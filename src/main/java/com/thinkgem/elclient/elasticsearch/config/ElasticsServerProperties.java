package com.thinkgem.elclient.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>elastics配置</p>
 */
@ConfigurationProperties(prefix = "elasticsearch.server")
@Data
public class ElasticsServerProperties {

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


    private boolean credentialsProviderConfig;

    private String providerUserName;

    private String providerUserPassword;

    private boolean connectTimeConfig;

    private boolean connectNumConfig;

    private int connectTimeOut;

    private int socketTimeOut;

    private int connectionRequestTimeOut;

    private int maxConnectNum;

    private int maxConnectPerRoute;

}
