package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;

import java.util.Date;

public class ApprovalRecordInfo extends DataEntity<ApprovalRecordInfo> {

    private int Id;
    private int processId;
    private String executor;
    private String stayExecutor;
    private int status;
    private String approvalOpinion;
    private int attachment;
    private Date approvalDate;
    private String category;
    private String creater;
    private Date createTime;
    private String updates;
    private Date updateTime;


    public int getId() {
        return Id;
    }
    public void setId(int Id) {
        this.Id = Id;
    }

    public int getProcessId() {
        return processId;
    }
    public void setProcessId(int processId) {
        this.processId = processId;
    }

    public String getExecutor() {
        return executor;
    }
    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getStayExecutor() {
        return stayExecutor;
    }
    public void setStayExecutor(String stayExecutor) {
        this.stayExecutor = stayExecutor;
    }

    public int  getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }

    public String getApprovalOpinion() {
        return approvalOpinion;
    }
    public void setApprovalOpinion(String approvalOpinion) {
        this.approvalOpinion = approvalOpinion;
    }

    public int getAttachment() {
        return attachment;
    }
    public void setAttachment(int attachment) {
        this.attachment = attachment;
    }

    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }

    public Date getApprovalDate() {
        return approvalDate;
    }
    public void setApprovalDate(Date approvalDate) {
        this.approvalDate = approvalDate;
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

    public String getUpdates() {
        return updates;
    }
    public void setUpdates(String updates) {
        this.updates = updates;
    }

    public Date getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
