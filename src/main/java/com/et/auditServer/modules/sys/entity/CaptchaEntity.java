package com.et.auditServer.modules.sys.entity;

import java.util.Date;

/**
 * @description:
 * @author: qgp
 * @create: 2019-02-26 16:51
 **/
public class CaptchaEntity {
    private String captchaId;
    private String captcha;
    private Date expireTime;
    private Date updateTime;

    public String getCaptchaId() {
        return captchaId;
    }

    public void setCaptchaId(String captchaId) {
        this.captchaId = captchaId;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
