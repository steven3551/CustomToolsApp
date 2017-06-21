package com.wuwg.component.sqlite;

/**
 * Created by wuwengao on 2017/6/21.
 */
public interface IBaseDao<T> {

    /**
     * 创建table
     */
    void createTable();

    /**
     * 增加数据
     * @param entity
     */
    void insert(T entity);

    /**
     * 删除数据
     * @param entity
     */
    void delete(T entity);

    /**
     * 修改数据
     * @param entity
     */
    void update(T entity);

    /**
     * 查询
     */
    void query();

}
