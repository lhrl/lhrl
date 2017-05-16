package com.lhrl.common.model;

import java.util.Date;

/**
 *
 * @author Mei Xianzhi
 */
public interface BizEntity extends Entity {

    String getCreator();

    void setCreator(String creator);

    Date getCreateTime();

    void setCreateTime(Date createTime);

    String getUpdator();

    void setUpdator(String updator);

    Date getUpdateTime();

    void setUpdateTime(Date updateTime);
}
