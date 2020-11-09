package com.et.auditServer.modules.sys.dto;

/**
 * @description:修改密码dto
 * @author: qgp
 * @create: 2019-03-04 10:00
 **/
public class UserDto {
    private String userName;
    private String userPassword;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
}
