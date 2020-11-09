package com.et.auditServer.modules.ed.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddCertInfoDTO;
import com.et.auditServer.modules.ed.dto.CertInfoDTO;
import com.et.auditServer.modules.ed.service.CertInfoService;
import com.et.auditServer.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "存证管理", description = "存证管理接口")
public class CertInfoController extends AbstractController {

    @Autowired
    private CertInfoService certInfoService;
    /**
     * 项目列表
     * */
    @GetMapping("certInfo/list")
    @ApiOperation(notes = "certInfo/list", value = "项目列表", httpMethod = "GET")
    public JsonResult list(@ApiParam(value = "页码", name = "pageNo", required = true)@RequestParam int pageNo,
                           @ApiParam(value = "页数", name = "pageSize", required = true) @RequestParam int pageSize,
                           @ApiParam(name = "title", value = "标题") @RequestParam(required = false) String title
                           ) {
        return certInfoService.list(pageNo, pageSize,title);
    }

    /**
     * 存证信息
     * */
    @GetMapping("certInfo/findByCertId")
    @ApiOperation(notes = "certInfo/findByCertId", value = "存证信息", httpMethod = "GET")
    public JsonResult findByProjId(@ApiParam(name = "certId", value = "存证id", required = true) @RequestParam int certId) {
        return  certInfoService.findByCertId(certId);
    }

    /**
     * 新增存证信息
     * */
    @PostMapping("certInfo/add")
    @ApiOperation(notes = "certInfo/add", value = "新增存证信息", httpMethod = "POST")
    public JsonResult insert(@ApiParam(name = "报文体", value = "存证对象",required = true) @RequestBody AddCertInfoDTO addCertInfoDTO) {
        return  certInfoService.insert(addCertInfoDTO);
    }


    /**
     * 保存存证信息
     * */
    @PutMapping("certInfo/update")
    @ApiOperation(notes = "certInfo/update", value = "保存存证信息", httpMethod = "PUT")
    public JsonResult update(@ApiParam(name = "报文体", value = "存证对象",required = true) @RequestBody CertInfoDTO certInfoDTO) {
        return certInfoService.update(certInfoDTO);
    }

    /**
     * 删除存证信息
     * */
    @DeleteMapping("certInfo/deleteCertInfo")
    @ApiOperation(notes = "certInfo/deleteCertInfo", value = "删除存证信息", httpMethod = "DELETE")
    public JsonResult deleteProjInfo(@ApiParam(name = "certId", value = "存证id", required = true) @RequestParam int certId) {
        return certInfoService.delCertInfo(certId);
    }


}
