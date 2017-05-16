/**
 *
 */
package com.lhrl.common.domain;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author sunmin
 * @param <PK>
 *
 */
public interface SellObject<PK extends Serializable> extends Serializable {

    /**
     * 获取主键
     *
     * @return
     */
    PK getId();

    /**
     * 获取产品
     *
     * @return
     */
    Product<PK> getProduct();

    /**
     * 获取价格
     *
     * @return
     */
    BigDecimal getPrice();
}
