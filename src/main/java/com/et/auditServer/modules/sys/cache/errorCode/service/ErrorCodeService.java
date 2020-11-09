package com.et.auditServer.modules.sys.cache.errorCode.service;

import com.et.auditServer.common.exception.BusinessException;
import com.et.auditServer.common.utils.JsonReturnCode;
import com.et.auditServer.modules.sys.cache.errorCode.entity.ErrorCode;
import com.et.auditServer.modules.sys.cache.errorCode.mapper.ErrorCodeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by xiejf on 2017/2/8.
 */
@Service("errorCodeService")
public class ErrorCodeService {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ErrorCodeMapper errorCodeMapper;

    public List<ErrorCode> getAllErrorCodes(ErrorCode errorCode) throws BusinessException {
        try{
            return errorCodeMapper.selectAllErrorCodes(errorCode);
        }catch(Exception e){
            logger.error("读取数据库错误码内容失败", e);
            throw new BusinessException(JsonReturnCode.FAIL);
        }
    }
}
