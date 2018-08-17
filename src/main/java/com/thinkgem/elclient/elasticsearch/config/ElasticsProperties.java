package com.thinkgem.elclient.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * <p>elastics配置</p>
 *
 * @author: XianGuo
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


}
