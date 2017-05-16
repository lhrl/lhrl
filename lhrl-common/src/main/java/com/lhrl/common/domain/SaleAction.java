/**
 * 
 */
package com.lhrl.common.domain;

import java.io.Serializable;
import java.util.List;

/**
 * @author sunmin
 *
 */
public interface SaleAction<PK extends Serializable> extends Serializable{

	PK getId();
	
	List<SellItem<PK>> getSellItems();

    /**
     * 获取系统类型
     *
     * @return
     */
    SystemType getSystemType();
}
