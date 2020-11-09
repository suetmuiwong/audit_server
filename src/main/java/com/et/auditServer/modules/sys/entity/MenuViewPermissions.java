package com.et.auditServer.modules.sys.entity;

import com.et.auditServer.common.persistence.DataEntity;

public class MenuViewPermissions extends DataEntity<MenuViewPermissions> {
    private String id;
    private String menuCode;
    private String viewModeCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMenuCode() {
        return menuCode;
    }

    public void setMenuCode(String menuCode) {
        this.menuCode = menuCode;
    }

    public String getViewModeCode() {
        return viewModeCode;
    }

    public void setViewModeCode(String viewModeCode) {
        this.viewModeCode = viewModeCode;
    }
}