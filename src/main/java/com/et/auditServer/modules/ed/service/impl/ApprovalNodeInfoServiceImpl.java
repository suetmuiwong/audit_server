package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.modules.ed.dao.ApprovalNodeInfoDao;
import com.et.auditServer.modules.ed.dto.ApprovalNodeInfoDTO;
import com.et.auditServer.modules.ed.entity.ApprovalNodeInfo;
import com.et.auditServer.modules.ed.service.ApprovalNodeInfoService;
import org.springframework.stereotype.Service;

@Service
public class ApprovalNodeInfoServiceImpl extends CrudService<ApprovalNodeInfoDao, ApprovalNodeInfo>  implements ApprovalNodeInfoService {
    @Override
    public int insert(ApprovalNodeInfoDTO approvalNodeDTO) {
        return dao.insert(approvalNodeDTO);
    }

    @Override
    public ApprovalNodeInfo approvalNodeInfo(int processId, int node, String category) {
        return dao.selectApprovalNodeInfo(processId,node,category);
    }

    @Override
    public int deleteById(int processId, int node, String category) {
        return dao.deleteById(processId,node,category);
    }
}
