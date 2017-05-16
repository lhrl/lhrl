package com.lhrl.common.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 *
 * @author Mei Xianzhi
 */
public class DaoFactoryBean implements FactoryBean<DaoFactory>, InitializingBean {

    private SessionFactory sessionFactory;
    private DaoFactory daoFactory;

    @Override
    public DaoFactory getObject() throws Exception {
        return daoFactory;
    }

    @Override
    public Class<?> getObjectType() {
        return DaoFactory.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

        CommonDao commonDao = new CommonDao(sessionFactory);
        commonDao.afterPropertiesSet();
        this.daoFactory = new DaoFactory(commonDao);
    }

}
