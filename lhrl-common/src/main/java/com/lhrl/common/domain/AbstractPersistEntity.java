package com.lhrl.common.domain;

import com.lhrl.common.dao.DaoFactory;
import com.lhrl.common.model.AbstractEntity;

import java.io.Serializable;

/**
 *
 * @author Mei Xianzhi
 * @param <PK>
 */
public abstract class AbstractPersistEntity<PK extends Serializable> extends AbstractEntity implements PersistEntity<PK> {

    @Override
    public void save() {
        DaoFactory.getDao().save(this);
    }

    @Override
    public void update() {
        DaoFactory.getDao().update(this);
    }

    @Override
    public void merge() {
        DaoFactory.getDao().merge(this);
    }

    @Override
    public void delete() {
        DaoFactory.getDao().delete(this);
    }

}
