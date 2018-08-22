package com.thinkgem.elclient.elasticsearch.annotation;


import com.thinkgem.elclient.elasticsearch.common.AggDescEnum;
import com.thinkgem.elclient.elasticsearch.common.AnalyzerConfigEnum;
import com.thinkgem.elclient.elasticsearch.common.EsConfig;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsFieldData {

    String dataName() default "";

    String elName() default "";

    String elQueryDesc() default EsConfig.FIELD_QUERY_TYPE.DEFAULT_TYPE;

    AggDescEnum elAggType() default AggDescEnum.NULL;

    AnalyzerConfigEnum analyzerType() default AnalyzerConfigEnum.NULL;

    AnalyzerConfigEnum analyzerSearchType() default AnalyzerConfigEnum.NULL;

}
