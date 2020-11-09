package com.et.auditServer.modules.sys.entity;

import com.et.auditServer.common.persistence.DataEntity;

public class MenuViewMode extends DataEntity<MenuViewMode> {
    private String id;
    private String modeCode;
    private String modeName;
    private String modeType;
    private String modeDesc;
    private String modeLevel;
    private String enabledFlag;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getModeCode() {
        return modeCode;
    }

    public void setModeCode(String modeCode) {
        this.modeCode = modeCode;
    }

    public String getModeName() {
        return modeName;
    }

    public void setModeName(String modeName) {
        this.modeName = modeName;
    }

    public String getModeType() {
        return modeType;
    }

    public void setModeType(String modeType) {
        this.modeType = modeType;
    }

    public String getModeDesc() {
        return modeDesc;
    }

    public void setModeDesc(String modeDesc) {
        this.modeDesc = modeDesc;
    }

    public String getModeLevel() {
        return modeLevel;
    }

    public void setModeLevel(String modeLevel) {
        this.modeLevel = modeLevel;
    }

    public String getEnabledFlag() {
        return enabledFlag;
    }

    public void setEnabledFlag(String enabledFlag) {
        this.enabledFlag = enabledFlag;
    }
}