package com.et.auditServer.modules.ed.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.service.ApprovalRecordService;
import com.et.auditServer.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "审批管理", description = "审批管理接口")
public class ApprovalRecordInfoController extends AbstractController {

    @Autowired
    private ApprovalRecordService approvalRecordService;

    /**
     * 存证信息审批
     */
    @GetMapping("ApprovalRecordInfo/certExamine")
    @ApiOperation(notes = "certInfo/findByCertId", value = "存证信息审批", httpMethod = "POST")
    public JsonResult certExamine(@ApiParam(name = "报文体", value = "审批记录对象",required = true) @RequestParam ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        return  approvalRecordService.certExamine(approvalRecordInfoDTO);
    }

    /**
     * 确认单审批
     */
    @GetMapping("ApprovalRecordInfo/certExamine")
    @ApiOperation(notes = "certInfo/findByCertId", value = "存证信息审批", httpMethod = "POST")
    public JsonResult confExamine(@ApiParam(name = "报文体", value = "审批记录对象",required = true) @RequestParam ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        return  approvalRecordService.confExamine(approvalRecordInfoDTO);
    }

    /**
     * 查询审批记录
     */
    @GetMapping("certInfo/list")
    @ApiOperation(notes = "certInfo/list", value = "项目列表", httpMethod = "GET")
    public JsonResult approvalRecordList(@ApiParam(value = "存证或确认单Id", name = "processId", required = true)@RequestParam int processId,
                           @ApiParam(value = "存证或确认单标识", name = "category", required = true) @RequestParam String category
    ) {
        return approvalRecordService.approvalRecordList(processId, category);
    }








}
