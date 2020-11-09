package com.et.auditServer.modules.ed.service;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddConfInfoDTO;
import com.et.auditServer.modules.ed.dto.ConfInfoDTO;


public interface ConfInfoService {
    JsonResult list(int pageNo, int pageSize, String title);
    JsonResult findByConfId(int confId);
    JsonResult insert(AddConfInfoDTO addConfInfoDTO);
    JsonResult update(ConfInfoDTO confInfoDTO);
    JsonResult delConfInfo(int confId);


}
