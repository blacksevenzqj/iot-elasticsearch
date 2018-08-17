package com.thinkgem.elclient.elasticsearch.config;

import com.thinkgem.elclient.elasticsearch.common.AnalyzerConfigEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>ES客户端工程类</p>
 */
public class ESClientDecorator implements InitializingBean, DisposableBean {

    private RestHighLevelClient restHighLevelClient;

    private HttpHost httpHost;

    private static Map<String, Map> mapType = new HashMap<>();

    public ESClientDecorator(HttpHost httpHost) {
        this.httpHost = httpHost;
    }

    public RestHighLevelClient getRestHighLevelClient() {
        if (restHighLevelClient == null) {
            restHighLevelClient = new RestHighLevelClient(RestClient.builder(httpHost));
        }
        return restHighLevelClient;
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
        restHighLevelClient.close();
        mapType = null;
    }

    public static Map<String, Map> getMapType(){
        return mapType;
    }


}
