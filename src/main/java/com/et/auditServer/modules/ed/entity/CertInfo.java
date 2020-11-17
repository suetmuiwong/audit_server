package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;

import java.util.Date;
import java.util.List;

public class CertInfo extends DataEntity<CertInfo> {
    private int certId;
    private String title;
    private String certDesc;
    private String manager;
    private String creater;
    private String executor;
    private String executorPhone;
    private Date createTime;
    private String deadline;
    private int status;
    private String picture;
    private String picHash;
    private int transfer;
    private String certDest;
    private String auditor;
    private int projId;
    private int node;

    public int getCertId() {
        return certId;
    }

    public void setCertId(int certId) {
        this.certId = certId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCertDesc() {
        return certDesc;
    }

    public void setCertDesc(String certDesc) {
        this.certDesc = certDesc;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public String getExecutor() {
        return executor;
    }

    public void setExecutor(String executor) {
        this.executor = executor;
    }

    public String getExecutorPhone() {
        return executorPhone;
    }

    public void setExecutorPhone(String executorPhone) {
        this.executorPhone = executorPhone;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
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

    public int getTransfer() {
        return transfer;
    }

    public void setTransfer(int transfer) {
        this.transfer = transfer;
    }

    public String getCertDest() {
        return certDest;
    }

    public void setCertDest(String certDest) {
        this.certDest = certDest;
    }

    public String getAuditor() {
        return auditor;
    }

    public void setAuditor(String auditor) {
        this.auditor = auditor;
    }

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public int getNode() {
        return node;
    }

    public void setNode(int node) {
        this.node = node;
    }
}