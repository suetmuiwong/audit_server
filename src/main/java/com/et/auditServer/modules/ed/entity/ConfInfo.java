package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;

import java.util.Date;

public class ConfInfo extends DataEntity<ConfInfo> {
    private int confId;
    private String title;
    private String confDesc;
    private String summary;
    private String auditor;
    private String creater;
    private String audited;
    private Date createTime;
    private Date deadline;
    private int status;
    private String picture;
    private String picHash;
    private String reviewer;
    private Date auditTime;
    private Date reviewTime;
    private String manager;
    private String managerLeader;
    private String department;

    public int getConfId() {
        return confId;
    }

    public void setConfId(int confId) {
        this.confId = confId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getConfDesc() {
        return confDesc;
    }

    public void setConfDesc(String confDesc) {
        this.confDesc = confDesc;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getAudited() {
        return audited;
    }

    public void setAudited(String audited) {
        this.audited = audited;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicHash() {
        return picHash;
    }

    public void setPicHash(String picHash) {
        this.picHash = picHash;
    }

    public String getReviewer() {
        return reviewer;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public Date getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(Date auditTime) {
        this.auditTime = auditTime;
    }

    public Date getReviewTime() {
        return reviewTime;
    }

    public void setReviewTime(Date reviewTime) {
        this.reviewTime = reviewTime;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getManagerLeader() {
        return managerLeader;
    }

    public void setManagerLeader(String managerLeader) {
        this.managerLeader = managerLeader;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }
}