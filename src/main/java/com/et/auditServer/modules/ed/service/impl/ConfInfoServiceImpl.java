package com.et.auditServer.modules.ed.service.impl;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.service.CrudService;
import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dao.ConfInfoDao;
import com.et.auditServer.modules.ed.dto.*;
import com.et.auditServer.modules.ed.entity.ConfInfo;
import com.et.auditServer.modules.ed.service.ApprovalNodeService;
import com.et.auditServer.modules.ed.service.ApprovalRecordService;
import com.et.auditServer.modules.ed.service.ConfInfoService;
import com.et.auditServer.modules.sys.cache.UserUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class ConfInfoServiceImpl extends CrudService<ConfInfoDao, ConfInfo> implements ConfInfoService {

    @Autowired
    private UserUtils userUtils;
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
        List<ConfInfoDao> list = null;
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
    public JsonResult findByConfId(int projId) {
        ConfInfo proj = dao.selectById(projId);
        return JsonResult.success(proj);
    }
    /**
     * 添加
     * @return
     * @param addConfInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult insert(AddConfInfoDTO addConfInfoDTO) {
        //设置执行节点
        addConfInfoDTO.setNode(1);
        addConfInfoDTO.setExecutor(addConfInfoDTO.getManager());
        int confId = dao.insert(addConfInfoDTO);

        //新增确认单审批记录
        ApprovalRecordInfoDTO approvalRecordInfoDTO = new ApprovalRecordInfoDTO();
        approvalRecordInfoDTO.setProcessId(confId);
        approvalRecordInfoDTO.setExecutor(addConfInfoDTO.getCreater());
        approvalRecordInfoDTO.setStayExecutor(addConfInfoDTO.getManager());
        approvalRecordInfoDTO.setStatus(0);
        approvalRecordInfoDTO.setApprovalOpinion("发起人");
        approvalRecordInfoDTO.setApprovalDate(new Date());
        approvalRecordInfoDTO.setCategory("Conf");
        approvalRecordInfoDTO.setCreater(addConfInfoDTO.getCreater());
        approvalRecordInfoDTO.setCreatedDate(new Date());
        approvalRecordService.insert(approvalRecordInfoDTO);

        //添加审批节点
        ApprovalNodeDTO approvalNodeDTO = new ApprovalNodeDTO();
        approvalNodeDTO.setProcessId(confId);
        approvalNodeDTO.setUpNode(addConfInfoDTO.getCreater());
        approvalNodeDTO.setNextNode(addConfInfoDTO.getManager());
        approvalNodeDTO.setNode(0);
        approvalNodeDTO.setCategory("Conf");
        approvalNodeService.insert(approvalNodeDTO);


        ConfInfo confInfo = new ConfInfo();
        confInfo.setConfId(addConfInfoDTO.getConfId());
        logger.info("新增信息成功");
        return JsonResult.success(confInfo);
    }


    /**
     * 修改
     * @return
     * @param confInfoDTO
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult update(ConfInfoDTO confInfoDTO) {
        dao.update(confInfoDTO);
        logger.info("更新信息成功");
        return JsonResult.success(confInfoDTO);
    }


    /**
     * 根据id删除
     */
    @Override
    @Transactional(readOnly = false)
    public JsonResult delConfInfo(int confId) {
         dao.deleteById(confId);
        ConfInfo confInfo = new ConfInfo();
        confInfo.setConfId(confId);
        logger.info("删除信息成功");
        return JsonResult.success(confInfo);
    }

    /**
     * 根据项目ID查询确认单信息
     */
    public  JsonResult confList(int pageNo, int pageSize,int projId){
        PageHelper.startPage(pageNo, pageSize);
        List<ConfInfoDao> list = dao.ConfInfoList(projId);
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }

    /**
     * 根据执行者查询确认单信息
     */
    @Override
    public JsonResult confList(int pageNo, int pageSize, String executor) {
        PageHelper.startPage(pageNo, pageSize);
        List<ConfInfoDao> list = dao.executorConfInfoList(executor);
        PageInfo pageInfo = new PageInfo(list);
        return JsonResult.success(pageInfo);
    }

    @Override
    public ConfInfo confInfo(int confId) {
        return dao.selectById(confId);
    }


}
