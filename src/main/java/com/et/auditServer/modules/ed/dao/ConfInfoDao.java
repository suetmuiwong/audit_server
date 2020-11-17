package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.entity.ConfInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfInfoDao extends CrudDao<ConfInfo>{
    int insert(ConfInfo record);
    int update(ConfInfo record);
    int deleteById(@Param("confId") int confId);
    List<ConfInfoDao> findListPage(String allDataUser, @Param("title") String title);
    ConfInfo selectById(@Param("confId") int confId);
    List<ConfInfoDao> selectConfInfoList (@Param("projId") int projId);
    List<ConfInfoDao> selectExecutorConfInfoList (@Param("executor") String executor);
}