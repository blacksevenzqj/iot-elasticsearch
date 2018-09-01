package com.thinkgem.elclient.elasticsearch.config;

import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
@EnableConfigurationProperties(ElasticsServerProperties.class)
public class ElasticsConfig {

    @Autowired
    private ElasticsServerProperties elasticsServerProperties;

    /**
     * 初始化
     * 默认情况下bean的名称和方法名称相同，你也可以使用name属性来指定
     */
    @Bean
    public RestHighLevelClient restHighLevelClient() {
        return getEsClientDecorator().getRestHighLevelClient();
    }

    @Bean(name = "esClientDecorator")
    @Scope("singleton")
    public EsClientDecorator getEsClientDecorator() {
        return new EsClientDecorator(elasticsServerProperties);
    }

}
