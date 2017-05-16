/**
 *
 */
package com.lhrl.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * 订单的接口
 *
 * @author sunmin
 * @param <PK>
 *
 */
public interface Order<PK extends Serializable> extends Serializable {

    PK getId();

    /**
     * 取得 订单编号
     *
     * @return
     */
    String getOrderNo();

    List<SellItem<PK>> getSellItems();

    /**
     * 获取系统类型
     *
     * @return
     */
    SystemType getSystemType();
}
