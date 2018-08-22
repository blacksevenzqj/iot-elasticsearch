package com.thinkgem.elclient.elasticsearch.entity.search;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class AggQueryEntry {

    private List<AggQueryEntryType> aggQueryList = new ArrayList<>();

    @Data
    public class AggQueryEntryType implements Comparable{
        private String groupName;
        private String fieldName;
        private String aggType;
        private Integer aggOrder;

        @Override
        public int compareTo(Object obj) {
            AggQueryEntryType aggQueryEntryType = (AggQueryEntryType)obj;
            return  this.aggOrder.compareTo(aggQueryEntryType.aggOrder);
        }
    }


}
