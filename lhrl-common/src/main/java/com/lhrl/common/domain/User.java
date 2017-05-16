package com.lhrl.common.domain;

import java.io.Serializable;

/**
 * 用户接口
 *
 * @author Mei Xianzhi
 * @param <PK>
 */
public interface User<PK extends Serializable> extends PersistEntity<PK> , Member<PK>{

 

}
