package com.hd.util;

public class Pager {
    private int currentPage;//当前页

    private int pageCount;//总页数
    private String url;//访问的请求地址

    public Pager(int currentPage,int pageCount, String url) {
        this.currentPage = currentPage;
        this.pageCount = pageCount;
        this.url = url;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }


    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
