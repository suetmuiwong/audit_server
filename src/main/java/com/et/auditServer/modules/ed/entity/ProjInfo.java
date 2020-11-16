package com.et.auditServer.modules.ed.entity;

import com.et.auditServer.common.persistence.DataEntity;

import javax.validation.constraints.AssertTrue;
import java.util.Date;
import java.util.List;

public class ProjInfo extends DataEntity<ProjInfo> {
    private int projId;
    private String projName;
    private String projDesc;
    private Date startTime;
    private Date endTime;
    private String manager;
    private String auditee;
    private String auditedManager;
    private String supervisor;
    private String office;
    private String officeManager;
    private String creater;
    private Date createTime;
    private List<CertInfo> CertList;
    private List<ConfInfo> ConfList;

    public int getProjId() {
        return projId;
    }

    public void setProjId(int projId) {
        this.projId = projId;
    }

    public String getProjName() {
        return projName;
    }

    public void setProjName(String projName) {
        this.projName = projName;
    }

    public String getProjDesc() {
        return projDesc;
    }

    public void setProjDesc(String projDesc) {
        this.projDesc = projDesc;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    public String getAuditee() {
        return auditee;
    }

    public void setAuditee(String auditee) {
        this.auditee = auditee;
    }

    public String getAuditedManager() {
        return auditedManager;
    }

    public void setAuditedManager(String auditedManager) {
        this.auditedManager = auditedManager;
    }

    public String getSupervisor() {
        return supervisor;
    }

    public void setSupervisor(String supervisor) {
        this.supervisor = supervisor;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getOfficeManager() {
        return officeManager;
    }

    public void setOfficeManager(String officeManager) {
        this.officeManager = officeManager;
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

    public List<CertInfo> getCertList() {
        return CertList;
    }

    public void setCertList(List<CertInfo> CertList) {
        this.CertList = CertList;
    }

    public List<ConfInfo> getConfList() {
        return ConfList;
    }

    public void setConfList(List<ConfInfo> ConfList) {
        this.ConfList = ConfList;
    }


}