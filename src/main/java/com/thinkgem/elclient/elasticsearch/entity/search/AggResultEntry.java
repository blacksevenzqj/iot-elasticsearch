package com.thinkgem.elclient.elasticsearch.entity.search;


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
        private String onLineMaxUpDate;
        private String offLineMaxUpDate;

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

        public String getOnLineMaxUpDate() {
            return onLineMaxUpDate;
        }
        public void setOnLineMaxUpDate(String onLineMaxUpDate) {
            this.onLineMaxUpDate = onLineMaxUpDate;
        }

        public String getOffLineMaxUpDate() {
            return offLineMaxUpDate;
        }
        public void setOffLineMaxUpDate(String offLineMaxUpDate) {
            this.offLineMaxUpDate = offLineMaxUpDate;
        }

        @Override
        public String toString() {
            return "AggResultSubEntry{" +
                    "onLine=" + onLine +
                    ", offLine=" + offLine +
                    ", onLineMaxUpDate='" + onLineMaxUpDate + '\'' +
                    ", offLineMaxUpDate='" + offLineMaxUpDate + '\'' +
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
