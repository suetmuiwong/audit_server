package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.CertInfoDao;
import com.et.auditServer.modules.ed.dto.AddCertInfoDTO;
import com.et.auditServer.modules.ed.dto.ApprovalNodeInfoDTO;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.entity.CertInfo;
import com.et.auditServer.modules.ed.service.ApprovalNodeInfoService;
import com.et.auditServer.modules.ed.service.ApprovalRecordInfoService;
import com.et.auditServer.modules.ed.service.CertInfoService;
import com.et.auditServer.modules.sys.cache.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class CertInfoServiceImpl extends CrudService<CertInfoDao, CertInfo> implements CertInfoService {

    @Autowired
    private UserUtils userUtils;
    @Autowired
    private ApprovalRecordInfoService approvalRecordInfoService;
    @Autowired
    private ApprovalNodeInfoService approvalNodeInfoService;
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
        //设置执行节点
        addCertInfoDTO.setNode(1);
        int certId = dao.insert(addCertInfoDTO);

        //新增审批记录
        ApprovalRecordInfoDTO approvalRecordInfoDTO = new ApprovalRecordInfoDTO();
        approvalRecordInfoDTO.setProcessId(certId);
        approvalRecordInfoDTO.setExecutor(addCertInfoDTO.getCreater());
        approvalRecordInfoDTO.setStayExecutor(addCertInfoDTO.getExecutor());
        approvalRecordInfoDTO.setStatus(0);
        approvalRecordInfoDTO.setApprovalOpinion("发起人");
        approvalRecordInfoDTO.setApprovalDate(new Date());
        approvalRecordInfoDTO.setCategory("Cert");
        approvalRecordInfoDTO.setCreater(addCertInfoDTO.getCreater());
        approvalRecordInfoDTO.setCreateTime(new Date());
        approvalRecordInfoService.insert(approvalRecordInfoDTO);

        //新增节点
        ApprovalNodeInfoDTO approvalNodeInfoDTO = new ApprovalNodeInfoDTO();
        approvalNodeInfoDTO.setProcessId(certId);
        approvalNodeInfoDTO.setUpNode(addCertInfoDTO.getCreater());
        approvalNodeInfoDTO.setNextNode(addCertInfoDTO.getExecutor());
        approvalNodeInfoDTO.setNode(0);
        approvalNodeInfoDTO.setCategory("Cert");
        approvalNodeInfoService.insert(approvalNodeInfoDTO);

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

    @Override
    public CertInfo certInfo(int CertId) {
        return dao.selectById(CertId);
    }
}
