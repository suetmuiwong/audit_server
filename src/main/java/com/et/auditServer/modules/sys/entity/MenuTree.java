package com.et.auditServer.modules.sys.entity;

import java.util.List;

public class MenuTree extends Menu {
    private List<MenuTree> childList;	// 子菜单
    private String parentName;//父菜单名称

    public List<MenuTree> getChildList() {
        return childList;
    }

    public void setChildList(List<MenuTree> childList) {
        this.childList = childList;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }
}
