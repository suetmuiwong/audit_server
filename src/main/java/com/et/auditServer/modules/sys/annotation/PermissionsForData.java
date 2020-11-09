package com.et.auditServer.modules.sys.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据权限注解，必须和updateUserPermissions注解一同使用
 * 此注解在mapper层使用
 *
 */
@Target({ElementType.METHOD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsForData {
    boolean flag() default true;
}
