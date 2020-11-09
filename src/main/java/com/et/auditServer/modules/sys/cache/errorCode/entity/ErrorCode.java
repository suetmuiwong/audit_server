package com.et.auditServer.modules.sys.cache.errorCode.entity;

/**
 * Created by xiejf on 2017/2/8.
 */
public class ErrorCode extends ErrorCodeKey{

    private String errmsg;

    private String outcode;

    private String outmsg;

    private String isconvert;

    private String isvalid;

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg == null ? null : errmsg.trim();
    }

    public String getOutcode() {
        return outcode;
    }

    public void setOutcode(String outcode) {
        this.outcode = outcode == null ? null : outcode.trim();
    }

    public String getOutmsg() {
        return outmsg;
    }

    public void setOutmsg(String outmsg) {
        this.outmsg = outmsg == null ? null : outmsg.trim();
    }

    public String getIsconvert() {
        return isconvert;
    }

    public void setIsconvert(String isconvert) {
        this.isconvert = isconvert == null ? null : isconvert.trim();
    }

    public String getIsvalid() {
        return isvalid;
    }

    public void setIsvalid(String isvalid) {
        this.isvalid = isvalid == null ? null : isvalid.trim();
    }

    @Override
    public String toString() {
        return "ErrorCode{" +
                "errmsg='" + errmsg + '\'' +
                ", outcode='" + outcode + '\'' +
                ", outmsg='" + outmsg + '\'' +
                ", isconvert='" + isconvert + '\'' +
                ", isvalid='" + isvalid + '\'' +
                '}';
    }

}
