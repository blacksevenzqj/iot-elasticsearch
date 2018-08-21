package com.thinkgem.elclient.elasticsearch.entity.search;

import lombok.Data;

import java.util.Date;

@Data
public class AggResultEntry {

    private String clientId;
    private Long clientCount;
    private AggResultSubEntry aggResultSubEntry;

    @Data
    public class AggResultSubEntry{
        private Long onLine;
        private Long offLine;
        private Date maxUpDate;
    }

}
