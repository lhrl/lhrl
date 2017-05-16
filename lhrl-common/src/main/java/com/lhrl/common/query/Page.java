package com.lhrl.common.query;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author 贤志
 * @param <T>
 */
public final class Page<T> implements Serializable {

    private Long total;
    private Integer start = 0;
    private Integer limit = 15;
    private List<T> data;

    public Page() {
    }

    public Page(Integer paramStart, Integer paramLimit) {
        this.start = paramStart;
        this.limit = paramLimit;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
