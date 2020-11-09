package com.et.auditServer.common.exception;

import com.et.auditServer.common.utils.CodeTable;

/**
 * Created by ningkai on 16-9-23.
 */
public class RuntimeException extends java.lang.RuntimeException {
    /**
     * 错误代码,默认为未知错误
     * */
    /**
     * 错误代码,默认为未知错误
     * */
    private String errorCode = CodeTable.UNKNOWN_ERROR;


    private String errorMessage = CodeTable.getCodeDescribe(CodeTable.UNKNOWN_ERROR);

    public RuntimeException() {
    }

    public RuntimeException(String errorCode) {
        this.errorCode = errorCode;
    }

    public RuntimeException(String errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }

    public RuntimeException(String errorCode, Throwable cause) {
        super(cause);
        this.errorCode = errorCode;
        this.errorMessage = cause.getMessage();
    }

    public RuntimeException(String errorCode, String message, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.errorMessage = message;
    }


    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }
}
