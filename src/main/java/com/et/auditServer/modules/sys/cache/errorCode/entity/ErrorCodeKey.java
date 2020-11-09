package com.et.auditServer.modules.sys.cache.errorCode.entity;

/**
 * Created by xiejf on 2017/2/8.
 */
public class ErrorCodeKey {

    private String errcode;

    private String systemcode;

    public String getErrcode() {
        return errcode;
    }

    public void setErrcode(String errcode) {
        this.errcode = errcode == null ? null : errcode.trim();
    }

    public String getSystemcode() {
        return systemcode;
    }

    public void setSystemcode(String systemcode) {
        this.systemcode = systemcode == null ? null : systemcode.trim();
    }

}
