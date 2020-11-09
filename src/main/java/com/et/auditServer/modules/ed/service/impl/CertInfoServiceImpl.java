package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.CertInfoDao;
import com.et.auditServer.modules.ed.dto.AddCertInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.entity.CertInfo;
import com.et.auditServer.modules.ed.service.CertInfoService;
import com.et.auditServer.modules.sys.cache.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CertInfoServiceImpl extends CrudService<CertInfoDao, CertInfo> implements CertInfoService {

    @Autowired
    private UserUtils userUtils;
    /**
     * 分页查询
     */
    @Override
    public JsonResult list(int pageNo, int pageSize, String title) {
        PageHelper.startPage(pageNo, pageSize);
        List<CertInfoDTO> list = null;
        if (userUtils.checkAllDataUser(loginCode())) {
            list = dao.findListPage(Constant.ALL_DATA_USER, title);
        } else {
            list = dao.findListPage(loginCode(), title);
        }
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }
    /**
     * 根据id查询
     */
    @Override
    public JsonResult findByCertId(int certId) {
        CertInfo certInfo = dao.selectById(certId);
        return JsonResult.success(certInfo);
    }
    /**
     * 添加
     * @return
     * @param addCertInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult insert(AddCertInfoDTO addCertInfoDTO) {
        dao.insert(addCertInfoDTO);
        CertInfo certInfo = new CertInfo();
        certInfo.setCertId(addCertInfoDTO.getCertId());
        logger.info("新增存证信息成功");
        return JsonResult.success(certInfo);
    }
    /**
     * 修改
     * @return
     * @param certInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult update(CertInfoDTO certInfoDTO) {
        dao.update(certInfoDTO);
        logger.info("更新存证信息成功");
        return JsonResult.success(certInfoDTO);
    }
    /**
     * 根据id删除
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult delCertInfo(int certId) {
        dao.deleteById(certId);
        CertInfo certInfo = new CertInfo();
        certInfo.setCertId(certId);
        logger.info("删除存证信息成功");
        return JsonResult.success(certInfo);
    }
}
