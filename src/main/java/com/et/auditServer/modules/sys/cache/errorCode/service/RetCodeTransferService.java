package com.et.auditServer.modules.sys.cache.errorCode.service;

import com.et.auditServer.common.exception.BusinessException;
import com.et.auditServer.common.utils.JsonReturnCode;
import com.et.auditServer.modules.sys.cache.errorCode.entity.ErrorCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by xiejf on 2017/1/19.
 */
@Service("retCodeTransferService")
public class RetCodeTransferService implements InitializingBean {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ErrorCodeService errorCodeService;

    private Map<String, ErrorCode> retCodes;

    //数据库错误信息存入缓存
    public void loadRetCodeTransfer() throws BusinessException {
        //1、清理数据
        if(retCodes == null) {
            logger.info("retCodes缓存为空，重新生成一个新的实例");
            retCodes = new HashMap<String, ErrorCode>();
        } else {
            logger.info("retCodes缓存不为空，清空其缓存内容");
            retCodes.clear();
        }
        //2、读取数据并重新加载
        ErrorCode errorCode = new ErrorCode();
        //无论是否需要转译，都需要查询出来，所以必须查到所有的使用中的参数
        errorCode.setIsvalid("1");
        List<ErrorCode> errorCodes = errorCodeService.getAllErrorCodes(errorCode);
        if(errorCodes==null || errorCodes.size()==0){
            logger.info("没有需要进行转译的错误码，直接返回方法调用");
            return;
        }
        //3、将数据存入缓存中
        int num = errorCodes.size();
        ErrorCode temp = null;
        for(int i=0; i<num; i++){
            try{
                temp = errorCodes.get(i);
                retCodes.put(temp.getSystemcode() + temp.getErrcode(), temp);
            }catch(Exception e){
                logger.error("本次错误码加载出现异常，请查询原因", e);
                throw new BusinessException(JsonReturnCode.FAIL);
            }
        }
        logger.info("本次错误码重新加载共加载{}个错误码映射", num);
    }

    //业务逻辑代码抛出异常时调用
    public Map<String, String> getTransfer(JsonReturnCode errorEnum) {
        ErrorCode errorCode = retCodes.get("ZT" + errorEnum.getCode());
        Map<String, String> result = new HashMap<String, String>();
        if(errorCode == null) {
            result.put("errorCode", errorEnum.getCode());
            result.put("errorMsg", "系统错误！");
        }else{
            //如果转译标志不为空且等于1，表示需要进行错误信息转译
            //如果为空或者是0等，不需要进行转译
            if(errorCode.getIsconvert()!=null && errorCode.getIsconvert().equals("1")) {
                result.put("errorCode", errorCode.getOutcode());
                result.put("errorMsg", errorCode.getOutmsg());
            }else{
                result.put("errorCode", errorCode.getErrcode());
                result.put("errorMsg", errorCode.getErrmsg());
            }
        }
        return result;
    }

    //此处与Spring进行了耦合
    @Override
    public void afterPropertiesSet() throws BusinessException {
        try{
            if(retCodes == null)
                loadRetCodeTransfer();
        }catch(Exception e){
            logger.error("初始化返回码映射缓存失败，请查询原因", e);
            throw e;
        }
    }
}
