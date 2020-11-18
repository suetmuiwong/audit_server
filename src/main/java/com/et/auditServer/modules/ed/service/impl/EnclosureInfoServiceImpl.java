package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.EnclosureInfoDao;
import com.et.auditServer.modules.ed.entity.EnclosureInfo;
import com.et.auditServer.modules.ed.service.EnclosureInfoService;
import org.springframework.stereotype.Service;

@Service
public class EnclosureInfoServiceImpl extends CrudService<EnclosureInfoDao, EnclosureInfo> implements EnclosureInfoService {
    /**
     * 上传附件
     * @return
     */
    @Override
    public JsonResult uploadAttachment() {




        return null;
    }

    /**
     * 查询附件
     * @return
     */
    @Override
    public JsonResult SelectAttachment() {




        return null;
    }



}
