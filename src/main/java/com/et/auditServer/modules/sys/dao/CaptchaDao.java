package com.et.auditServer.modules.sys.dao;


import com.et.auditServer.modules.sys.entity.CaptchaEntity;
import org.apache.ibatis.annotations.Param;

import java.util.Date;

public interface CaptchaDao {
    CaptchaEntity queryByCaptcha(@Param("captcha") String captcha, @Param("captchaId") String captchaId);

    CaptchaEntity queryByToken(@Param("token")String token);

    int save(CaptchaEntity captchaEntity);

    int update(CaptchaEntity captchaEntity);

    int deleteByCaptchaId(@Param("captchaId")String captchaId);

    void deleteByExpired(@Param("expireDate")Date expireDate);

    CaptchaEntity selectByCaptchaId(@Param("captchaId") String captchaId);
}
