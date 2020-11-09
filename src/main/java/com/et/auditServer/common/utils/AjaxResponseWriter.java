package com.et.auditServer.common.utils;

import com.google.common.collect.Maps;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * 当前端通过ajax请求时，直接将返回结果写回前端
 * @author yangwk
 */
public class AjaxResponseWriter {

	/**
	 * 写回数据到前端
	 * @param request
	 * @param response
	 * @param code
	 * @param message 返回的描述信息
	 * @throws IOException
	 */
	public static void write(HttpServletRequest request, HttpServletResponse response, String code, String message) throws IOException{
		String contentType = "application/json";
		response.setContentType(contentType);
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Access-Control-Allow-Credentials", "true");
		response.setHeader("Access-Control-Allow-Origin",request.getHeader("Origin"));
		
		Map<String, String> map = Maps.newLinkedHashMap();
		map.put("code", code);
		map.put("msg", message);
		String result = JacksonHelper.toJson(map);
		PrintWriter out = response.getWriter();
		try{
			out.print(result);
			out.flush();
		} finally {
			out.close();
		}
	}
	
}
