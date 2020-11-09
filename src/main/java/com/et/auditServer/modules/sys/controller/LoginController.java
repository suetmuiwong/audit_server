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

import javax.servlet.http.HttpSession;
import java.util.Map;


/**
 * @description:登录
 * @author: hxm
 * @create: 2020-11-07 16:24
 **/
@RestController
@Api(tags = "用户登录", description = "用户登录接口")
@RequestMapping(value = "")
public class LoginController extends AbstractController {
    @Autowired
    UserService userService;

    /**
     * 登录
     * @return
     */
    @PostMapping("/login")
    @ApiOperation(notes = "/login", value = "获取用户列表", httpMethod="POST")
    public JsonResult login(@ApiParam(value = "报文体", name = "请求对象", required = true)  @RequestBody UserDto userDto,
                            HttpSession session){
        if(userDto == null||StringUtils.isBlank(userDto.getUserName())||StringUtils.isBlank(userDto.getUserPassword())){
            logger.error("请求参数有误，请您稍后再试");
            return JsonResult.failMessage(JsonReturnCode.PARAM_ERROR);
        }
        User user = userService.getUserByPasswordUserName(userDto.getUserName(), userDto.getUserPassword());
        session.setAttribute("loginName",userDto.getUserName());
        return JsonResult.success(user);
    }

}
