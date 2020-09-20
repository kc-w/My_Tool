package com.wan.pojo;


public class page {

    private int nowPage;//当前页第一记录
    private int pageSize=10;//每页记录数
    private int allSize;//总记录数
    private int nowPageNumber;//当前页
    private int allPage;//总页数


    public int getNowPage() {
        return nowPage;
    }

    public void setNowPage(int nowPage) {
        this.nowPage = nowPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getAllSize() {
        return allSize;
    }

    public void setAllSize(int allSize) {
        this.allSize = allSize;
    }

    public int getNowPageNumber() {
        int nowPageNumber=nowPage/10+1;
        return nowPageNumber;
    }

    public void setNowPageNumber(int nowPageNumber) {
        this.nowPageNumber = nowPageNumber;
    }

    public int getAllPage() {
        int allPage=allSize/10+1;
        return allPage;
    }

    public void setAllPage(int allPage) {
        this.allPage = allPage;
    }


}
