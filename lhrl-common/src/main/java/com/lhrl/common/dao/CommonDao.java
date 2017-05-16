package com.lhrl.common.dao;

import com.lhrl.common.model.AbstractLockEntity;
import com.lhrl.common.model.BizEntity;
import com.lhrl.common.util.DateUtils;
import com.lhrl.common.util.ThreadLocalUserUtils;
import com.lhrl.common.model.AbstractEntity;
import org.hibernate.Criteria;
import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.*;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Repository;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import  com.lhrl.common.query.*;


/**
 *
 * @author 贤志
 */
@Repository
public class CommonDao implements DaoSupport, InitializingBean {

    @Resource
    private SessionFactory sessionFactory;

    private Query hibernateHQLQuery;
    private Query hibernateSQLQuery;

    public CommonDao() {
    }

    public CommonDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    protected SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    protected Session getCurrentSession() {
        Session session = sessionFactory.getCurrentSession();
        session.setFlushMode(FlushMode.AUTO);
        return session;
    }

    @Override
    public <T extends AbstractEntity> T get(Class<T> claz, Serializable id) {
        return (T) getCurrentSession().get(claz, id);
    }

    @Override
    public <T extends AbstractEntity> T load(Class<T> claz, Serializable id) {
        return (T) getCurrentSession().load(claz, id);
    }

    @Override
    public <T extends AbstractEntity> void refresh(T entity) {
        getCurrentSession().refresh(entity);
    }

    @Override
    public <T extends AbstractEntity> boolean contains(T entity) {
        return getCurrentSession().contains(entity);
    }

    @Override
    public <T extends AbstractEntity> void evict(T entity) {
        getCurrentSession().evict(entity);
    }

    @Override
    public <T extends AbstractEntity> Serializable save(T entity) {
        this.setEntityModifyUser(entity, true);
        return getCurrentSession().save(entity);
    }

