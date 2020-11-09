package com.et.auditServer.modules.ed.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.ed.dto.AddConfInfoDTO;
import com.et.auditServer.modules.ed.dto.ConfInfoDTO;
import com.et.auditServer.modules.ed.service.ConfInfoService;
import com.et.auditServer.modules.sys.controller.AbstractController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Api(tags = "确认单管理", description = "确认单管理接口")
public class ConfInfoController extends AbstractController {

    @Autowired
    private ConfInfoService confInfoService;
    /**
     * 确认单列表
     * */
    @GetMapping("confInfo/list")
    @ApiOperation(notes = "confInfo/list", value = "确认单列表", httpMethod = "GET")
    public JsonResult list(@ApiParam(value = "页码", name = "pageNo", required = true)@RequestParam int pageNo,
                           @ApiParam(value = "页数", name = "pageSize", required = true) @RequestParam int pageSize,
                           @ApiParam(name = "title", value = "标题") @RequestParam(required = false) String  title
                           ) {
        return confInfoService.list(pageNo, pageSize,title);
    }

    /**
     * 确认单信息
     * */
    @GetMapping("confInfo/findByConfId")
    @ApiOperation(notes = "confInfo/findByConfId", value = "确认单信息", httpMethod = "GET")
    public JsonResult findByProjId(@ApiParam(name = "confId", value = "确认单id", required = true) @RequestParam int confId) {
        return confInfoService.findByConfId(confId);
    }

    /**
     * 新增确认单信息
     * */
    @PostMapping("confInfo/add")
    @ApiOperation(notes = "confInfo/add", value = "新增确认单信息", httpMethod = "POST")
    public JsonResult insert(@ApiParam(name = "报文体", value = "确认单对象",required = true) @RequestBody AddConfInfoDTO addConfInfoDTO) {
        return confInfoService.insert(addConfInfoDTO);
    }


    /**
     * 保存确认单信息
     * */
    @PutMapping("confInfo/update")
    @ApiOperation(notes = "confInfo/update", value = "保存确认单信息", httpMethod = "PUT")
    public JsonResult update(@ApiParam(name = "报文体", value = "确认单对象",required = true) @RequestBody ConfInfoDTO confInfoDTO) {
        return confInfoService.update(confInfoDTO);
    }

    /**
     * 删除确认单信息
     * */
    @DeleteMapping("confInfo/deleteConfInfo")
    @ApiOperation(notes = "confInfo/deleteConfInfo", value = "删除确认单信息", httpMethod = "DELETE")
    public JsonResult deleteProjInfo(@ApiParam(name = "confId", value = "确认单id", required = true) @RequestParam int confId) {
        return confInfoService.delConfInfo(confId);
    }


}
