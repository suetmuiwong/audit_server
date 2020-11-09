package com.et.auditServer.common.exception;

/**
 * @author Li.Junlin
 * @date 2018.07.06 16:13
 **/
public enum CodeEnum {
    /**
     * 3000:二维码申请失败
     */
    SUCCESS("0000", "成功"),
    FAILED("9999", "失败"),

    // 请求参数错误
    PARAM_ERROR("1001", "参数错误"),
    OLD_PWD_ERROR("1002","原密码不正确"),
    NEW_OLD_PWD_SAME("1003","新旧密码相同"),

    // 数据库异常
    DATA_ALREADY_EXIST("2001", "数据库中已存在该记录"),

    // 系统异常
    URL_NOT_EXIST("9001", "路径不存在，请检查路径是否正确"),
    INVALID_TOKEN("9002", "token令牌无效"),
    USER_NOT_RIGHT("9003", "没有权限，请联系管理员授权"),
    VALIDATE_CODE_ERROR("9004", "验证码不正确"),
    ACCOUNT_OR_PWD_ERROR("9005", "账号或密码不正确"),
    ACCOUNT_IS_LOCK("9006", "账号已被锁定,请联系管理员"),
    ADMIN_NOT_DEL("9007","系统管理员不能删除"),
    PRESENT_USER_NOT_DEL("9008", "当前用户不能删除"),
    PRESENT_USER_NOT_EXIST("9009", "查询用户不存在");

    /**
     * 返回码
     */
    private String code;
    /**
     * 结果描述
     */
    private String message;

    CodeEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }


    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    /**
     * 取枚举的json字符串
     *
     * @return
     */
    public static String toJsonStr(CodeEnum senum) {
        StringBuffer jsonStr = new StringBuffer("[");
        jsonStr.append("{errorCode:'").append(senum.getCode()).append("',errorMes:'").append(senum.getMessage()).append("'}");
        jsonStr.append("]");
        return jsonStr.toString();
    }
}
