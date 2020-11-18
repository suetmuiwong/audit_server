package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.dto.ConfInfoDTO;
import com.et.auditServer.modules.ed.entity.ConfInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ConfInfoDao extends CrudDao<ConfInfo>{
    int insert(ConfInfo record);
    int update(ConfInfo record);
    int deleteById(@Param("confId") int confId);
    List<ConfInfoDTO> findListPage(String allDataUser, @Param("title") String title);
    ConfInfo selectById(@Param("confId") int confId);
    List<ConfInfoDTO> selectConfInfoList (@Param("projId") int projId);
    List<ConfInfoDTO> selectExecutorConfInfoList (@Param("executor") String executor);
}