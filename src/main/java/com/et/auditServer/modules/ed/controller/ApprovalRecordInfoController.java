package com.et.auditServer.modules.ed.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.ApprovalRecordInfoDTO;
import com.et.auditServer.modules.ed.service.ApprovalRecordInfoService;
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
    private ApprovalRecordInfoService approvalRecordInfoService;

    /**
     * 新增存证审批信息
     * */
    @PostMapping("approvalRecordInfo/certExamine")
    @ApiOperation(notes = "approvalRecordInfo/certExamine", value = "新增存证审批信息", httpMethod = "POST")
    public JsonResult certExamine(@ApiParam(name = "报文体", value = "存证审批对象",required = true) @RequestBody ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        return  approvalRecordInfoService.certExamine(approvalRecordInfoDTO);
    }

    /**
     * 新增确认单审批信息
     * */
    @PostMapping("approvalRecordInfo/confExamine")
    @ApiOperation(notes = "approvalRecordInfo/confExamine", value = "新增确认审批信息", httpMethod = "POST")
    public JsonResult confExamine(@ApiParam(name = "报文体", value = "确认审批对象",required = true) @RequestBody ApprovalRecordInfoDTO approvalRecordInfoDTO) {
        return  approvalRecordInfoService.certExamine(approvalRecordInfoDTO);
    }

    /**
     * 查询审批记录
     */
    @GetMapping("ApprovalRecordInfo/approvalRecordList")
    @ApiOperation(notes = "ApprovalRecordInfo/approvalRecordList", value = "项目列表", httpMethod = "GET")
    public JsonResult approvalRecordList(@ApiParam(value = "存证或确认单Id", name = "processId", required = true) @RequestParam int processId,
                                         @ApiParam(value = "存证或确认单标识", name = "category", required = true) @RequestParam String category
    ) {
        return approvalRecordInfoService.approvalRecordList(processId, category);
    }

}
