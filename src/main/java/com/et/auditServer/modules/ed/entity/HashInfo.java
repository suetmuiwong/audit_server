package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;

import java.util.Date;

public class HashInfo extends DataEntity<HashInfo> {
    private int hashId;
    private String hashName;
    private String hashValue;
    private String hashType;
    private String title;
    private String creater;
    private Date createTime;

    public int getHashId() {
        return hashId;
    }

    public void setHashId(int hashId) {
        this.hashId = hashId;
    }

    public String getHashName() {
        return hashName;
    }

    public void setHashName(String hashName) {
        this.hashName = hashName;
    }

    public String getHashValue() {
        return hashValue;
    }

    public void setHashValue(String hashValue) {
        this.hashValue = hashValue;
    }

    public String getHashType() {
        return hashType;
    }

    public void setHashType(String hashType) {
        this.hashType = hashType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}