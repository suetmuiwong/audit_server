package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.CertInfoDao;
import com.et.auditServer.modules.ed.dto.AddCertInfoDTO;
import com.et.auditServer.modules.ed.dto.ApprovalNodeDTO;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.entity.CertInfo;
import com.et.auditServer.modules.ed.service.ApprovalNodeService;
import com.et.auditServer.modules.ed.service.ApprovalRecordService;
import com.et.auditServer.modules.ed.service.CertInfoService;
import com.et.auditServer.modules.sys.cache.UserUtils;
import com.et.auditServer.modules.sys.entity.User;
import com.et.auditServer.modules.sys.service.UserService;
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
    private UserService userService;
    @Autowired
    private ApprovalRecordService approvalRecordService;
    @Autowired
    private ApprovalNodeService approvalNodeService;

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
        approvalRecordService.insert(approvalRecordInfoDTO);

        //新增节点
        ApprovalNodeDTO approvalNodeDTO = new ApprovalNodeDTO();
        approvalNodeDTO.setProcessId(certId);
        approvalNodeDTO.setUpNode(addCertInfoDTO.getCreater());
        approvalNodeDTO.setNextNode(addCertInfoDTO.getExecutor());
        approvalNodeDTO.setNode(0);
        approvalNodeDTO.setCategory("Cert");
        approvalNodeService.insert(approvalNodeDTO);

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
    /**
     * 根据项目Id查出相关存证需求信息
     */
    public  JsonResult certList(int pageNo, int pageSize,int projId){
        PageHelper.startPage(pageNo, pageSize);
        List<CertInfoDTO> list = dao.CertInfoList(projId);
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }
    /**
     * 根据用户查询相关审批信息
     */
    @Override
    public JsonResult certList(int pageNo, int pageSize, String executor) {
        PageHelper.startPage(pageNo, pageSize);
        List<CertInfoDTO> list = dao.executorCertInfoList(executor);
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }

    @Override
    public CertInfo certInfo(int CertId) {
        return dao.selectById(CertId);
    }
}
