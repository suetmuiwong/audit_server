package com.et.auditServer.modules.ed.service;

import com.et.auditServer.modules.ed.dto.ApprovalNodeInfoDTO;
import com.et.auditServer.modules.ed.entity.ApprovalNodeInfo;

public interface ApprovalNodeInfoService {

    int insert(ApprovalNodeInfoDTO approvalNodeDTO);
    ApprovalNodeInfo approvalNodeInfo(int processId, int node, String category);
    int deleteById( int processId, int node, String category);
}
