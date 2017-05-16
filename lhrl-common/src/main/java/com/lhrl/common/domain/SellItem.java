/**
 *
 */
package com.lhrl.common.domain;

import java.io.Serializable;

/**
 * 订单项 产品和数量
 *
 * @author sunmin
 * @param <PK>
 *
 */
public interface SellItem<PK extends Serializable> extends Serializable {

    /**
     * 得到订单项的产品
     *
     * @return
     */
    SellObject<PK> getSellObject();

    /**
     * 得到产品的数量
     *
     * @return
     */
    Integer getCount();

}
