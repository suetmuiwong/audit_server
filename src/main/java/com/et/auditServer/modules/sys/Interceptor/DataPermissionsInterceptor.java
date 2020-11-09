package com.et.auditServer.modules.sys.Interceptor;

import com.et.auditServer.modules.sys.annotation.PermissionsForData;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Properties;


/*@Intercepts({
        @Signature(type = StatementHandler.class, method = "prepare", args = {Connection.class, Integer.class})
})
@Component*/
public class DataPermissionsInterceptor implements Interceptor {
    private final String COUNT_STR = "_COUNT";

    public static List<String> appNameList = null;
    public static String currentLoginCode = null;
    public static boolean isSuperAdmin = false;

    @Override
    public Object intercept(Invocation invocation) throws Throwable {

        //判断用户是否为超级管理员，为true直接放行
//        if (isSuperAdmin) {
            return invocation.proceed();
//        }

    }




    /**
     * 拼接sql函数in的参数（appName）
     *
     * @param appNameList
     * @return
     */
    private String joinAppName(List<String> appNameList) {
        String result = "";
        if (appNameList == null || appNameList.size() == 0) {
            return "''";
        }
        for (String appName : appNameList) {
            result += "'" + appName + "'" + ",";
        }
        return result.substring(0, result.lastIndexOf(","));
    }

    @Override
    public Object plugin(Object target) {
        if (target instanceof StatementHandler) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }

    }

    @Override
    public void setProperties(Properties properties) {

    }
}
