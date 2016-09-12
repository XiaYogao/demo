package com.demo.base;

import java.io.Serializable;

/**
 * 基础model类，可用于分页、排序查询
 *
 * @author sharygus@gmail.com
 * @date 2016/9/12 0012 下午 21:33
 * @since V1.0
 */
public class BaseModel implements Serializable {

    private static final long serialVersionUID = 7432549594542115268L;

    /** 主键ID */
    protected Long id;
    /** 排序字段 */
    private transient String orderField;
    /** 排序类型：ASC(升序)、DESC(降序) */
    private transient String orderFieldType;
    /** 分页下标，最小：0 */
    private transient Integer startIndex;
    /** 每页记录数 */
    private transient Integer pageSize;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderField() {
        return orderField;
    }

    public void setOrderField(String orderField) {
        this.orderField = orderField;
    }

    public String getOrderFieldType() {
        return orderFieldType;
    }

    public void setOrderFieldType(String orderFieldType) {
        this.orderFieldType = orderFieldType;
    }

    public Integer getStartIndex() {
        return (startIndex == null || startIndex < 0) ? 0 : startIndex;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPageSize() {
        return (pageSize == null || pageSize < 0) ? 15 : pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
