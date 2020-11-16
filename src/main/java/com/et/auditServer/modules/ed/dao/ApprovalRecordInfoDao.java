package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.entity.ApprovalRecordInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalRecordInfoDao extends CrudDao<ApprovalRecordInfo>{
    /**
     *新增审批信息
     */
    int insert(ApprovalRecordInfo approvalRecord);
    /**
     *根据存证或者确认单ID以及区分状态查询审批记录
     */
    List<ApprovalRecordInfoDTO> approvalRecordList(@Param("processId") int processId,@Param("category") String category);
    /**
     *根据存证或确认单ID状态删除审批信息
     */
    int deleteById(@Param("processId") int processId,@Param("category") String category);
}