package com.thinkgem.elclient.elasticsearch.entity.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AggQueryEntry {

    private List<AggQueryEntryType> aggQueryList = new ArrayList<>();

    @Data
    public class AggQueryEntryType{
        private String groupName;
        private String fieldName;
        private String aggType;
    }


}
