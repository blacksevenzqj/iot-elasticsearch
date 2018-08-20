package com.thinkgem.elclient.elasticsearch.entity.base;


public class EsPageInfo {

    private int pageNum = 1;

    private int pageStart = 0;

    private int pageSize = 2;


    public int getPageNum() {
        return pageNum;
    }
    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageStart() {
        pageStart = (pageNum - 1) * pageSize;
        return pageStart;
    }
    public void setPageStart(int pageStart) {
        this.pageStart = pageStart;
    }

    public int getPageSize() {
        return pageSize;
    }
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
