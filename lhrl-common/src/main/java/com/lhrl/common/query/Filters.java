package com.lhrl.common.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 贤志
 */
public final class Filters implements java.io.Serializable {

    private final List<Filter> filters;

    public Filters() {
        this.filters = new ArrayList<>();
    }

    public Filters addFilter(Filter filter) {
        this.filters.add(filter);
        return this;
    }

    public Filters addFilter(String property, Object value, Filter.Operaion operation) {
        this.filters.add(new Filter(property, value, operation));
        return this;
    }

    public Filters addFilters(Filter[] filters) {
        if (null != filters) {
            this.filters.addAll(Arrays.asList(filters));
        }
        return this;
    }

    public Filters addFilters(List<? extends Filter> filters) {
        if (null != filters) {
            this.filters.addAll(filters);
        }
        return this;
    }

    public List<Filter> getFilters() {
        return this.filters;
    }
}
