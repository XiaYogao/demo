package com.demo.base;

import java.io.Serializable;
import java.util.List;

/**
 * @author xiayongao@gmail.com
 * @date 2016/9/13 0013 上午 01:46
 * @since V1.0
 */
public interface IBaseService<T, ID extends Serializable> {

    /**
     * 添加纪录
     *
     * @param t 数据
     * @return 影响行数
     */
    int add(T t);

    /**
     * 修改纪录
     *
     * @param t 数据
     * @return 影响行数
     */
    int modify(T t);

    /**
     * 保存或更新记录
     *
     * @param t 数据
     * @return 影响行数
     */
    int saveOrModify(T t);

    /**
     * 批量保存记录
     *
     * @param ts 数据
     * @return 影响行数
     */
    int batchSave(List<T> ts);

    /**
     * 批量更新记录
     *
     * @param ts 数据
     * @return 影响行数
     */
    int batchModify(List<T> ts);

    /**
     * 按主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    int remove(ID id);

    /**
     * 按主键批量删除记录
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    int batchRemove(ID... ids);

    /**
     * 按主键获取记录
     *
     * @param id 主键
     * @return 对应记录数据
     */
    T get(ID id);

    /**
     * 查询记录数
     *
     * @param condition 查询条件
     * @return 记录数
     */
    Long count(T condition);

    /**
     * 带分页、排序的查询
     *
     * @param condition 条件
     * @return 记录数据列表
     */
    List<T> find(T condition);

}
