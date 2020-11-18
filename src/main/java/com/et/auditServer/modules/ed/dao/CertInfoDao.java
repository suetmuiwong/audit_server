package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.entity.CertInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CertInfoDao extends CrudDao<CertInfo>{
    int insert(CertInfo record);
    int update(CertInfo record);
    int deleteById(@Param("certId") int certId);
    List<CertInfoDTO> findListPage(String allDataUser, @Param("title") String title);
    CertInfo selectById(@Param("certId") int certId);
    //通过项目Id查询所属项目的的存证信息
    List<CertInfoDTO> findProjIdList (@Param("projId") int projId);
    //查询执行人所属存证信息
    List<CertInfoDTO> findExecutorList (@Param("executor") String executor);
}