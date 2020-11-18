package com.et.auditServer.modules.ed.service;

import com.et.auditServer.modules.ed.dto.ApprovalNodeDTO;
import com.et.auditServer.modules.ed.entity.ApprovalNodeInfo;
import org.apache.ibatis.annotations.Param;


public interface ApprovalNodeService {
    int insert(ApprovalNodeDTO approvalNodeDTO);
    ApprovalNodeInfo approvalNodeInfo(int processId, int node, String category);
    int deleteById( int processId, int node, String category);
}
