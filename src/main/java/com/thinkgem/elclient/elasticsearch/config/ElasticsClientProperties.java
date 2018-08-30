package com.thinkgem.elclient.elasticsearch.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>elastics配置</p>
 */
@Component
@ConfigurationProperties(prefix = "elasticsearch.client")
@Data
public class ElasticsClientProperties {

    private long searchSourceTimeOutSeconds;

    private long scrollTimeWindowMinutes;

    private int miniMumShouldMatch;

    private int aggSearchSourceSize;

}
