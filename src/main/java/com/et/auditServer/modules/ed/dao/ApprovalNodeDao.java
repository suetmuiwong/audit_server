package com.et.auditServer.modules.ed.dao;

import com.et.auditServer.common.persistence.CrudDao;
import com.et.auditServer.modules.ed.entity.ApprovalNodeInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ApprovalNodeDao extends CrudDao<ApprovalNodeInfo>{

    /**
     *新增流程节点信息
     */
    int insert(ApprovalNodeInfo approvalNodeInfo);

    /**
     *根据流程节点查询节点相关信息
     */
    ApprovalNodeInfo approvalNodeInfo(@Param("processId") int processId,@Param("node") int node,@Param("category") String category);

    /**
     * 根据流程节点删除相关节点信息
     */
    int deleteById(@Param("processId") int processId,@Param("node") int node, @Param("category") String category);
}