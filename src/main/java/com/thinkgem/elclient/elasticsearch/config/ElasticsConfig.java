package com.thinkgem.elclient.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(ElasticsProperties.class)
public class ElasticsConfig {

    @Autowired
    private ElasticsProperties elasticsProperties;

    /**
     * 初始化
     */
    @Bean
    public RestHighLevelClient getRestHighLevelClient() {
        return getEsClientDecorator().getRestHighLevelClient();
    }

    @Bean
    @Scope("singleton")
    public ESClientDecorator getEsClientDecorator() {
        return new ESClientDecorator(elasticsProperties);
    }

}
