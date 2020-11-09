package com.et.auditServer.modules.sys.cache.errorCode.mapper;

import com.et.auditServer.modules.sys.cache.errorCode.entity.ErrorCode;
import com.et.auditServer.modules.sys.cache.errorCode.entity.ErrorCodeKey;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by xiejf on 2017/2/8.
 */
@Repository
public interface ErrorCodeMapper {
    int deleteByPrimaryKey(ErrorCodeKey key);

    int insert(ErrorCode record);

    int insertSelective(ErrorCode record);

    ErrorCode selectByPrimaryKey(ErrorCodeKey key);

    int updateByPrimaryKeySelective(ErrorCode record);

    int updateByPrimaryKey(ErrorCode record);

    List<ErrorCode> selectAllErrorCodes(ErrorCode errorCode);
}
