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
    //通过项目Id查询所属项目的的存证信息
    List<ConfInfoDTO> selectConfInfoList (@Param("projId") int projId);
    //查询执行人所属存证信息
    List<ConfInfoDTO> selectExecutorConfInfoList (@Param("executor") String executor);
}