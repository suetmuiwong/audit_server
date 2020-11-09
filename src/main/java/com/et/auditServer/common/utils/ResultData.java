package com.et.auditServer.common.utils;

import com.et.auditServer.common.exception.BaseException;
import com.et.auditServer.common.exception.CodeEnum;
import com.et.auditServer.common.exception.RuntimeException;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * TODO 返回结果的封装类对象
 *
 * @param <T> 定义返回结果类具体数据的类型
 * @author Qing.GuangPing
 * @since 2019-01-06
 */
@ApiModel()
public class ResultData<T> {

    private static final long serialVersionUID = 1L;

    /**
     * 返回码.
     */
    @ApiModelProperty(value = "结果代码字段，0000为成功，其他为错误")
    private String resultCode;
    /**
     * 返回信息
     */
    private String resultMsg;

    /**
     * 具体数据对象
     */
    private T content;// 具体的对象

    public ResultData(String resultCode, String resultMsg, T content) {
        this.resultCode = resultCode;
        this.resultMsg = resultMsg;
        this.content = content;

    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultCode = CodeEnum.FAILED.getCode();
        this.resultMsg = resultMsg;
    }

    public ResultData(CodeEnum codeEnum) {
        this.resultCode = codeEnum.getCode();
        this.resultMsg = codeEnum.getMessage();
    }

    public ResultData(BaseException baseException){
        this.resultCode = baseException.getErrorCode();
        this.resultMsg = baseException.getErrorMessage();
    }

    public ResultData(RuntimeException runtimeException){
        this.resultCode = runtimeException.getErrorCode();
        this.resultMsg = runtimeException.getErrorMessage();
    }

    public ResultData(T content) {
        this.resultCode = CodeEnum.SUCCESS.getCode();
        this.resultMsg = CodeEnum.SUCCESS.getMessage();
        this.content = content;
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public ResultData() {
        super();
        this.resultCode = CodeEnum.SUCCESS.getCode();
        this.resultMsg = CodeEnum.SUCCESS.getMessage();
    }
}
