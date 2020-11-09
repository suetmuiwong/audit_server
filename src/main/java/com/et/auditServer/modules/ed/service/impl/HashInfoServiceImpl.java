package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.HashInfoDao;
import com.et.auditServer.modules.ed.dto.AddHashInfoDTO;
import com.et.auditServer.modules.ed.dto.HashInfoDTO;
import com.et.auditServer.modules.ed.entity.HashInfo;
import com.et.auditServer.modules.ed.service.HashInfoService;
import com.et.auditServer.modules.sys.cache.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HashInfoServiceImpl extends CrudService<HashInfoDao, HashInfo> implements HashInfoService {

    @Autowired
    private UserUtils userUtils;

    /**
     * 分页查询
     */
    @Override
    public JsonResult list(int pageNo, int pageSize, String hashName) {
        PageHelper.startPage(pageNo, pageSize);
        List<HashInfoDao> list = null;
        if (userUtils.checkAllDataUser(loginCode())) {
            list = dao.findListPage(Constant.ALL_DATA_USER, hashName);
        } else {
            list = dao.findListPage(loginCode(), hashName);
        }
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }

    /**
     * 根据id查询
     */
    @Override
    public JsonResult findByHashId(int hashId) {
        HashInfo hashInfo = dao.selectById(hashId);
        return JsonResult.success(hashInfo);
    }

    /**
     * 添加
     * @return
     * @param addHashInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult insert(AddHashInfoDTO addHashInfoDTO) {
        dao.insert(addHashInfoDTO);
        HashInfo hashInfo = new HashInfo();
        hashInfo.setHashId(addHashInfoDTO.getHashId());
        logger.info("新增信息成功");
        return JsonResult.success(hashInfo);
    }

    /**
     * 修改
     * @return
     * @param hashInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult update(HashInfoDTO hashInfoDTO) {
        dao.update(hashInfoDTO);
        logger.info("更新信息成功");
        return JsonResult.success(hashInfoDTO);
    }

    /**
     * 根据id删除
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult delHashInfo(int hashId) {
         dao.deleteById(hashId);
        HashInfo hashInfo = new HashInfo();
        hashInfo.setHashId(hashId);
        logger.info("删除信息成功");
        return JsonResult.success(hashId);
    }



}
