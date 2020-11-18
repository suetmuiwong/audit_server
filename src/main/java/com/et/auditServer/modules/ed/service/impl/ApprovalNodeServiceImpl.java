package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.ApprovalNodeDao;
import com.et.auditServer.modules.ed.dao.ApprovalRecordInfoDao;
import com.et.auditServer.modules.ed.dto.ApprovalNodeDTO;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.entity.ApprovalNodeInfo;
import com.et.auditServer.modules.ed.entity.ApprovalRecordInfo;
import com.et.auditServer.modules.ed.service.ApprovalNodeService;
import com.et.auditServer.modules.ed.service.ApprovalRecordService;
import com.et.auditServer.modules.ed.service.CertInfoService;
import com.et.auditServer.modules.sys.cache.UserUtils;
import com.et.auditServer.modules.sys.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApprovalNodeServiceImpl extends CrudService<ApprovalNodeDao, ApprovalNodeInfo> implements ApprovalNodeService {


    @Override
    public int insert(ApprovalNodeDTO approvalNodeDTO) {
        return dao.insert(approvalNodeDTO);
    }

    @Override
    public ApprovalNodeInfo approvalNodeInfo(int processId, int node,String category) {
        return dao.approvalNodeInfo(processId,node,category);
    }

    @Override
    public int deleteById(int processId, int node,String category) {
        return dao.deleteById(processId,node,category);
    }
}
