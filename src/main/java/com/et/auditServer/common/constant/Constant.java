package com.et.auditServer.common.constant;

import java.math.BigDecimal;

public class Constant {
    //session
    public static final String SESSION_LOGIN_TOKEN = "LOGIN_TOKEN";

    public final static short ONE = 1;

    public final static short ZERO = 0;

    public final static String STR_ONE = "1";
    public final static String STR_ZERO = "0";

    public final static String INVESTOR_TYPE = "INVESTOR_TYPE";
    public final static String BD_TYPE = "BD_TYPE";
    public final static String BD_TRADE_TYPE = "BD_TRADE_TYPE";
    public final static String CONFIRM_FLAG = "CONFIRM_FLAG";
    public final static String PERSONNEL_SCALE_TYPE = "PERSONNEL_SCALE_TYPE";
    public final static String REGION_TYPE = "REGION_TYPE";
    public final static String INDUSTRY_TYPE = "INDUSTRY_TYPE";
    public final static String COMPANY_TYPE = "COMPANY_TYPE";
    public final static String MANAGEMENT_STATE_TYPE = "MANAGEMENT_STATE_TYPE";
    public final static String DISTRIBUTION_MODE = "DISTRIBUTION_MODE";

    public final static String TOKEN_URL = "TOKEN_URL";//token验证url
    public final static String LOGON_SSO_URL = "LOGON_SSO_URL";//单点登录接口url
    public final static String ZERO_POINT = " 00:00:00";

    public final static String TWENTY_FOUR_POINTS = " 24:00:00";

    public final static BigDecimal BIG_DECIMAL_HUNDRED = new BigDecimal("100");
    public final static BigDecimal BIG_DECIMAL_ZERO = new BigDecimal("0");

    public final static long SEMIH = 1800;

    //大于等于
    public final static String GT_EQUALS = ">=";
    //小于等于
    public final static String LT_EQUALS = "<=";

    //加号
    public final static String PLUS = "+";
    //减号
    public final static String MINUS_SIGN = "-";

    //新增标记字符串
    public final static String ADD_FLAG_STR = "\"isNewRecord\":true";

    public final static String ADD_ZH = "新增";

    public final static String UPDATE_ZH = "修改";

    public final static String ALL_DATA_USER = "allDataUser";

    public final static int GROUP_SIZE = 500;//每次提交数量

    public final static String ALL_DATA_ROLE_CODE = "alldataroleCode";

    public final static String ALERT_TIME = "ALERT_TIME";//预警基础参数

    public final static String ALL = "all";

    public final static String COMMA = ",";

    public final static String COLON = ":";

    public final static String SUP_U_P = "253b94aedb6cb272f335a279f66020d4";

    public final static String UNDERLINE = "_";


    public final static String HTTP="http://";

    public final static String QUESTION_MARK = "?";

    public final static String LEFT_SLASH = "/";

    public final static String SALESMAN_ROLE_CODE = "salesmanRoleCode";

    public final static int ADMIN_AUTHORIZATION_LENGTH = 32;//admin的AUTHORIZATION长度是32

    public final static int ERROR_COUNT_EFFECTIVE_TIME = 900;//登录错误次数有效时间  秒

    public final static int DOWNLOAD_DATA_TIME = 60;//下载数据保存时间  秒

    public final static String STR_SPOT = "."; //小数点

    public final static String FAST_DFS_TOKEN_ID = "FastDFSToken";


    public final static String TOKEN_INTERFACE_URL = "TOKEN_INTERFACE_URL";//token接口url

    public final static String LOGIN_INTERFACE_URL = "LOGIN_INTERFACE_URL";//登录接口url

    public final static String FAST_DFS_APPID = "FAST_DFS_APPID";//fastDFS的appId
    public final static String FAST_DFS_APPKEY = "FAST_DFS_APPKEY";//fastDFS的appKey
    public final static String FAST_DFS_TOKEN_URL = "FAST_DFS_TOKEN_URL";//fastDFS的tokenUrl
    public final static String FAST_DFS_UPLOAD_URL = "FAST_DFS_UPLOAD_URL";//fastDFS的上传Url
    public final static String FAST_DFS_DOWNLOAD_URL = "FAST_DFS_DOWNLOAD_URL";//fastDFS的下载url
    public final static String FAST_DFS_DELETE_URL = "FAST_DFS_DELETE_URL";//fastDFS的删除url



    public final static String BOND_PSD_SECRETARIES = "15958070254004abcdef"; //密码加密密文

    public final static String GF_CAPTCHA_EXPIRE = "GF_CAPTCHA_EXPIRE";//验证码code

    public final static String GF_DEFAULT_PSWD = "GF_DEFAULT_PASSWORD";//重置的默认密码code


    public final static String  LOGIC_DELETE_DELETE = "DELETE";//是否是逻辑删除
    public final static String  LOGIC_DELETE_NORMAL = "NORMAL";//是否是逻辑删除


}
