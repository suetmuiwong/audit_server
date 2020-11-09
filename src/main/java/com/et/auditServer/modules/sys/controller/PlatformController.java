package com.et.auditServer.modules.sys.controller;

import com.et.auditServer.common.utils.JsonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/platform")
public class PlatformController  {


    @GetMapping("/error/checkHttp")
    public JsonResult checkHttp() {
        return JsonResult.success();
    }
}
