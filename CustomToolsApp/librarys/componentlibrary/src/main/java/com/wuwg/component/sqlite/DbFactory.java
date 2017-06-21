package com.wuwg.component.sqlite;

/**
 * 数据库工厂类
 * Created by wuwengao on 2017/6/21.
 */
public class DbFactory {

    private static DbFactory instance;

    public static DbFactory getInstance() {
        synchronized (DbFactory.class) {
            if (instance == null) {
                instance = new DbFactory();
            }
        }
        return instance;
    }

    /**
     * 获取操作 entityClass 实体类的 daoClass
     *
     * @param daoClass
     * @param entityClass
     * @return
     */
    private IBaseDao getDbDao(Class daoClass, Class entityClass) {
        IBaseDao baseDao = null;
        return baseDao;
    }

}
