package com.demo.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * 基础数据操作接口
 */
public interface IBaseDao<T, ID extends Serializable> {

    /**
     * 新增记录
     * @param t 新增数据
     * @return 影响行数
     */
    int insert(T t);

    /**
     * 批量插入记录
     * @param ts 需要插入的记录
     * @return 影响行数
     */
    int batchInsert(List<T> ts);

    /**
     * 按主键删除
     * @param id 主键
     * @return 影响行数
     */
    int delete(ID id);

    /**
     * 按主键批量删除记录
     * @param ids 主键集合
     * @return 影响行数
     */
    int batchDelete(ID... ids);

    /**
     * 按主键更新记录
     * @param t 要更新的数据（包含主键值）
     * @return 影响行数
     */
    int update(T t);

    /**
     * 批量按住见更新记录
     * @param ts 更新的数据
     * @return 影响行数
     */
    int batchUpdate(List<T> ts);

    /**
     * 按主键获取记录
     * @param id 主键
     * @return 对应记录数据
     */
    T get(ID id);

    /**
     * 查找记录
     * @param condition 查询条件
     * @return 记录数据列表
     */
    List<T> find(T condition);

    /**
     * 查询记录数
     * @param condition 查询条件
     * @return 记录数
     */
    Long count(T condition);

    /**
     * 带分页、排序的查询
     * @param condition 条件
     * @return 记录数据列表
     */
    List<T> list(T condition);

}
