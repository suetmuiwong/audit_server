package com.et.auditServer.modules.ed.service;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddHashInfoDTO;
import com.et.auditServer.modules.ed.dto.HashInfoDTO;


public interface HashInfoService {
    JsonResult list(int pageNo, int pageSize, String hashName);
    JsonResult findByHashId(int hashId);
    JsonResult insert(AddHashInfoDTO addHashInfoDTO);
    JsonResult update(HashInfoDTO hashInfoDTO);
    JsonResult delHashInfo(int hashId);





}
