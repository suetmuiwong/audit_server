package com.et.auditServer.modules.ed.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddHashInfoDTO;
import com.et.auditServer.modules.ed.dto.HashInfoDTO;
import com.et.auditServer.modules.ed.service.HashInfoService;
import com.et.auditServer.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "哈希值管理", description = "哈希值管理接口")
public class HashInfoController extends AbstractController {

    @Autowired
    private HashInfoService hashInfoService;
    /**
     * 哈希值列表
     * */
    @GetMapping("hashInfo/list")
    @ApiOperation(notes = "hashInfo/list", value = "哈希值列表", httpMethod = "GET")
    public JsonResult list(@ApiParam(value = "页码", name = "pageNo", required = true)@RequestParam int pageNo,
                           @ApiParam(value = "页数", name = "pageSize", required = true) @RequestParam int pageSize,
                           @ApiParam(name = "hashName", value = "哈希值名称") @RequestParam(required = false) String hashName
                           ) {
        return hashInfoService.list(pageNo, pageSize,hashName);
    }

    /**
     * 哈希值信息
     * */
    @GetMapping("hashInfo/findByProjId")
    @ApiOperation(notes = "hashInfo/findByProjId", value = "哈希值信息", httpMethod = "GET")
    public JsonResult findByHashId(@ApiParam(name = "hashId", value = "哈希值id", required = true) @RequestParam int hashId) {
        return hashInfoService.findByHashId(hashId);
    }

    /**
     * 新增哈希值信息
     * */
    @PostMapping("hashInfo/add")
    @ApiOperation(notes = "hashInfo/add", value = "新增哈希值信息", httpMethod = "POST")
    public JsonResult insert(@ApiParam(name = "报文体", value = "哈希值对象",required = true) @RequestBody AddHashInfoDTO addHashInfoDTO) {
        return hashInfoService.insert(addHashInfoDTO);
    }


    /**
     * 保存哈希值信息
     * */
    @PutMapping("hashInfo/update")
    @ApiOperation(notes = "hashInfo/update", value = "保存哈希值信息", httpMethod = "PUT")
    public JsonResult update(@ApiParam(name = "报文体", value = "哈希值对象",required = true) @RequestBody HashInfoDTO hashInfoDTO) {
        return hashInfoService.update(hashInfoDTO);
    }

    /**
     * 删除哈希值信息
     * */
    @DeleteMapping("hashInfo/deleteProjInfo")
    @ApiOperation(notes = "hashInfo/deleteProjInfo", value = "删除哈希值信息", httpMethod = "DELETE")
    public JsonResult deleteHashInfo(@ApiParam(name = "hashId", value = "哈希值id", required = true) @RequestParam int hashId) {
        return hashInfoService.delHashInfo(hashId);
    }


}
