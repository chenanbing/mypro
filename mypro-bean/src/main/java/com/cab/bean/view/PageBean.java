package com.cab.bean.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Author     : Alec Fan
 * DateTime   : 2017/5/20 11:31
 * Description: 分页包装类型
 */
public class PageBean<T> implements Serializable {

    /**
     * 第几页
     */
    private int currentPage ;

    /**
     * 每页显示对象个数
     */
    private int pageSize ;

    /**
     * 对象总数量
     */
    private long total;

    /**
     * 总页数数
     */
    private int pageCount ;

    /**
     * 每页的数据列表
     */
    private List<T> rows = new ArrayList<T>();

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }
}