package com.et.auditServer.modules.sys.Interceptor;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.utils.AjaxResponseWriter;
import com.et.auditServer.common.utils.CheckLtpaToken2Utils;
import com.et.auditServer.common.utils.RedisUtils;
import com.et.auditServer.common.utils.*;
import com.et.auditServer.modules.sys.entity.User;
import com.et.auditServer.modules.sys.service.UserService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Component
public class URLHandlerInterceptor implements HandlerInterceptor {

    @Autowired
    private UserService userService;
    @Autowired
    private RedisUtils redisCache;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object user = request.getSession().getAttribute("loginName");
        if(user == null){
            //未登陆，返回登陆页面
            AjaxResponseWriter.write(request, response, "401", "请登录");
            //response.sendRedirect("/");     //没有user信息的话进行路由重定向
            return false;
        }else{
            //已登陆，放行请求
            return true;
        }
//        System.out.println(request);
//        System.out.println(response);

         /*if (true) {//开发关闭拦截
            String loginName = (String) request.getSession().getAttribute("loginName");
            System.out.println("loginName开发关闭拦截"+loginName);
            if (StringUtils.isEmpty(loginName)) {
                AjaxResponseWriter.write(request, response, "401", "请登录");
                //response.sendRedirect("/");     //没有user信息的话进行路由重定向
                return false;
            }
            return true;
        }*/
        //请求进入这个拦截器
//        String authorization = request.getHeader("Authorization");
//        if(authorization == null){
//            authorization = request.getHeader("token");
//            if (authorization == null){
//                authorization = request.getParameter("Authorization");
//            }
//
//        }
//        if(authorization == null){
//            Cookie[] cookies = request.getCookies();
//            if(cookies != null){
//                for (Cookie cookie : cookies) {
//                    if (cookie.getName().equals("LtpaToken2")) {
//                        authorization = cookie.getValue();
//                    }
//                }
//            }
//        }
//
//        if (redisCache == null) {
//            BeanFactory factory = WebApplicationContextUtils.getRequiredWebApplicationContext(request.getServletContext());
//            redisCache = (RedisUtils) factory.getBean("redisUtils");
//        }
//        User user = null;
//        if (authorization != null && authorization.length() == Constant.ADMIN_AUTHORIZATION_LENGTH) {//只有admin才从redis获取用户信息
//            Object obj = redisCache.get(authorization);
//            if (obj != null) {
//                user = (User) obj;
//            }
//        }
//        if (user != null && "admin".equals(user.getLoginCode())) {//超级用户
//            HttpSession session = request.getSession();
//            //把用户数据保存在session域对象中
//            session.setAttribute("loginName", user.getLoginCode());
//            return true;
//        }
//        //检查Portal Token登陆信息
//        Map<String, Object> resultMap = CheckLtpaToken2Utils.checkLtpaToken2(request);
//
//
//        if (!(boolean) resultMap.get("checkResult")) {//验证token
////            System.out.println("进入拦截器");
//            /*if("XMLHttpRequest".equalsIgnoreCase(request.getHeader("X-Requested-With"))){
//                response.sendError(401);
//            }*/
//            AjaxResponseWriter.write(request, response, "401", "请登录");
//            //response.sendRedirect("/");     //没有user信息的话进行路由重定向
//            return false;
//        }
//
//        String loginCode = (String) resultMap.get("loginName");
//        // SSO单点登陆模式
//        String ssoLoginName = loginCode;
//
//        if (ssoLoginName.endsWith("_a")) {
//            // 去除尾部“_a”投行账号特征
//            ssoLoginName = ssoLoginName.substring(0, ssoLoginName.length() - 2);
//        }
//        //先查询有没有该用户账户
//        User activeUser = userService.getUserByUserName(ssoLoginName);
//        System.out.println(activeUser+"ssoLoginName");
//
//        if (activeUser == null) {
//            AjaxResponseWriter.write(request, response, "401", "请登录");
//            return false;
//        }
//        if (!Constant.STR_ZERO.equals(activeUser.getState())) {
//            AjaxResponseWriter.write(request, response, "401", "请登录");
//            return false;
//        }
//        HttpSession session = request.getSession();
//        //把用户数据保存在session域对象中
//        session.setAttribute("loginName", resultMap.get("loginName"));
//        redisCache.delete(loginCode);
       // return true;    //有的话就继续操作
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

}
