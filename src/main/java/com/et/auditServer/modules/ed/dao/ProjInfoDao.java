package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.modules.ed.dto.ProjInfoDTO;
import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.entity.ProjInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjInfoDao extends CrudDao<ProjInfo>{
    int insert(ProjInfo record);
    int update(ProjInfo record);
    int deleteById(@Param("projId") int projId);
    List<ProjInfoDTO> findListPage(String allDataUser, @Param("projName") String projName);
    ProjInfo selectById(@Param("projId") int projId);

}