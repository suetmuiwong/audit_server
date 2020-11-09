package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.entity.HashInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface HashInfoDao extends CrudDao<HashInfo>{
    int insert(HashInfo record);
    int update(HashInfo record);
    int deleteById(@Param("hashId") int hashId);
    List<HashInfoDao> findListPage(String allDataUser, @Param("hashName") String hashName);
    HashInfo selectById(@Param("hashId") int hashId);

}