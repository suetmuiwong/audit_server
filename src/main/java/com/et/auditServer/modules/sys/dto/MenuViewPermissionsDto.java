package com.et.auditServer.modules.sys.dto;


public class MenuViewPermissionsDto {
    private String menuViewPermissionsId;
    private String menuCode;
    private String viewModeId;

    private String modeCode;
    private String modeName;
    private String modeType;
    private String modeDesc;
    private String modeLevel;
    private String enabledFlag;

    public String getMenuViewPermissionsId() {
        return menuViewPermissionsId;
    }

    public void setMenuViewPermissionsId(String menuViewPermissionsId) {
        this.menuViewPermissionsId = menuViewPermissionsId;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }


    public String getViewModeId() {
        return viewModeId;
    }

    public void setViewModeId(String viewModeId) {
        this.viewModeId = viewModeId;
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

    public String getModeCode() {
        return modeCode;
    }

    public void setModeCode(String modeCode) {
        this.modeCode = modeCode;
    }
}
