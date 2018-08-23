package com.thinkgem.elclient.elasticsearch.entity.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AggResultAll {

    private String groupName;

    private Integer groupCount;

    private String keyName;

    private Long keyCount;

    private Double keyMaxValue;

    private String keyMaxDate;

    private AggResultAll parent;

    List<AggResultAll> agg = new ArrayList<>();


    @Override
    public String toString() {
        return "AggResultAll{" +
                "groupName='" + groupName + '\'' +
                ", groupCount=" + groupCount +
                ", keyName='" + keyName + '\'' +
                ", keyCount=" + keyCount +
                ", keyMaxValue=" + keyMaxValue +
                ", keyMaxDate='" + keyMaxDate + '\'' +
//                ", parent=" + parent +
                ", agg=" + agg +
                '}';
    }

}
