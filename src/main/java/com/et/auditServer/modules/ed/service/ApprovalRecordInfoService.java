package com.et.auditServer.modules.ed.service;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;

public interface ApprovalRecordInfoService {

    int insert(ApprovalRecordInfoDTO approvalRecordInfoDTO);
    JsonResult delCertInfo(int processId, String category);
    JsonResult approvalRecordList(int processId, String category);
    JsonResult certExamine(ApprovalRecordInfoDTO approvalRecordInfoDTO);
    JsonResult confExamine(ApprovalRecordInfoDTO approvalRecordInfoDTO);
}
