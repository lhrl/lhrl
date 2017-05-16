package com.lhrl.common.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;

import java.util.Map;

/**
 *
 * @author Mei Xianzhi
 */
public abstract class HibernateQuery implements Query {

    private final SessionFactory sessionFactory;

    HibernateQuery(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected Session getCurrentSession() {
        return this.sessionFactory.getCurrentSession();
    }

    protected void setParamValues(org.hibernate.Query queryObject, Object[] paramValues) {
        if (paramValues != null && paramValues.length > 0) {
            for (int i = 0; i < paramValues.length; i++) {
                queryObject.setParameter(i, paramValues[i]);
            }
        }
    }

    /**
     * 创建sql查询对象
     *
     * @param str
     * @param paramValues
     * @param start
     * @param limit
     * @param targetClass
     * @return
     */
    protected org.hibernate.Query createSQLQuery(String str, Object[] paramValues, int start, int limit, Class<?> targetClass) {
        return this.buildQuery(this.getCurrentSession().createSQLQuery(str), paramValues, start, limit, targetClass);
    }

    /**
     * 创建HQL查询对象
     *
     * @param str
     * @param paramValues
     * @param start
     * @param limit
     * @param targetClass
     * @return
     */
    protected org.hibernate.Query createQuery(String str, Object[] paramValues, int start, int limit, Class<?> targetClass) {
        return this.buildQuery(this.getCurrentSession().createQuery(str), paramValues, start, limit, targetClass);
    }

    private org.hibernate.Query buildQuery(org.hibernate.Query queryObject, Object[] paramValues, int start, int limit, Class<?> targetClass) {

        if (paramValues != null && paramValues.length > 0) {
            for (int i = 0; i < paramValues.length; i++) {
                queryObject.setParameter(i, paramValues[i]);
            }
        }

        if (start >= 0) {
            queryObject.setFirstResult(start);
        }

        if (limit > 0) {
            queryObject.setMaxResults(limit);
        }

        if (targetClass != null) {
            queryObject.setResultTransformer(Map.class.isAssignableFrom(targetClass) ? Transformers.ALIAS_TO_ENTITY_MAP : Transformers.aliasToBean(targetClass));
        }

        return queryObject;
    }

}
