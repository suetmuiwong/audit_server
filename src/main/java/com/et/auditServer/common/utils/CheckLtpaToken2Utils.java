package com.et.auditServer.common.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;
@Component
public class CheckLtpaToken2Utils {


    public static Map<String, Object> checkLtpaToken2(HttpServletRequest req) {
        Map<String, Object> resultMap = new HashMap<>();
        /*if (!checkFlag) {
            resultMap.put("checkResult", true);
            resultMap.put("loginName", "");
            resultMap.put("ltpaToken2", "");
            return resultMap;
        }*/

        resultMap.put("checkResult", false);
        resultMap.put("loginName", "");
        resultMap.put("ltpaToken2", "");
        String cookies_in_header = req.getHeader("Cookie");
        HashMap<String, String> cookies = new HashMap<>();
        if (cookies_in_header == null || cookies_in_header.length() < 1){
            return resultMap;
        }else {
            cookies_in_header = cookies_in_header.replaceAll("\\=", "等.号");
            String[] cookies_array = cookies_in_header.split(";");
            String[] cookie_set;
            String singleCookie;
            String cv;
            for (int i = 0; i < cookies_array.length; i++){
                singleCookie = cookies_array[i].trim();
                singleCookie = singleCookie.replaceFirst("等.号", "分.隔.符");
                singleCookie = singleCookie.replaceAll("等.号", "=");
                cookie_set = singleCookie.split("分.隔.符");
                if (cookie_set.length > 1){
                    if (cookie_set[0] != null && cookie_set[0].length() > 0 && cookie_set[1] != null){
                        cv = cookie_set[1].replaceAll("\"", "");
                        cookies.put(cookie_set[0], cv);
                    }
                }
            }
        }
        String ltpaToken2 = cookies.get("LtpaToken2");

        return CheckLtpaToken2Utils.getUserInfoByLtpaToken2(ltpaToken2);

    }

    public static Map<String,Object> getUserInfoByLtpaToken2(String ltpaToken2){

        Map<String, Object> resultMap = new HashMap<>();
        /*if (!checkFlag) {
            resultMap.put("checkResult", true);
            resultMap.put("loginName", "");
            resultMap.put("ltpaToken2", "");
            return resultMap;
        }*/

        resultMap.put("checkResult", false);
        resultMap.put("loginName", "");
        resultMap.put("ltpaToken2", "");

        return resultMap;


    }
}
