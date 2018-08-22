package com.thinkgem.elclient.elasticsearch.entity.search;

import java.util.Date;

public class AggResultEntry {

    private String clientId;
    private Long clientCount;
    private AggResultSubEntry aggResultSubEntry;

    public AggResultEntry() {
        this.aggResultSubEntry = new AggResultSubEntry();
    }

    public class AggResultSubEntry{
        private Long onLine;
        private Long offLine;
        private Date maxUpDate;

        public Long getOnLine() {
            return onLine;
        }
        public void setOnLine(Long onLine) {
            this.onLine = onLine;
        }

        public Long getOffLine() {
            return offLine;
        }
        public void setOffLine(Long offLine) {
            this.offLine = offLine;
        }

        public Date getMaxUpDate() {
            return maxUpDate;
        }
        public void setMaxUpDate(Date maxUpDate) {
            this.maxUpDate = maxUpDate;
        }

        @Override
        public String toString() {
            return "AggResultSubEntry{" +
                    "onLine=" + onLine +
                    ", offLine=" + offLine +
                    ", maxUpDate=" + maxUpDate +
                    '}';
        }

    }

    public String getClientId() {
        return clientId;
    }
    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Long getClientCount() {
        return clientCount;
    }
    public void setClientCount(Long clientCount) {
        this.clientCount = clientCount;
    }

    public AggResultSubEntry getAggResultSubEntry() {
        return aggResultSubEntry;
    }
    public void setAggResultSubEntry(AggResultSubEntry aggResultSubEntry) {
        this.aggResultSubEntry = aggResultSubEntry;
    }

    @Override
    public String toString() {
        return "AggResultEntry{" +
                "clientId='" + clientId + '\'' +
                ", clientCount=" + clientCount +
                ", aggResultSubEntry=" + aggResultSubEntry +
                '}';
    }

}
