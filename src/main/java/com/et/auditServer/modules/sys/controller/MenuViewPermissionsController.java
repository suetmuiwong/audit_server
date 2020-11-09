package com.et.auditServer.modules.sys.controller;


import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.sys.service.MenuViewPermissionsService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RestController
@Api(tags = "权限管理", description = "权限管理接口")
public class MenuViewPermissionsController extends AbstractController {
    @Autowired
    private MenuViewPermissionsService menuViewPermissionsService;

    @GetMapping("menuViewPermissions/getByMenuCode")
    @ApiOperation(notes = "menuViewPermissions/getByMenuCode", value = "根据menuCode获取", httpMethod = "GET")
    public JsonResult getByMenuCode(@ApiParam(value = "菜单code", name = "menuCode" ) @RequestParam(required = true) String menuCode) {
        return JsonResult.success(menuViewPermissionsService.getByMenuCode(menuCode));
    }

}
