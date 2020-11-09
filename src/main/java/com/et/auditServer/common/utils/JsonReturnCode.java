package com.et.auditServer.common.utils;

/**
 * 描述: json格式数据返回码
 *<ul>
 *      <li>100 : 用户未登录 </li>
 *      <li>200 : 成功 </li>
 *      <li>300 : 失败 </li>
 * </ul>
 * @author : Administrator
 */
public enum JsonReturnCode {

    NOT_LOGIN("401","未登录"),
    SUCCESS ("200","成功"),
    FAIL ("500","内部失败"),
    ACCESS_ERROR ("403","权限不足,禁止访问"),
    NOT_FOUND ("404","页面未发现"),
    LOGIN_AGAIN ("405","请重新登录!"),
    UN_ROLE_ERROR("406", "权限不足，请联系管理员！"),
    GETVERIFY_FAIL("1001","获取验证码失败"),
    PARAM_ERROR("1002","请求参数异常"),
    VERIFY_OVERDUE("1003","验证码失效"),
    VERIFY_ERROR("1004","验证码输入有误"),
    USER_LOCKED("1005","用户锁定"),
    USER_NOTFOUND("1006","用户不存在"),
    PASSWORD_ERROR("1007","账号密码错误"),
    SAVE_FAIL("1008","保存失败"),

    ROLE_EXISTS("1012","角色代码已存在！"),
    ROLE_NOTEXISTS("1013","角色代码不存在！"),
    MENU_EXISTS("1014","菜单代码已存在，不能新增！"),
    MENU_NOTEXISTS("1015", "待修改的菜单不存在！"),
    PARENT_NOTEXISTS("1016", "父级代码不存在！"),
    EMPCODE_EXISTS("1017", "用户编码已存在！"),
    DICT_STOP_MSG("1018","该字典数据包含未停用的子字典！"),
    NEWPASSWORD_ERROR("1019","两次输入的新密码不一致！"),
    LOGINCODE_EXISTS("1020","用户账号已存在！"),
    OLDPASSWORD_ERROR("1021","输入的旧密码错误！"),
    PASSWORD_CONTAINSALL ("1035","密码必须同时包含字母和数字！"),
    PASSWORD_DECRYPTION_ERROR("1036","密码解密错误！"),
    NEW_OLD_PASSWORD_EQUALS_ERROR("1037","新旧密码一致不需要修改！"),
    USER_ALREADY_LOGGED("1038", "用户已登录！"),
    USER_LOGIN_ELSEWHERE("1039", "该用户在其他设备登录，请重新登录！"),
    LOGINCODE_NOT_EXISTS("1040","用户账号不存在！"),
    CACHE_ERROR("1041", "获取缓存数据有误！"),
    DELETE_FAIL("1042","删除失败"),
    DELETE_SUCCESS("1043", "删除成功"),
    SAVE_SUCCESS("1044", "保存成功"),
    CACHE_CONNECTION_ERROR("1045", "缓存连接异常！"),
    SYSTEM_ROLE("1048", "系统内置角色，不能修改或删除！"),
    SYSTEM_USER("1049", "系统内置用户，不能修改或删除！"),
    GET_FASTDFS_TOKEN_ERROR("1054", "获取fastDFS的token失败！"),
    UPLOAD_FILE_FASTDFS_ERROR("1055", "上传文件到fastDFS失败！"),
    GET_FASTDFS_DOWNLOAD_ERROR("1056", "获取fastDFS的下载路径失败！"),;

    private String code;
    private String desc;

    JsonReturnCode(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
