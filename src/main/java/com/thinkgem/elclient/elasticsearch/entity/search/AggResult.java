package com.thinkgem.elclient.elasticsearch.entity.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class AggResult {

    private String groupName;

    private Integer groupCount;

    private String keyName;

    private Long keyCount;

    private Double keyMaxValue;

    private Date keyMaxDate;

    List<AggResult> agg = new ArrayList<>();

}
