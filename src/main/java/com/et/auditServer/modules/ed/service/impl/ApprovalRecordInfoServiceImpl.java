package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.ApprovalRecordInfoDao;
import com.et.auditServer.modules.ed.dto.ApprovalNodeInfoDTO;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.dto.ConfInfoDTO;
import com.et.auditServer.modules.ed.entity.ApprovalNodeInfo;
import com.et.auditServer.modules.ed.entity.ApprovalRecordInfo;
import com.et.auditServer.modules.ed.entity.CertInfo;
import com.et.auditServer.modules.ed.entity.ConfInfo;
import com.et.auditServer.modules.ed.service.ApprovalNodeInfoService;
import com.et.auditServer.modules.ed.service.ApprovalRecordInfoService;
import com.et.auditServer.modules.ed.service.CertInfoService;
import com.et.auditServer.modules.ed.service.ConfInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ApprovalRecordInfoServiceImpl extends CrudService<ApprovalRecordInfoDao, ApprovalRecordInfo> implements ApprovalRecordInfoService {

    @Autowired
    private CertInfoService certInfoService;
    @Autowired
    private ConfInfoService confInfoService;
    @Autowired
    private ApprovalNodeInfoService approvalNodeInfoService;

    @Override
    public int insert(ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        return dao.insert(approvalRecordInfoDTO);
    }

    @Override
    public JsonResult delCertInfo(int processId, String category) {
        dao.deleteById(processId,category);
        ApprovalRecordInfo approvalRecordInfo = new ApprovalRecordInfo();
        approvalRecordInfo.setProcessId(processId);
        logger.info("删除审批信息成功");
        return JsonResult.success(approvalRecordInfo);
    }

    @Override
    public JsonResult approvalRecordList(int processId, String category) {
        List<ApprovalRecordInfoDTO> approvalList = dao.selectApprovalRecordList(processId,category);
        return JsonResult.success(approvalList);
    }

    @Override
    public JsonResult certExamine(ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        //记录执行的节点
        ApprovalNodeInfoDTO approvalNodeInfoDTO = new ApprovalNodeInfoDTO();
        approvalNodeInfoDTO.setCategory("Cert");
        //修改存证单的审批信息
        CertInfoDTO certInfoDTO = new CertInfoDTO();
        certInfoDTO.setCertId(approvalRecordInfoDTO.getProcessId());
        //查询审批存证信息
        CertInfo certInfo  = certInfoService.certInfo(approvalRecordInfoDTO.getProcessId());
        //记录审批历史
        ApprovalRecordInfo approvalRecordInfoCert = new ApprovalRecordInfo();
        approvalRecordInfoCert.setStayExecutor(approvalRecordInfoDTO.getStayExecutor());

        //同意
        if(approvalRecordInfoDTO.getStatus() == 1)
        {
            if(approvalRecordInfoDTO.getExecutor() == certInfo.getCreater())
            {
                //执行人为项目负责人时流程执行完成
                certInfoDTO.setStatus(2);
                certInfoService.update(certInfoDTO);
            }else{
                //如果执行人点击同意则设置带执行人为项目负责人
                approvalRecordInfoCert.setStayExecutor(certInfo.getCreated());
                //修改状态为执行中并且设置下个执行人为项目负责人
                certInfoDTO.setStatus(1);
                certInfoDTO.setExecutor(certInfo.getCreated());
                certInfoDTO.setNode(certInfo.getNode()+1);
                certInfoService.update(certInfoDTO);
                //记录执行节点
                approvalNodeInfoDTO.setProcessId(approvalRecordInfoDTO.getProcessId());
                approvalNodeInfoDTO.setUpNode(approvalRecordInfoDTO.getExecutor());
                approvalNodeInfoDTO.setNextNode(certInfo.getCreater());
                approvalNodeInfoDTO.setNode(certInfo.getNode());
                approvalNodeInfoService.insert(approvalNodeInfoDTO);
            }
        }
        //流程结束
        if(approvalRecordInfoDTO.getStatus() == 4)
        {
            certInfoDTO.setStatus(2);
            certInfoService.update(certInfoDTO);
        }
        //拒绝
        if(approvalRecordInfoDTO.getStatus() == 2)
        {
            //查询上级审批
            ApprovalNodeInfo approvalNodeInfo = approvalNodeInfoService.approvalNodeInfo(certInfo.getCertId(),certInfo.getNode()-1,"Cert");
            //修改状态为退回
            certInfoDTO.setStatus(3);
            certInfoDTO.setExecutor(approvalNodeInfo.getUpNode());
            certInfoDTO.setNode(certInfo.getNode()-1);
            //执行者拒绝是设置上级执行人
            approvalRecordInfoCert.setStayExecutor(approvalNodeInfo.getUpNode());
            //删除退回节点
            approvalNodeInfoService.deleteById(certInfo.getCertId(),certInfo.getNode()-1,"Cert");
        }
        //扭转
        if(approvalRecordInfoDTO.getStatus() == 3 && approvalRecordInfoDTO.getStayExecutor() != null)
        {
            //修改状态为执行中
            certInfoDTO.setStatus(1);
            certInfoDTO.setExecutor(approvalRecordInfoDTO.getStayExecutor());
            certInfoDTO.setNode(certInfo.getNode()+1);
            certInfoService.update(certInfoDTO);
            //创建节点记录
            approvalNodeInfoDTO.setProcessId(approvalRecordInfoDTO.getProcessId());
            approvalNodeInfoDTO.setUpNode(approvalRecordInfoDTO.getExecutor());
            approvalNodeInfoDTO.setNextNode(approvalRecordInfoDTO.getStayExecutor());
            approvalNodeInfoDTO.setNode(certInfo.getNode());
            approvalNodeInfoService.insert(approvalNodeInfoDTO);

        }
        //设置存证需求单审批记录
        approvalRecordInfoCert.setProcessId(approvalRecordInfoDTO.getProcessId());
        approvalRecordInfoCert.setExecutor(approvalRecordInfoDTO.getExecutor());
        approvalRecordInfoCert.setStatus(approvalRecordInfoDTO.getStatus());
        approvalRecordInfoCert.setApprovalOpinion(approvalRecordInfoDTO.getApprovalOpinion());
        approvalRecordInfoCert.setAttachment(approvalRecordInfoDTO.getAttachment());
        approvalRecordInfoCert.setApprovalDate(new Date());
        approvalRecordInfoCert.setCategory("Cert");
        approvalRecordInfoCert.setCreater(approvalRecordInfoDTO.getCreater());
        approvalRecordInfoCert.setCreateTime(new Date());
        dao.insert(approvalRecordInfoCert);

        logger.info("审批成功");
        return JsonResult.success();
    }

    @Override
    public JsonResult confExamine(ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        //记录执行的节点
        ApprovalNodeInfoDTO approvalNodeInfoDTO = new ApprovalNodeInfoDTO();
        approvalNodeInfoDTO.setCategory("Conf");
        //修改存证单的审批信息
        ConfInfoDTO confInfoDTO = new ConfInfoDTO();
        confInfoDTO.setConfId(approvalRecordInfoDTO.getProcessId());
        //查询审批存证信息
        ConfInfo confInfo  = confInfoService.confInfo(approvalRecordInfoDTO.getProcessId());
        //记录审批历史
        ApprovalRecordInfo approvalRecordInfoConf = new ApprovalRecordInfo();

        //同意
        if(approvalRecordInfoDTO.getStatus() == 1)
        {
            if(confInfo.getExecutor() == confInfo.getManagerLeader())
            {
                //被审计单位业务负责人领导审核同意后流程结束
                confInfoDTO.setStatus(2);
                confInfoService.update(confInfoDTO);
            }else{
                //如果执行人点击同意则设置带执行人为被审计单位业务负责人领导
                approvalRecordInfoConf.setStayExecutor(confInfo.getManagerLeader());
                //设置确认单执行者为被审计单位业务负责人领导
                confInfoDTO.setStatus(1);
                confInfoDTO.setExecutor(confInfo.getManagerLeader());
                confInfoDTO.setNode(confInfo.getNode()+1);
                confInfoService.update(confInfoDTO);
                //记录执行节点
                approvalNodeInfoDTO.setProcessId(approvalRecordInfoDTO.getProcessId());
                approvalNodeInfoDTO.setUpNode(approvalRecordInfoDTO.getExecutor());
                approvalNodeInfoDTO.setNextNode(confInfo.getManagerLeader());
                approvalNodeInfoDTO.setNode(confInfo.getNode());
                approvalNodeInfoService.insert(approvalNodeInfoDTO);

            }
        }
        //流程结束
        if(approvalRecordInfoDTO.getStatus() == 2)
        {
            confInfoDTO.setStatus(2);
            confInfoService.update(confInfoDTO);
        }

        //拒绝
        if(approvalRecordInfoDTO.getStatus() == 3)
        {
            //查询上级审批
            ApprovalNodeInfo approvalNodeInfo = approvalNodeInfoService.approvalNodeInfo(confInfo.getConfId(),confInfo.getNode()-1,"Conf");
            //修改状态为退回
            confInfoDTO.setStatus(3);
            confInfoDTO.setExecutor(approvalNodeInfo.getUpNode());
            confInfoDTO.setNode(confInfo.getNode()-1);
            //执行者拒绝是设置上级执行人
            approvalRecordInfoConf.setStayExecutor(approvalNodeInfo.getUpNode());
            //删除退回节点
            approvalNodeInfoService.deleteById(confInfo.getConfId(),confInfo.getNode()-1,"Conf");
        }

        //新增存证需求单审批记录
        approvalRecordInfoConf.setProcessId(approvalRecordInfoDTO.getProcessId());
        approvalRecordInfoConf.setExecutor(approvalRecordInfoDTO.getExecutor());
        approvalRecordInfoConf.setStatus(approvalRecordInfoDTO.getStatus());
        approvalRecordInfoConf.setApprovalOpinion(approvalRecordInfoDTO.getApprovalOpinion());
        approvalRecordInfoConf.setAttachment(approvalRecordInfoDTO.getAttachment());
        approvalRecordInfoConf.setApprovalDate(approvalRecordInfoDTO.getApprovalDate());
        approvalRecordInfoConf.setCategory("Conf");
        approvalRecordInfoConf.setCreater(approvalRecordInfoDTO.getExecutor());
        approvalRecordInfoConf.setCreateTime(new Date());
        dao.insert(approvalRecordInfoConf);

        logger.info("审批成功");
        return JsonResult.success();
    }
}
