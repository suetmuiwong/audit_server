package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.*;
import com.et.auditServer.modules.ed.dao.ProjInfoDao;
import com.et.auditServer.modules.ed.dto.AddProjInfoDTO;
import com.et.auditServer.modules.ed.dto.ProjInfoDTO;
import com.et.auditServer.modules.ed.entity.ProjInfo;

import com.et.auditServer.modules.sys.cache.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.et.auditServer.modules.ed.service.ProjInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ProjInfoServiceImpl extends CrudService<ProjInfoDao, ProjInfo> implements ProjInfoService {

    @Autowired
    private UserUtils userUtils;

    /**
     * 分页查询
     */
    @Override
    public JsonResult list(int pageNo, int pageSize, String projName) {
        PageHelper.startPage(pageNo, pageSize);
        List<ProjInfoDTO> list = null;
        if (userUtils.checkAllDataUser(loginCode())) {
            list = dao.findListPage(Constant.ALL_DATA_USER, projName);
        } else {
            list = dao.findListPage(loginCode(), projName);
        }
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }

    /**
     * 根据id查询
     */
    @Override
    public JsonResult findByProjId(int projId) {
        ProjInfo projInfo = dao.selectById(projId);
        return JsonResult.success(projInfo);
    }

    /**
     * 添加
     * @return
     * @param addProjInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult insert(AddProjInfoDTO addProjInfoDTO) {
        dao.insert(addProjInfoDTO);
        ProjInfo projInfo = new ProjInfo();
        projInfo.setProjId(addProjInfoDTO.getProjId());
        logger.info("新增信息成功");
        return JsonResult.success(projInfo);
    }

    /**
     * 修改
     * @return
     * @param projInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult update(ProjInfoDTO projInfoDTO) {
        dao.update(projInfoDTO);
        logger.info("更新信息成功");
        return JsonResult.success(projInfoDTO);
    }

    /**
     * 根据id删除
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult delProjInfo(int projId) {
         dao.deleteById(projId);
        ProjInfo projInfo = new ProjInfo();
        projInfo.setProjId(projId);
        logger.info("删除信息成功");
        return JsonResult.success(projInfo);
    }



}
