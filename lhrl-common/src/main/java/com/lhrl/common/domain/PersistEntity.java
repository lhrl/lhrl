package com.lhrl.common.domain;

import com.lhrl.common.model.Entity;

import java.io.Serializable;

/**
 * 持久化实体接口
 *
 * @author Mei Xianzhi
 * @param <PK>
 */
public interface PersistEntity<PK extends Serializable> extends Entity {

    /**
     * 获取主键
     *
     * @return
     */
    PK getId();

    /**
     * 设置主键
     *
     * @param id
     */
    void setId(PK id);

    void save();

    void update();

    void merge();

    void delete();
}
