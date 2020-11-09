package com.et.auditServer.modules.ed.service;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddProjInfoDTO;
import com.et.auditServer.modules.ed.dto.ProjInfoDTO;


public interface ProjInfoService {
    JsonResult list(int pageNo, int pageSize, String projName);
    JsonResult findByProjId(int projId);
    JsonResult insert(AddProjInfoDTO addProjInfoDTO);
    JsonResult update(ProjInfoDTO projInfoDTO);
    JsonResult delProjInfo(int projId);





}
