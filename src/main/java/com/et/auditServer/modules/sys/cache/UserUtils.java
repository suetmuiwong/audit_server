/**
 * Copyright &copy; 2012-2016 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.et.auditServer.modules.sys.cache;

import com.et.auditServer.common.exception.BusinessException;
import com.et.auditServer.common.utils.JsonReturnCode;
import com.et.auditServer.common.utils.RedisUtils;
import com.et.auditServer.modules.sys.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 用户工具类
 * @author ThinkGem
 * @version 2013-12-05
 */
@Service
public class UserUtils {

	public static final String CACHE_ALL_DATA_USER = "allDataUser";
	@Autowired
	private RedisUtils redisCache;
	/**
	 * 获取当前登录者对象
	 */
	public static User getActiveUser(){
		//获取到当前线程绑定的请求对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		User user = new User();
		user.setUserName(String.valueOf(request.getSession().getAttribute("loginName")));
		if (user.getUserName() == null || "null".equals(user.getUserName())) {
			throw new BusinessException(JsonReturnCode.LOGIN_AGAIN);
		}
		return user;
	}


	//获取拥有全数据权限的用户列表
	public boolean checkAllDataUser(String loginCode){
		boolean flag = true;
//		List<String> userList = null;
//		try {
//			userList = (List<String>) redisCache.get(CACHE_ALL_DATA_USER);
//		} catch (Exception e) {
//			throw new BusinessException(JsonReturnCode.CACHE_ERROR);
//		}
//		if (userList==null){
//			userList = userDao.getAlldataUserlist();
//
//			try {
//				redisCache.set(CACHE_ALL_DATA_USER, userList, RedisUtils.NOT_EXPIRE);
//			} catch (Exception e) {
//				throw new BusinessException(JsonReturnCode.CACHE_CONNECTION_ERROR);
//			}
//		}
//		for (String user : userList) {
//			if (user.equals(loginCode)) {
//				flag = true;
//				break;
//			}
//		}
		return flag;
	}

	//清理缓存
	public void clearDictCache(){
		try {
			redisCache.delete(CACHE_ALL_DATA_USER);
		} catch (Exception e) {
			throw new BusinessException(JsonReturnCode.CACHE_CONNECTION_ERROR);
		}

	}
}
