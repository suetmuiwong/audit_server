package com.et.auditServer.modules.sys.dto;

import java.util.List;

/**
 * @description:
 * @author: qgp
 * @create: 2019-03-12 09:47
 **/
public class UserMenuDto {
    private List<String> MenuCodeList;

    private List<String> MenuNameList;

    public List<String> getMenuCodeList() {
        return MenuCodeList;
    }

    public void setMenuCodeList(List<String> menuCodeList) {
        MenuCodeList = menuCodeList;
    }

    public List<String> getMenuNameList() {
        return MenuNameList;
    }

    public void setMenuNameList(List<String> menuNameList) {
        MenuNameList = menuNameList;
    }
}
