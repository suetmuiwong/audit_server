package com.et.auditServer.common.exception;

/**
 * Created by clark on 16-12-1.
 */
public class TimeoutException extends BaseException  {

    public TimeoutException(){}

    public TimeoutException(String errorCode, String message) {super(errorCode, message);}
}
