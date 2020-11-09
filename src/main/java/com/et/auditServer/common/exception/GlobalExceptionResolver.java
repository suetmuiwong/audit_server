package com.et.auditServer.common.exception;

/**
 * @ClassName GlobalExceptionResolver
 * @Description TODO 全局处理异常捕获.
 * 捕获controller未处理的异常。
 * @Author qgp
 * @Date 2019-03-01
 */

import com.et.auditServer.common.utils.JsonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Set;


@ControllerAdvice
public class GlobalExceptionResolver {
    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionResolver.class);

    /**
     * 处理所有不可知的异常
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    JsonResult handleException(Exception e){
        logger.error(e.getMessage(), e);
        return JsonResult.fail();
    }

    /**
     * 处理所有业务异常
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    JsonResult handleBusinessException(BusinessException e){
        logger.info(e.getMessage(), e);
        return JsonResult.fail(e);
    }
    /**
     * 处理所有Validator校验异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    JsonResult handleConstraintViolationException(ConstraintViolationException e){
        logger.info(e.getMessage(), e);
        StringBuffer sb = new StringBuffer("");
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for(ConstraintViolation<?> violation:violations){
            sb.append(violation.getMessage()).append(",");
        }
        String retMsg = sb.toString().substring(0,sb.toString().length()-1);
        return JsonResult.failMessage(retMsg);
    }

    /**
     * 处理shiro权限不足异常,目前无权限日志会打印异常
     * @param e
     * @return
     */
    /*@ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    JsonResult handleUnauthorizedException(UnauthorizedException e){
        return JsonResult.failMessage(JsonReturnCode.ACCESS_ERROR);
    }*/

    /**
     * 处理shiro的session过期问题
     * @param e
     * @return
     */
    /*@ExceptionHandler(UnknownSessionException.class)
    @ResponseBody
    JsonResult handleUnknownSessionException(UnknownSessionException e){
        return JsonResult.failMessage(JsonReturnCode.LOGIN_AGAIN);
    }*/

    /*@ExceptionHandler(value = {MultipartException.class })
    @ResponseBody
    JsonResult handleMultipartException(MultipartException e) {
        if (e.getMessage().contains("FileUploadBase$SizeLimitExceededException")) {
            return JsonResult.failMessage(JsonReturnCode.FILE_UPLOAD_SIZE_EXCEEDED);
        }
        return JsonResult.fail();
    }*/
}
