package com.oa.commons.vo;

import java.util.List;

public class Page<T>{//使用泛型可以保证所有的类都可以使用此值对象类；
    private int pageNum; //当前的页码；即第几页
    private int pageSize; //页面的大小；
    private int pageStartIndex; //每一页的开头索引值，索引从0开始；（pageNum-1）* pageSize；1 0,2 3,3 6；
    private int totalRow; //总的记录数
    private int totalPages; //总的页数

    private List<T> datas; //需要的每一页的数据

    public Page(){//无参初始化
        pageNum = 1;
        pageSize = 3;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public Page(int pageNum, int pageSize){//带参数构造

        //需要保证传入参数的时候参数的合理性；

        this.pageSize = pageSize >=0 ? pageSize : 3; //页面的大小必须大于0。小于0默认改为3
        this.pageNum = pageNum > 0 ? pageNum : 1; //不能小于0
    }

    public int getPageNum() {
        return pageNum;
    }


    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageStartIndex() {
       this.pageStartIndex = (pageNum-1)*pageSize; //计算每一页的起始索引
        return pageStartIndex;
    }

    public int getTotalRow() {
        return totalRow;
    }

    public void setTotalRow(int totalRow) {
        this.totalRow = totalRow;
    }

    public int getTotalPages() {//总的页面数
        this.totalPages = totalRow/pageSize+1;//无需set，直接可以获取到；
        return totalPages;
    }

    public List<T> getDatas() {
        return datas;
    }

    public void setDatas(List<T> datas) {
        this.datas = datas;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageNum=" + pageNum +
                ", pageSize=" + pageSize +
                ", pageStartIndex=" + pageStartIndex +
                ", totalRow=" + totalRow +
                ", totalPages=" + totalPages +
                ", datas=" + datas +
                '}';
    }
}