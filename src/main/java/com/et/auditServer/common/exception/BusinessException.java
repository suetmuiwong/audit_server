package com.et.auditServer.common.exception;

import com.et.auditServer.common.utils.JsonReturnCode;

import java.lang.RuntimeException;
import java.util.Map;

/**
 * 获取参数异常
 */
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 8639609611794399340L;

    /**
     * @param message
     */
    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }

    private String errorCode;
    private String errorMsg;

    public BusinessException() {
    }

    /**
     * 构造异常实体
     *
     * @param errorMap：错误Map
     */
    public BusinessException(Map errorMap) {
        super(errorMap.get("errorMsg").toString());
        this.errorCode = errorMap.get("errorCode").toString();
        this.errorMsg = errorMap.get("errorMsg").toString();
    }
    /**
     * 构造异常实体
     *
     */
    public BusinessException(JsonReturnCode jsonReturnCode) {
        super(jsonReturnCode.getDesc());
        this.errorCode = jsonReturnCode.getCode();
        this.errorMsg = jsonReturnCode.getDesc();
    }


    /**
     *
     * @param errorCode：错误码，4位。若为其他系统返回的报错，截取返回的错误码后4位填入
     * @param errorMsg:错误信息。
     */
    public BusinessException(String errorCode, String errorMsg) {
        super(errorMsg);
        this.errorCode = errorCode;
        this.errorMsg = errorMsg;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

}
