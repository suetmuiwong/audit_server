package com.et.auditServer.modules.sys.service;

import com.alibaba.fastjson.JSONObject;
import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.exception.BusinessException;
import com.et.auditServer.common.utils.*;
import com.et.auditServer.modules.sys.cache.errorCode.service.RetCodeTransferService;
import com.et.auditServer.modules.sys.dao.CaptchaDao;
import com.et.auditServer.modules.sys.entity.CaptchaEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: qgp
 * @create: 2019-03-09 16:38
 **/
@Service
public class CaptchaService {
    @Autowired
    private CaptchaDao captchaDao;
    @Autowired
    private RetCodeTransferService retCodeTransferService;


    @Autowired
    private RedisUtils redisCache;

    public CaptchaEntity queryByCaptcha(String captcha, String captchaId) {
        return captchaDao.queryByCaptcha(captcha, captchaId);
    }

    public CaptchaEntity queryByToken(String token) {
        return captchaDao.queryByToken(token);
    }

    public void save(CaptchaEntity token){
        captchaDao.save(token);
    }

    public void update(CaptchaEntity token){
        captchaDao.update(token);
    }

    public boolean isExpired(Date expireTime){
        Date d=new Date();
        return d.getTime()>expireTime.getTime()?true:false;
    }

    @Transactional
    public Map<String, Object> createToken(String captcha) {
        //生成一个id
        String id = IdGenerator.get();
        //当前时间
        Date now = new Date();
        int EXPIRE = 12345;
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        CaptchaEntity tokenEntity  = new CaptchaEntity();
        tokenEntity.setCaptcha(captcha);
        tokenEntity.setCaptchaId(id);
        tokenEntity.setUpdateTime(now);
        tokenEntity.setExpireTime(expireTime);
        //保存token
        save(tokenEntity);
        Map<String, Object> map = new HashMap<>();
        map.put("expire", EXPIRE);
        map.put("captchaId", id);
        return map;
    }

    public void deleteByCaptchaId(String captchaId) {
        captchaDao.deleteByCaptchaId(captchaId);
    }

    public void deleteByExpired() {
        //当前时间
        Date now = new Date();
        int EXPIRE = 12345;

        //过期时间
        Date expireTime = new Date(now.getTime() - EXPIRE * 1000);
        captchaDao.deleteByExpired(expireTime);
    }

    /**
     * 获取FastDFS微服务token
     * */
    public String getFastDFSToken() {
        String token;
        String appId = "1234555";
        String appKey = "456778";
        String tokenUrl = "7898888888";
        String fastDfsToken = (String) redisCache.get(Constant.FAST_DFS_TOKEN_ID);
        if (StringUtil.isEmpty(fastDfsToken)) {
            StringBuffer params = new StringBuffer();
            params.append("appId=" );
            params.append(appId);
            params.append("&");
            params.append("appKey=");
            params.append(appKey);
            JSONObject result = HttpUtils.doGet(tokenUrl, params);
            if (result == null || StringUtils.isEmpty(result.getString("token"))) {
                throw new BusinessException(retCodeTransferService.getTransfer(JsonReturnCode.GET_FASTDFS_TOKEN_ERROR));
            }
            token = result.getString("token");
            fastDfsToken = token;
            Date expireDate = DateUtils.stringToDate(result.getString("expiredTime"), DateUtils.DEFAULT_DATE_TIME_PATTERN);
            //保存token
            redisCache.set(Constant.FAST_DFS_TOKEN_ID,token ,expireDate.getTime()/1000-60);
        }
        return fastDfsToken;
    }
}
