/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.et.auditServer.common.persistence;


import com.et.auditServer.modules.sys.entity.User;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.Length;

import java.util.Date;
import java.util.List;

/**
 * 数据Entity类
 *
 * @author ThinkGem
 * @version 2014-05-16
 */
public abstract class DataEntity<T> extends BaseEntity<T> {

    private static final long serialVersionUID = 1L;

    protected String remark;    // 备注
    protected String created;    // 创建者
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    protected Date createdDate;    // 创建日期
    protected String lastUpdated;    // 更新者
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd")
    protected Date lastUpdatedDate;    // 更新日期

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


    public DataEntity() {
        super();
        /*this.status = DEL_FLAG_NORMAL;*/
    }


    /**
     * 插入之前执行方法，需要手动调用
     */
    @Override
    public void preInsert(String userName) {
        this.created = userName;
        this.lastUpdated = userName;
        this.lastUpdatedDate = new Date();
        this.createdDate = this.lastUpdatedDate;
    }

    /**
     * 更新之前执行方法，需要手动调用
     */
    @Override
    public void preUpdate(String userName) {
        this.lastUpdated = userName;
        this.lastUpdatedDate = new Date();
    }

    @Length(min = 0, max = 255)
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    // @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(String lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

   // @JsonFormat(pattern = "yyyy-MM-dd")
    public Date getLastUpdatedDate() {
        return lastUpdatedDate;
    }

    public void setLastUpdatedDate(Date lastUpdatedDate) {
        this.lastUpdatedDate = lastUpdatedDate;
    }

}
