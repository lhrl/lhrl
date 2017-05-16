package com.lhrl.common.query;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author 贤志
 */
public final class Sorts implements java.io.Serializable {

    private final List<Sort> sorts;

    public Sorts() {
        sorts = new ArrayList<>();
    }

    public Sorts addSort(Sort sort) {
        if (null != sort) {
            this.sorts.add(sort);
        }
        return this;
    }

    public Sorts addSort(String property, String direction) {
        this.sorts.add(new Sort(property, direction));
        return this;
    }

    public Sorts addSorts(Sort[] sorts) {
        if (null != sorts) {
            this.sorts.addAll(Arrays.asList(sorts));
        }
        return this;
    }

    public Sorts addSorts(List<Sort> sorts) {
        if (null != sorts) {
            this.sorts.addAll(sorts);
        }
        return this;
    }

    public List<Sort> getSorts() {
        return this.sorts;
    }
}
