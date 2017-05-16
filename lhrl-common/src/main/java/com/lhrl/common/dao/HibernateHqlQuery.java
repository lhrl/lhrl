package com.lhrl.common.dao;

import org.hibernate.SessionFactory;

import java.util.List;

/**
 *
 * @author Mei Xianzhi
 */
public class HibernateHqlQuery extends HibernateQuery {

    HibernateHqlQuery(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    @Override
    public List find(String str) {
        return this.find(str, null, 0, 0, null);
    }

    @Override
    public List find(String str, Class<?> targetClass) {
        return this.find(str, null, 0, 0, targetClass);
    }

    @Override
    public List find(String str, int start, int limit) {
        return this.find(str, null, start, limit, null);
    }

    @Override
    public List find(String str, int start, int limit, Class<?> targetClass) {
        return this.find(str, null, start, limit, targetClass);
    }

    @Override
    public List find(String str, Object[] paramValues) {
        return this.find(str, paramValues, 0, 0, null);
    }

    @Override
    public List find(String str, Object[] paramValues, Class<?> targetClass) {
        return this.find(str, paramValues, 0, 0, targetClass);
    }

    @Override
    public List find(String str, Object[] paramValues, int start, int limit) {
        return this.find(str, paramValues, start, limit, null);
    }

    @Override
    public List find(String str, Object[] paramValues, int start, int limit, Class<?> targetClass) {
        return this.createQuery(str, paramValues, start, limit, targetClass).list();
    }

    @Override
    public int update(String str) {
        return this.createQuery(str, null, 0, 0, null).executeUpdate();
    }

    @Override
    public int update(String str, Object[] paramValues) {
        return this.createQuery(str, paramValues, 0, 0, null).executeUpdate();
    }

}
