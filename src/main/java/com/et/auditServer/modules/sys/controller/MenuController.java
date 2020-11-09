package com.et.auditServer.modules.sys.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.modules.sys.entity.Menu;
import com.et.auditServer.modules.sys.entity.MenuTreeSort;
import com.et.auditServer.modules.sys.service.MenuService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Api(tags = "菜单管理", description = "菜单管理接口")
public class MenuController extends AbstractController {
    @Autowired
    private MenuService menuService;
    /**
     * 获取菜单树
     * @author qgping
     * @date 2019-03-05 10:46
     * @param menuName
     * @return
     * @throws
     * @since
    */
    //@MySysLog(value = "获取菜单树", type = "2")
    @GetMapping("menu/menuTree")
    @ApiOperation(notes = "menu/menuTree", value = "获取菜单树", httpMethod = "GET")
    public JsonResult menuTree(@ApiParam(value = "菜单名称", name = "menuName" ) @RequestParam(required = false) String menuName) {
        return JsonResult.success(menuService.menuTree(menuName));
    }

    /**
     * 保存菜单
     * @author qgping
     * @date 2019-03-05 10:47
     * @param menu
     * @return
     * @throws
     * @since
    */
    //@MySysLog(value = "修改菜单树", type = "1")
    @PutMapping("menu/save")
    @ApiOperation(notes = "menu/save", value = "编辑保存菜单", httpMethod = "PUT")
    public JsonResult save(@ApiParam(name = "报文体", value = "菜单对象")@RequestBody Menu menu) {
        return menuService.saveMenu(menu);
    }

    /**
     * 修改排序
     * */
    //@MySysLog(value = "修改菜单排序", type = "1")
    @PutMapping("menu/updateTreeSort")
    @ApiOperation(notes = "menu/updateTreeSort", value = "修改排序", httpMethod = "PUT")
    public JsonResult updateTreeSort(@ApiParam(value = "菜单排序修改集合", name = "menuTreeSorts")@RequestBody List<MenuTreeSort> menuTreeSorts) {
        return menuService.updateTreeSort(menuTreeSorts);
    }

//    /**
//     * 获取当前登录人拥有菜单
//     * */
//    @GetMapping("menu/getUserMenuTree")
//    @ApiOperation(notes = "menu/getUserMenuTree", value = "获取当前登录人拥有的菜单", httpMethod = "GET")
//    public JsonResult getUserMenuTree(){
//        return JsonResult.success(menuService.getUserMenuTree());
//    }

    /**
     * 根据菜单编码删除菜单
     * */
    //@MySysLog(value = "删除菜单", type = "3")
    @DeleteMapping("menu/deleteByMenuCode")
    @ApiOperation(notes = "menu/deleteByMenuCode", value = "根据菜单编码删除菜单", httpMethod = "DELETE")
    public JsonResult deleteByMenuCode(@ApiParam(value = "菜单编码", name = "menuCode", required = true) @RequestParam String menuCode){

        return menuService.deleteByMenuCode(menuCode);
    }


    /**
     * 根据菜单编码获取菜单编
     * */
    //@MySysLog(value = "根据菜单编码获取菜单", type = "2")
    @GetMapping("menu/findByMenuCode")
    @ApiOperation(notes = "menu/findByMenuCode", value = "根据菜单编码获取菜单", httpMethod = "GET")
    public JsonResult findByMenuCode(@ApiParam(value = "菜单编码", name = "menuCode", required = true) @RequestParam String menuCode){
        return menuService.findByMenuCode(menuCode);
    }
}
