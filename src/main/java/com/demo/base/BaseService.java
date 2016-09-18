package com.demo.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

/**
 * @author xiayongao@gmail.com
 * @date 2016/9/13 0013 上午 01:39
 * @since V1.0
 */
public abstract class BaseService<T, ID extends Serializable> implements IBaseService<T, ID> {

    private final static Logger LOG = LoggerFactory.getLogger(BaseService.class);

    protected abstract IBaseDao<T, ID> getDao();

    /**
     * 添加纪录
     *
     * @param t 数据
     * @return 影响行数ID
     */
    @Override
    public int add(T t) {
        LOG.debug(String.format("add record: data->%s", t));
        return getDao().insert(t);
    }

    /**
     * 修改纪录
     *
     * @param t 数据
     * @return 影响行数
     */
    @Override
    public int modify(T t) {
        LOG.debug(String.format("modify record: data->%s", t));
        return getDao().update(t);
    }

    /**
     * 保存或更新记录
     *
     * @param t 数据
     * @return 影响行数
     */
    @Override
    public int saveOrModify(T t) {
        Long id = 0L;
        if (t instanceof BaseModel) {
            id = ((BaseModel) t).getId();
        } else {
            try {
                Class<?> clz = t.getClass();
                id = (Long) clz.getMethod("getId").invoke(t);
            } catch (Exception e) {
                LOG.error("entity has no primary key id", e);
                throw new RuntimeException("获取对象主键值失败", e);
            }
        }
        if (id != null && id > 0) {
            LOG.debug(String.format("modify record: data->%s", t));
            return modify(t);
        }
        return add(t);
    }

    /**
     * 批量保存记录
     *
     * @param ts 数据
     * @return 影响行数
     */
    @Override
    public int batchSave(List<T> ts) {
        if (ts != null || ts.size() > 0) {
            LOG.debug(String.format("batch add record: size->%d", ts.size()));
            return getDao().batchInsert(ts);
        }
        return 0;
    }

    /**
     * 批量更新记录
     *
     * @param ts 数据
     * @return 影响行数
     */
    @Override
    public int batchModify(List<T> ts) {
        if (ts != null || ts.size() > 0) {
            LOG.debug(String.format("batch modify record: size->%d", ts.size()));
            return getDao().batchUpdate(ts);
        }
        return 0;
    }

    /**
     * 按主键删除
     *
     * @param id 主键
     * @return 影响行数
     */
    @Override
    public int remove(ID id) {
        LOG.debug(String.format("remove record: id->%d", id));
        return getDao().delete(id);
    }

    /**
     * 按主键批量删除记录
     *
     * @param ids 主键集合
     * @return 影响行数
     */
    @Override
    public int batchRemove(ID... ids) {
        LOG.debug(String.format("remove record: ids->%s", Arrays.toString(ids)));
        return getDao().batchDelete(ids);
    }

    /**
     * 按主键获取记录
     *
     * @param id 主键
     * @return 对应记录数据
     */
    @Override
    public T get(ID id) {
        return getDao().get(id);
    }

    /**
     * 查询记录数
     *
     * @param condition 查询条件
     * @return 记录数
     */
    @Override
    public Long count(T condition) {
        return getDao().count(condition);
    }

    /**
     * 带分页、排序的查询
     *
     * @param condition 条件
     * @return 记录数据列表
     */
    @Override
    public List<T> list(T condition) {
        return getDao().list(condition);
    }

}
