package com.lhrl.common.dao;

import com.lhrl.common.model.AbstractEntity;
import com.lhrl.common.query.Filters;
import com.lhrl.common.query.Page;
import com.lhrl.common.query.Sorts;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author 贤志
 */
public interface DaoSupport {

    /**
     *
     * @param <T>
     * @param claz
     * @param id
     * @return
     */
    <T extends AbstractEntity> T get(Class<T> claz, Serializable id);

    <T extends AbstractEntity> T load(Class<T> claz, Serializable id);

    <T extends AbstractEntity> void refresh(T entity);

    <T extends AbstractEntity> boolean contains(T entity);

    <T extends AbstractEntity> void evict(T entity);

    <T extends AbstractEntity> Serializable save(T entity);

    <T extends AbstractEntity> void saveAll(Collection<T> collection);

    <T extends AbstractEntity> void update(T entity);

    <T extends AbstractEntity> void updateAll(Collection<T> collection);

    @Deprecated
    <T extends AbstractEntity> void saveOrUpdate(T entity);

    @Deprecated
    <T extends AbstractEntity> void saveOrUpdateAll(Collection<T> entities);

    <T extends AbstractEntity> void persist(T entity);

    <T extends AbstractEntity> T merge(T entity);

    <T extends AbstractEntity> void delete(T entity);

    <T extends AbstractEntity> void deleteAll(Collection<T> entities);

    <T extends AbstractEntity> List<T> findByExample(T entity);

    <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String propName, Object propValue);

    <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String[] propNames, Object[] propValues);

    <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String[] propNames, Object[] propValues, int firstResult, int maxResults);

    <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String[] propNames, Object[] propValues, int firstResult, int maxResults, String orderColumn, boolean isAsc);

    <T extends AbstractEntity> List<T> find(Class<T> claz, Filters filters);

    <T extends AbstractEntity> List<T> find(Class<T> claz, Filters filters, Sorts sorts);

    <T extends AbstractEntity> Page find(Class<T> claz, Filters filters, Sorts sorts, Page page);

    <T extends AbstractEntity> Page find(Class<T> claz, Filters filters, Sorts sorts, Page page, List<String> fields);

    Query getHQLQuery();

    Query getSQLQuery();
}
