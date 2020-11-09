package com.et.auditServer.common.utils;


import java.util.HashMap;
import java.util.Map;

/**
 * 业务处理状态码定义基本类
 * Created by googe on 2016-03-16.
 */
public final class CodeTable {
    private static Map<String, String> codeTable = new HashMap<String, String>();//手动定义错误代码对应相关错误信息

    public final static String SUCCESS = "0000";//正常 key+value

    public final static String SUCCESS_NODATA = "0001";//查询成功，无结果
    public final static String SUCCESS_JSON_ERROR = "0002";//查询成功，返回的JSON有误
    public final static String SUCCESS_CACHE_NOTEXIST = "0003";//缓存有效期内无记录

    public final static String ACCESS_OTS_ERROR = "0011";//OTS无法访问
    public final static String ACCESS_EXT_ERROR = "0014";//无法访问外部数据接口

    public final static String DECODE_ERROR = "0018"; //解密异常
    public final static String ENCODE_ERROR = "0019"; //对请求参数进行加密处理时发生异常
    public final static String XML2JSON_ERROR = "0020"; //XML转JSON异常
    public final static String XML_PARSE_ERROR = "0021"; // 解析XML错误

    public final static String USER_NOTEXIST = "0100";//账号不存在
    public final static String USERNAME_OR_PASS_ERROR = "0101";//用户名或密码错误
    public final static String ACOUNT_DISABLED = "0102";//账户被禁用

    public final static String INTERFACE_AUTH_ERROR = "0103"; //没有该接口的访问权限
    public final static String ARGUMENTS_ERROR = "0104";//args参数错误
    public final static String SIGN_ERROR = "0105"; //数据签名错误

    public final static String ILLEGAL_IP_ERROR = "0107";//非法IP请求
    public final static String INTERFACE_NOTSUPPORT_CACHE = "0108";//该接口不支持缓存方式

    public final static String SQL_OPERATOR_ERROR = "0200";//sql 操作失败

    public final static String INVOKE_ERROR = "0598";//调用service异常

    public final static String ADD_SORTER_FAILED = "0300"; // 添加分类失败
    public final static String UPDATE_SORTER_FAILED = "0301";// 修改分类失败
    public final static String DELETE_SORTER_FAILED = "0302";// 删除分类失败

    public final static String NOT_SUBSCRIBE_ERROR = "0400"; // 资源未被订阅
    public final static String RPT_SUBSCRIBE_CANCEL_ERROR = "0401"; // 资源重复订阅或取消

    public final static String CREATE_SSLCONTEXT_ERROR = "0995";//创建 SSLConetxt 错误
    public final static String LOAD_KEYSTORE_ERROR = "0996"; // 加载本地错误
    public final static String LOAD_TRUSTSTORE_ERROR = "0997"; // 加载服务端错误

    public final static String UNKNOWN_ERROR = "0998";//未知异常
    public final static String SYSTEM_ERROR = "0999"; //系统异常
    public final static String THIRDPARTY_ERROR = "1000";//第三方错误

    public final static String E_HANDLE_PARSE = "1100";//handle报文解析出错
    public final static String E_HANDLE_CATALOG = "1101";//handle目录操作错误


    static {

        codeTable.put(SUCCESS, "操作成功");

        codeTable.put(SUCCESS_NODATA, "查询成功，无结果");
        codeTable.put(SUCCESS_JSON_ERROR, "查询成功，返回的JSON有误");
        codeTable.put(SUCCESS_CACHE_NOTEXIST, "缓存有效期内无记录");

        codeTable.put(ACCESS_OTS_ERROR, "OTS无法访问");
        codeTable.put(ACCESS_EXT_ERROR, "无法访问外部数据接口");

        codeTable.put(DECODE_ERROR, "解密异常");
        codeTable.put(ENCODE_ERROR, "对请求参数进行加密处理时发生异常");
        codeTable.put(XML2JSON_ERROR, "XML转JSON异常");
        codeTable.put(XML_PARSE_ERROR, "解析XML异常");

        codeTable.put(USER_NOTEXIST, "账户不存在");
        codeTable.put(USERNAME_OR_PASS_ERROR, "用户名或密码错误");
        codeTable.put(ACOUNT_DISABLED, "账户被禁用");
//        codeTable.put(INTERFACE_NOTEXIST_ERROR, "请求的接口不存在");
        codeTable.put(INTERFACE_AUTH_ERROR, "没有该接口的访问权限");
        codeTable.put(ARGUMENTS_ERROR, "args参数错误");
//        codeTable.put(SIGN_ERROR, "数据签名错误");
//        codeTable.put(INSUFFICIENT_ERROR, "余额不够，无法进行查询");
        codeTable.put(ILLEGAL_IP_ERROR, "非法IP请求");
        codeTable.put(INTERFACE_NOTSUPPORT_CACHE, "该接口不支持缓存方式");

        codeTable.put(UNKNOWN_ERROR, "未知异常");
        codeTable.put(SYSTEM_ERROR, "系统异常");
        codeTable.put(THIRDPARTY_ERROR, "第三方错误");

        codeTable.put(ARGUMENTS_ERROR, "参数异常");

        codeTable.put(INVOKE_ERROR, "调用异常");

        codeTable.put(SQL_OPERATOR_ERROR, "SQL 执行异常");

        codeTable.put(ADD_SORTER_FAILED, "分类目录节点添加失败");
        codeTable.put(UPDATE_SORTER_FAILED, "分类目录节点修改失败");
        codeTable.put(DELETE_SORTER_FAILED, "分类目录节点删除失败");

        codeTable.put(NOT_SUBSCRIBE_ERROR, "资源未被订阅");
        codeTable.put(RPT_SUBSCRIBE_CANCEL_ERROR, "资源重复订阅或取消");

        codeTable.put(LOAD_KEYSTORE_ERROR, "加载本地错误");
        codeTable.put(LOAD_TRUSTSTORE_ERROR, "加载服务端错误");
        codeTable.put(CREATE_SSLCONTEXT_ERROR, "创建 SSLConetxt 错误");
        codeTable.put(E_HANDLE_PARSE, "handle返回报文格式错误");
        codeTable.put(E_HANDLE_CATALOG, "handle目录接口访问出错");
    }

    /**
     * 获取异常描述
     *
     * @param code
     * @return
     */
    public static String getCodeDescribe(String code) {
        if (codeTable.get(code) == null) {
            return "";
        }
        return codeTable.get(code);
    }
}
