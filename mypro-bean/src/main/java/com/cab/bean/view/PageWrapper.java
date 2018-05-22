package com.cab.bean.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author     : Alec Fan
 * DateTime   : 2017/5/20 11:31
 * Description: 分页包装类型
 */
public class PageWrapper<T> implements Serializable {

    /**
     * 第几页
     */
    private int pageNo ;

    /**
     * 每页显示对象个数
     */
    private int pageSize ;

    /**
     * 对象总数量
     */
    private long totalCount;

    /**
     * 总页数数
     */
    private int pageCount ;

    /**
     * 每页的数据列表
     */
    private List<T> result = new ArrayList<T>();

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(long totalCount) {
        this.totalCount = totalCount;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getResult() {
        return result;
    }

    public void setResult(List<T> result) {
        this.result = result;
    }
}