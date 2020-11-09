package com.et.auditServer.modules.sys.controller;

import com.et.auditServer.common.utils.JsonResult;
import com.et.auditServer.common.utils.JsonReturnCode;
import com.et.auditServer.modules.sys.dto.UserDto;
import com.github.pagehelper.PageInfo;
import com.et.auditServer.modules.sys.dto.AddUserDto;
import com.et.auditServer.modules.sys.entity.User;
import com.et.auditServer.modules.sys.service.UserService;
import io.swagger.annotations.*;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * @description:用户管理，登录注册，找回密码
 * @author: qgp
 * @create: 2019-03-01 16:24
 **/
@RestController
@Api(tags = "用户管理", description = "用户管理接口")
@RequestMapping(value = "/user/")
public class UserController extends AbstractController {

    @Autowired
    UserService userService;

    /**
     * 获取用户列表
     * @return
     */
//    @RequiresPermissions("sys:empUser:view")
    //@MySysLog(value = "查询用户列表", type = "2")
    @PostMapping("/getUserList")
    @ApiOperation(notes = "/getUserList", value = "获取用户列表", httpMethod="POST")
    public JsonResult getUserList(@ApiParam(value = "报文体", name = "请求对象", required = true)  @RequestBody UserDto userDto,
                                  @ApiParam(value = "页码", name = "pageNo", required = true) @RequestParam int pageNo,
                                  @ApiParam(value = "页数", name = "pageSize", required = true) @RequestParam int pageSize){
        PageInfo<User> userPageList = userService.getUserList( pageNo, pageSize);
        return JsonResult.success(userPageList);
    }

    /**
     * 获取用户详情
     * @param userName
     * @return
     */
//    @RequiresPermissions("sys:empUser:edit")
    //@MySysLog(value = "查询用户详情", type = "2")
    @GetMapping(value = "/getUserInfo")
    @ApiOperation(notes = "/getUserInfo", value = "获取用户详情", httpMethod = "GET")
    public JsonResult getUserInfo(@ApiParam(value = "用户名", name = "userName", required = true)@RequestParam String userName){
        //校验入参
        if(StringUtils.isBlank(userName)){
            logger.error("请求参数有误，请您稍后再试");
            return JsonResult.failMessage(JsonReturnCode.PARAM_ERROR);
        }
        User user = userService.getUserByUserName(userName);
        return JsonResult.success(user);
    }


    /**
     * 保存用户信息
     * @param userDto
     * @return
     */
    //@MySysLog(value = "新增用户信息", type = "0")
    @PostMapping(value = "/addUserInfo")
    @ApiOperation(notes = "/addUserInfo", value = "添加用户信息", httpMethod = "POST")
    public  JsonResult addUserInfo(@ApiParam(name = "报文体", value = "用户对象") @RequestBody AddUserDto userDto){
        //校验入参
        if(userDto == null||StringUtils.isBlank(userDto.getUserName())||StringUtils.isBlank(userDto.getUserPhone())||StringUtils.isBlank(userDto.getUserEmail())){
            logger.error("请求参数有误，请您稍后再试");
            return JsonResult.failMessage(JsonReturnCode.PARAM_ERROR);
        }
        userService.addUserInfo(userDto);
        return JsonResult.success();
    }

    /**
     * 删除用户
     * @param userName
     * @return
     */
    //@MySysLog(value = "删除用户", type = "3")
    @DeleteMapping(value = "/deleteUserInfo")
    @ApiOperation(notes="/deleteUserInf", value = "删除用户", httpMethod = "DELETE")
    public  JsonResult deleteUserInfo(@ApiParam(value = "用户名", name = "userName", required = true) @RequestParam @Validated String userName){
        //校验入参
        if(StringUtils.isBlank( userName)){
            logger.error("请求参数有误，请您稍后再试");
            return JsonResult.failMessage(JsonReturnCode.PARAM_ERROR);
        }
        userService.deleteUserInfo(userName);
        return JsonResult.success();
    }

    /**
     * 修改用户
     * @param userDto
     * @return
     */
    //@MySysLog(value = "修改用户", type = "1")
    @PutMapping(value = "/updateUserInfo")
    @ApiOperation(notes = "/updateUserInfo", value = "修改用户", httpMethod = "PUT")
    public  JsonResult updateUserInfo(@ApiParam(name = "报文体", value = "用户对象") @RequestBody AddUserDto userDto){
        //校验入参
        if(userDto == null||StringUtils.isBlank(userDto.getUserName())){
            logger.error("请求参数有误，请您稍后再试");
            return JsonResult.failMessage(JsonReturnCode.PARAM_ERROR);
        }
        userService.updateUserInfo(userDto);
        return JsonResult.success();
    }


    /**
     * 重置密码
     * @param userName
     * @return
     */
    //@MySysLog(value = "重置用户密码", type = "1")
    @PutMapping(value = "/resetUserInfo")
    @ApiOperation(notes = "/resetUserInfo", value = "重置密码", httpMethod = "PUT")
    public  JsonResult resetUserInfo(@ApiParam(value = "用户名", name = "userName", required = true) @RequestParam String  userName){
        //校验入参
        if(StringUtils.isBlank(userName)){
            logger.error("请求参数有误!");
            return JsonResult.failMessage(JsonReturnCode.PARAM_ERROR);
        }

        return  userService.resetUserInfo(userName);
    }

    /**
     * 获取登录用户详情
     * @return
     */
    //@RequiresPermissions("sys:empUser:edit")
    //@MySysLog(value = "查询用户详情", type = "2")
    @GetMapping(value = "/getLoginUserInfo")
    @ApiOperation(notes = "/getLoginUserInfo", value = "获取当前登录人用户详情", httpMethod = "GET")
    public JsonResult getLoginUserInfo(){
        User user = userService.getLoginUserInfo();
        return JsonResult.success(user);
    }

    @GetMapping("/selectUserByLikeUserNameOrUserPhone")
    @ApiOperation(notes = "/selectUserByLikeUserNameOrUserPhone", value = "根据用户姓名或者手机号模糊查询用户信息", httpMethod = "GET")
    public JsonResult selectUserByLikeUserNameOrUserPhone(@ApiParam(value = "用户姓名或者手机号", name = "userNameOrUserPhone", required = true) @RequestParam String userNameOrUserPhone) {
        return JsonResult.success(userService.selectUserByLikeUserNameOrUserPhone(userNameOrUserPhone));
    }

}
