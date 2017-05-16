package com.lhrl.common.domain;

import java.io.Serializable;

public interface Member<PK extends Serializable> extends Serializable {

    /**
     * 获取主键
     *
     * @return
     */
    PK getId();

    /**
     * 获取登录名
     *
     * @return
     */
    public String getLoginName();

    /**
     * 获取用户类型
     *
     * @return
     */
    public UserType getUserType();
}
