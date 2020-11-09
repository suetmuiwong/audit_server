package com.et.auditServer.modules.sys.annotation;

import java.lang.annotation.*;

/**
 * 用于更新用户权限信息
 * 在需要加数据权限的接口（Controller）上使用，此注解和PermissionsForData注解搭配使用
 *
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface UpdateUserPermissions {
    String menuCode ();
}
