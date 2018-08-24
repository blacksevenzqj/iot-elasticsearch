package com.thinkgem.elclient.elasticsearch.config;

import com.thinkgem.elclient.elasticsearch.common.AnalyzerConfigEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import org.apache.http.HttpHost;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>ES客户端工程类</p>
 */
public class ESClientDecorator implements InitializingBean, DisposableBean {

    private ElasticsProperties elasticsProperties;

    private RestClientBuilder builder;

    private RestClient restClient;

    private RestHighLevelClient restHighLevelClient;

    private HttpHost httpHost;

    private static Map<String, Map> mapType = new HashMap<>();

    public ESClientDecorator(ElasticsProperties elasticsProperties) {
        this.elasticsProperties = elasticsProperties;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Map mapString = new HashMap();
        mapString.put(EsConfig.EL_TYPE, EsConfig.El_STRING);
        Map mapInteger = new HashMap();
        mapInteger.put(EsConfig.EL_TYPE, EsConfig.El_INTEGER);
        Map mapDouble = new HashMap();
        mapDouble.put(EsConfig.EL_TYPE, EsConfig.El_DOUBLE);
        Map mapBoolean = new HashMap();
        mapBoolean.put(EsConfig.EL_TYPE, EsConfig.EL_BOOLEAN);
        Map mapDate = new HashMap();
        mapDate.put(EsConfig.EL_TYPE, EsConfig.EL_DATE);
        Map mapKeyWord = new HashMap();
        mapKeyWord.put(EsConfig.EL_TYPE, EsConfig.El_KEYWORD);
        Map mapLong= new HashMap();
        mapLong.put(EsConfig.EL_TYPE, EsConfig.El_LONG);

        Map mapAnalyzerIk= new HashMap();
        mapAnalyzerIk.put(EsConfig.AnalyzerConfig.ANALYZER, AnalyzerConfigEnum.IK.getValue());
        Map mapAnalyzerIkSearch= new HashMap();
        mapAnalyzerIkSearch.put(EsConfig.AnalyzerConfig.SEARCH_ANALYZER, AnalyzerConfigEnum.IK_SEARCH.getValue());

        mapType.put(EsConfig.El_STRING, mapString);
        mapType.put(EsConfig.El_INTEGER, mapInteger);
        mapType.put(EsConfig.El_DOUBLE, mapDouble);
        mapType.put(EsConfig.EL_BOOLEAN, mapBoolean);
        mapType.put(EsConfig.EL_DATE, mapDate);
        mapType.put(EsConfig.El_KEYWORD, mapKeyWord);
        mapType.put(EsConfig.El_LONG, mapLong);

        mapType.put(AnalyzerConfigEnum.IK.getKey(), mapAnalyzerIk);
        mapType.put(AnalyzerConfigEnum.IK_SEARCH.getKey(), mapAnalyzerIkSearch);
    }

    @Override
    public void destroy() throws Exception {
        if (restHighLevelClient != null) {
            try {
                restHighLevelClient.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mapType = null;
    }

    public static Map<String, Map> getMapType(){
        return mapType;
    }

    /**
     * ==============================================================================================================
     */

    public RestHighLevelClient getRestHighLevelClient() {
        if (restHighLevelClient == null) {
            init();
        }
        return restHighLevelClient;
    }

    private void init(){
        if(httpHost == null){
            httpHost = new HttpHost(elasticsProperties.getClusterNodes(), elasticsProperties.getPort());
        }
        builder = RestClient.builder(httpHost);
        if(elasticsProperties.isConnectTimeConfig()){
            setConnectTimeOutConfig();
        }
        if(elasticsProperties.isConnectNumConfig()){
            setMutiConnectConfig();
        }if(elasticsProperties.isCredentialsProviderConfig()){
            setCredentialsProviderConfig();
        }
        restClient = builder.build();
        restHighLevelClient = new RestHighLevelClient(builder);
    }

    /**
     *  主要关于异步httpclient的连接延时配置
     */
    private void setConnectTimeOutConfig(){
        builder.setRequestConfigCallback(requestConfigBuilder -> {
            requestConfigBuilder.setConnectTimeout(elasticsProperties.getConnectTimeOut());
            requestConfigBuilder.setSocketTimeout(elasticsProperties.getSocketTimeOut());
            requestConfigBuilder.setConnectionRequestTimeout(elasticsProperties.getConnectionRequestTimeOut());
            return requestConfigBuilder;
        });
    }
    /**
     *  主要关于异步httpclient的连接数配置
     */
    private void setMutiConnectConfig(){
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setMaxConnTotal(elasticsProperties.getMaxConnectNum());
            httpClientBuilder.setMaxConnPerRoute(elasticsProperties.getMaxConnectPerRoute());
            return httpClientBuilder;
        });
    }
    /**
     * 设置登录认证
     */
    private void setCredentialsProviderConfig(){
        final CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        credentialsProvider.setCredentials(AuthScope.ANY, new UsernamePasswordCredentials(elasticsProperties.getProviderUserName(), elasticsProperties.getProviderUserPassword()));
        builder.setHttpClientConfigCallback(httpClientBuilder -> {
            httpClientBuilder.setDefaultCredentialsProvider(credentialsProvider);
            return httpClientBuilder;
        });
    }

}