    @Override
    public <T extends AbstractEntity> void saveAll(Collection<T> collection) {
        if (null == collection || collection.isEmpty()) {
            return;
        }

        int count = 0;
        Session session = getCurrentSession();
        for (T entity : collection) {
            this.setEntityModifyUser(entity, true);
            session.save(entity);
            count++;
            if (count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    public <T extends AbstractEntity> void update(T entity) {
        this.setEntityModifyUser(entity, false);
        getCurrentSession().update(entity);
    }

    @Override
    public <T extends AbstractEntity> void updateAll(Collection<T> collection) {
        if (null == collection || collection.isEmpty()) {
            return;
        }

        int count = 0;
        Session session = getCurrentSession();
        for (T entity : collection) {
            this.setEntityModifyUser(entity, false);
            session.update(entity);
            count++;
            if (count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    @Override
    @Deprecated
    public <T extends AbstractEntity> void saveOrUpdate(T entity) {
        this.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    @Deprecated
    public <T extends AbstractEntity> void saveOrUpdateAll(Collection<T> entities) {
        Session session = getCurrentSession();
        int count = 1;
        for (T entity : entities) {
            session.saveOrUpdate(entity);
            if (count % 20 == 0) {
                session.flush();
                session.clear();
            }
            count++;
        }
    }

    @Override
    public <T extends AbstractEntity> void persist(T entity) {
        getCurrentSession().persist(entity);
    }

    @Override
    public <T extends AbstractEntity> T merge(T entity) {
        return (T) this.getCurrentSession().merge(entity);
    }

    @Override
    public <T extends AbstractEntity> void delete(T entity) {
        this.getCurrentSession().delete(entity);
    }

    @Override
    public <T extends AbstractEntity> void deleteAll(Collection<T> collection) {
        if (null == collection || collection.isEmpty()) {
            return;
        }

        int count = 0;
        Session session = getCurrentSession();
        for (T entity : collection) {
            session.delete(entity);
            if (count % 20 == 0) {
                session.flush();
                session.clear();
            }
        }
    }

    private <T extends AbstractEntity> void setEntityModifyUser(T entity, boolean save) {

        if (entity == null) {
            return;
        }

        String modifyUserName = ThreadLocalUserUtils.getUser();
        if (modifyUserName == null || "".equals(modifyUserName.trim()) || !BizEntity.class.isAssignableFrom(entity.getClass())) {
            return;
        }

        BizEntity bizEntity = (BizEntity) entity;
        bizEntity.setUpdator(modifyUserName);
        bizEntity.setUpdateTime(new Date());
        if (save) {
            bizEntity.setCreator(modifyUserName);
            bizEntity.setCreateTime(bizEntity.getUpdateTime());
        }

    }

    @Override
    public <T extends AbstractEntity> List<T> findByExample(T entity) {
        return this.getCurrentSession().createCriteria(entity.getClass())
                .add(Example.create(entity))
                .list();
    }

    @Override
    public <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String propName, Object propValue) {
        return findByProperty(claz, new String[]{propName}, new Object[]{propValue});
    }

    @Override
    public <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String[] propNames, Object[] propValues) {
        return findByProperty(claz, propNames, propValues, -1, -1);
    }

    @Override
    public <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String[] propNames, Object[] propValues, int firstResult, int maxResults) {
        return findByProperty(claz, propNames, propValues, firstResult, maxResults, null, false);
    }

    @Override
    public <T extends AbstractEntity> List<T> findByProperty(Class<T> claz, String[] propNames, Object[] propValues, int firstResult, int maxResults, String orderColumn, boolean isAsc) {
        Assert.isTrue(propNames.length == propValues.length, "参数不匹配");
        Criteria criteria = this.getCurrentSession().createCriteria(claz);
        int length = propNames.length;

        for (int i = 0; i < length; i++) {
            criteria.add(Restrictions.eq(propNames[i], propValues[i]));
        }
        if (firstResult >= 0) {
            criteria.setFirstResult(firstResult);
        }
        if (maxResults > 0) {
            criteria.setMaxResults(maxResults);
        }

        if (StringUtils.hasText(orderColumn)) {
            if (isAsc) {
                criteria.addOrder(Order.asc(orderColumn));
            } else {
                criteria.addOrder(Order.desc(orderColumn));
            }
        }

        return criteria.list();
    }

    @Override
    public <T extends AbstractEntity> List<T> find(Class<T> claz, Filters filters) {
        return this.find(claz, filters, null);
    }

    @Override
    public <T extends AbstractEntity> List<T> find(Class<T> claz, Filters filters, Sorts sorts) {
        Criteria criteria = this.getCurrentSession().createCriteria(claz);
        this.setFilters(claz, filters, criteria);
        this.setSorts(sorts, criteria);
        return criteria.list();
    }

    @Override
    public <T extends AbstractEntity> Page find(Class<T> claz, Filters filters, Sorts sorts, Page page) {
        return this.find(claz, filters, sorts, page, null);
    }

    @Override
    public <T extends AbstractEntity> Page find(Class<T> claz, Filters filters, Sorts sorts, Page page, List<String> fields) {
        Criteria criteria = this.getCurrentSession().createCriteria(claz);
        this.setFilters(claz, filters, criteria);
        this.setPage(page, criteria);
        this.setSorts(sorts, criteria);
        this.setQueryFields(fields, criteria, claz);
        page.setData(criteria.list());
        return page;
    }

    /**
     * 设置查询排序
     *
     * @param sorts
     * @param criteria
     */
    private void setSorts(final Sorts sorts, final Criteria criteria) {
        if (null == sorts) {
            return;
        }
        for (Sort sort : sorts.getSorts()) {
            criteria.addOrder(sort.getDirection().equalsIgnoreCase("ASC") ? Order.asc(sort.getProperty()) : Order.desc(sort.getProperty()));
        }

    }

    /**
     * 设置查询分页
     *
     * @param page
     * @param criteria
     */
    private void setPage(final Page page, final Criteria criteria) {
        if (null == page) {
            return;
        }

//        if (null == page.getStart() || 0 == page.getStart()) {
        page.setTotal((Long) criteria.setProjection(Projections.rowCount()).uniqueResult());
        criteria.setProjection(null);
//        }
        criteria.setFirstResult(page.getStart()).setMaxResults(page.getLimit());
    }

    private <T extends AbstractEntity> Object typeConvert(Class<T> entityClass, String property, String subProperty, Object value) {

        if (null == value || null == property || null == entityClass) {
            return value;
        }

        try {
            Class propertyType = entityClass.getDeclaredField(property).getType();
            Class valueType = value.getClass();

            if (valueType.equals(propertyType)) {
                return value;
            }

            //长整形转换
            if (Long.class.equals(propertyType)) {
                return Long.valueOf(value.toString());
            }

            //日期类型转换
            if (Date.class.equals(propertyType)) {
                return Long.class.equals(valueType) ? new Date((long) value) : DateUtils.parase(value.toString(), "yyyy-MM-dd HH:mm:ss");
            }

            if (AbstractEntity.class.isAssignableFrom(propertyType)) {
                return typeConvert(propertyType, subProperty, null, value);
            }

        } catch (NoSuchFieldException | SecurityException ex) {
            int pos = property.indexOf(".");
            if (pos <= 0) {
                Class claz = entityClass.getSuperclass();
                if (AbstractLockEntity.class.isAssignableFrom(claz)) {
                    return typeConvert(claz, property, subProperty, value);
                }
            } else {
                return typeConvert(entityClass, property.substring(0, pos), property.substring(pos + 1), value);
            }
        }

        return value;
    }

    private <T extends AbstractEntity> void setFilters(Class<T> claz, Filters filters, Criteria criteria) {

        if (null == filters) {
            return;
        }
        for (Filter filter : filters.getFilters()) {
            //此处暂时不对属性的值最类型转换，交由业务层处理
            filter.setValue(this.typeConvert(claz, filter.getProperty(), null, filter.getValue()));
            switch (filter.getOperation()) {
                case eq:
                    criteria.add(Restrictions.eq(filter.getProperty(), filter.getValue()));
                    break;
                case ne:
                    criteria.add(Restrictions.ne(filter.getProperty(), filter.getValue()));
                    break;
                case in:
                    if (ObjectUtils.isArray(filter.getValue())) {
                        criteria.add(Restrictions.in(filter.getProperty(), (Object[]) filter.getValue()));
                    } else {
                        criteria.add(Restrictions.in(filter.getProperty(), (Collection) filter.getValue()));
                    }
                    break;
                case notIn:
                    if (ObjectUtils.isArray(filter.getValue())) {
                        criteria.add(Restrictions.not(Restrictions.in(filter.getProperty(), (Object[]) filter.getValue())));
                    } else {
                        criteria.add(Restrictions.not(Restrictions.in(filter.getProperty(), (Collection) filter.getValue())));
                    }
                    break;
                case ge:
                    criteria.add(Restrictions.ge(filter.getProperty(), filter.getValue()));
                    break;
                case gt:
                    criteria.add(Restrictions.gt(filter.getProperty(), filter.getValue()));
                    break;
                case le:
                    criteria.add(Restrictions.le(filter.getProperty(), filter.getValue()));
                    break;
                case lt:
                    criteria.add(Restrictions.lt(filter.getProperty(), filter.getValue()));
                    break;
                case like:
                    criteria.add(Restrictions.like(filter.getProperty(), filter.getValue()));
                    break;
                case likeAny:
                    criteria.add(Restrictions.like(filter.getProperty(), filter.getValue().toString(), MatchMode.ANYWHERE));
                    break;
                case likeStart:
                    criteria.add(Restrictions.like(filter.getProperty(), filter.getValue().toString(), MatchMode.START));
                    break;
                case likeEnd:
                    criteria.add(Restrictions.like(filter.getProperty(), filter.getValue().toString(), MatchMode.END));
                    break;
                default:
                    throw new UnsupportedOperationException("Unsupported yet");

            }
        }
    }

    /**
     * 设置查询字段
     *
     * @param queryFields
     * @param criteria
     * @param entityClass
     */
    private void setQueryFields(final List<String> queryFields, final Criteria criteria, final Class entityClass) {
        if (null == queryFields) {
            return;
        }
        ProjectionList pl = Projections.projectionList();
        for (String field : queryFields) {
            if (null == field) {
                continue;
            }
            pl.add(Projections.property(field), field);
        }
        criteria.setProjection(pl).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
    }

    @Override
    public Query getHQLQuery() {
        return this.hibernateHQLQuery;
    }

    @Override
    public Query getSQLQuery() {
        return this.hibernateSQLQuery;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.hibernateHQLQuery = new HibernateHqlQuery(sessionFactory);
        this.hibernateSQLQuery = new HibernateSQLQuery(sessionFactory);
    }
}
