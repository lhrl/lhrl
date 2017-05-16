package com.lhrl.common.domain;

import java.io.Serializable;

/**
 * 销售员
 *
 * @author Mei Xianzhi
 * @param <PK>
 */
public interface Seller<PK extends Serializable> extends Member<PK> {

    /**
     * 获取上级
     *
     * @return
     */
    Seller<PK> getUpper();
}
