package com.lhrl.common.dao;

/**
 *
 * @author Mei Xianzhi
 */
public class DaoFactory {

    static DaoSupport dao;

    public static DaoSupport getDao() {
        return dao;
    }

    public DaoFactory(DaoSupport daoSupport) {
        dao = daoSupport;
    }

}
