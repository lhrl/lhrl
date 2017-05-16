package com.lhrl.common.domain;

import java.io.Serializable;

/**
 * 产品接口
 *
 * @author Mei Xianzhi
 * @param <PK>
 */
public interface Product<PK extends Serializable> extends Serializable {

    /**
     * 获取主键
     *
     * @return
     */
    PK getId();

    /**
     * 获取商品名称
     *
     * @return
     */
    String getName();

    /**
     * 获取系统类型
     *
     * @return
     */
    SystemType getSystemType();

}
