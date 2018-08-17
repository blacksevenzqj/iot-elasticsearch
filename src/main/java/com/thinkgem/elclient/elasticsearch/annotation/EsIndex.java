package com.thinkgem.elclient.elasticsearch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface EsIndex {

    String indexName() default "";

    int numberOfShards() default 5;

    int numberOfReplicas() default 1;

}
