package com.et.auditServer.modules.ed.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddProjInfoDTO;
import com.et.auditServer.modules.ed.dto.ProjInfoDTO;
import com.et.auditServer.modules.sys.controller.AbstractController;
import com.et.auditServer.modules.ed.service.ProjInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "项目管理", description = "项目管理接口")
public class ProjInfoController extends AbstractController {

    @Autowired
    private ProjInfoService projInfoService;
    /**
     * 项目列表
     * */
    @GetMapping("projInfo/list")
    @ApiOperation(notes = "projInfo/list", value = "项目列表", httpMethod = "GET")
    public JsonResult list(@ApiParam(value = "页码", name = "pageNo", required = true)@RequestParam int pageNo,
                           @ApiParam(value = "页数", name = "pageSize", required = true) @RequestParam int pageSize,
                           @ApiParam(name = "projName", value = "项目名称") @RequestParam(required = false) String projName
                           ) {
        return projInfoService.list(pageNo, pageSize,projName);
    }

    /**
     * 项目信息
     * */
    @GetMapping("projInfo/findByProjId")
    @ApiOperation(notes = "projInfo/findByProjId", value = "项目信息", httpMethod = "GET")
    public JsonResult findByProjId(@ApiParam(name = "projId", value = "项目id", required = true) @RequestParam int projId) {
        return projInfoService.findByProjId(projId);
    }

    /**
     * 新增项目信息
     * */
    @PostMapping("projInfo/add")
    @ApiOperation(notes = "projInfo/add", value = "新增项目信息", httpMethod = "POST")
    public JsonResult insert(@ApiParam(name = "报文体", value = "项目对象",required = true) @RequestBody AddProjInfoDTO addProjInfoDTO) {
        return projInfoService.insert(addProjInfoDTO);
    }


    /**
     * 保存项目信息
     * */
    @PutMapping("projInfo/update")
    @ApiOperation(notes = "projInfo/update", value = "保存项目信息", httpMethod = "PUT")
    public JsonResult update(@ApiParam(name = "报文体", value = "项目对象",required = true) @RequestBody ProjInfoDTO projInfoDTO) {
        return projInfoService.update(projInfoDTO);
    }

    /**
     * 删除项目信息
     * */
    @DeleteMapping("projInfo/deleteProjInfo")
    @ApiOperation(notes = "projInfo/deleteProjInfo", value = "删除项目信息", httpMethod = "DELETE")
    public JsonResult deleteProjInfo(@ApiParam(name = "projId", value = "项目id", required = true) @RequestParam int projId) {
        return projInfoService.delProjInfo(projId);
    }


}
