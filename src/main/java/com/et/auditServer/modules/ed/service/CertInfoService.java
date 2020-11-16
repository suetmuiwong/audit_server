package com.et.auditServer.modules.ed.service;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddCertInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.entity.CertInfo;
import com.et.auditServer.modules.ed.entity.ConfInfo;


public interface CertInfoService {
    JsonResult list(int pageNo, int pageSize, String title);
    JsonResult findByCertId(int certId);
    JsonResult insert(AddCertInfoDTO addCertInfoDTO);
    JsonResult update(CertInfoDTO certInfoDTO);
    JsonResult delCertInfo(int certId);
    JsonResult certList(int pageNo, int pageSize,int proiId);
    JsonResult certList(int pageNo, int pageSize,String executor);
    CertInfo certInfo(int CertId);
}
