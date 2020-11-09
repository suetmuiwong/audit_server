/**

 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package com.et.auditServer.common.utils;

import com.et.auditServer.common.constant.Constant;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期处理
 * 
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2016年12月21日 下午12:53:33
 */
public class DateUtils {
	/** 时间格式(yyyy-MM-dd) */
	public final static String DEFAULT_DATE_PATTERN = "yyyy-MM-dd";
	/** 时间格式(yyyy-MM-dd HH:mm:ss) */
	public final static String DEFAULT_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    /** 时间格式(yyyy-MM-dd HH:mm:ss.SSS) */
    public final static String MILLISECOND_DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

    private static String[] parsePatterns = {
            "yyyy-MM-dd", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd HH:mm", "yyyy-MM",
            "yyyy/MM/dd", "yyyy/MM/dd HH:mm:ss", "yyyy/MM/dd HH:mm", "yyyy/MM",
            "yyyy.MM.dd", "yyyy.MM.dd HH:mm:ss", "yyyy.MM.dd HH:mm", "yyyy.MM",
            "yyyyMMdd", "yyyyMMddHHmmss"};

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @return  返回yyyy-MM-dd格式日期
     */
	public static String format(Date date) {
        return format(date, DEFAULT_DATE_PATTERN);
    }

    /**
     * 日期格式化 日期格式为：yyyy-MM-dd
     * @param date  日期
     * @param pattern  格式，如：DateUtils.DATE_TIME_PATTERN
     * @return  返回yyyy-MM-dd格式日期
     */
    public static String format(Date date, String pattern) {
        if(date != null){
            SimpleDateFormat df = new SimpleDateFormat(pattern);
            return df.format(date);
        }
        return null;
    }

    /**
     * 字符串转换成日期
     * @param strDate 日期字符串
     * @param pattern 日期的格式，如：DateUtils.DATE_TIME_PATTERN
     */
    public static Date stringToDate(String strDate, String pattern) {
        if (StringUtils.isBlank(strDate)){
            return null;
        }
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = null;
        try {
            date = sdf.parse(strDate);
        } catch (ParseException e) {

        }
        return date;
    }



    /**
     * 转换为时间（天,时:分:秒.毫秒）
     *
     * @param timeMillis
     * @return
     */
    public static String formatDateTime(long timeMillis) {
        long day = timeMillis / (24 * 60 * 60 * 1000);
        long hour = (timeMillis / (60 * 60 * 1000) - day * 24);
        long min = ((timeMillis / (60 * 1000)) - day * 24 * 60 - hour * 60);
        long s = (timeMillis / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
        long sss = (timeMillis - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000 - min * 60 * 1000
                - s * 1000);
        return (day > 0 ? day + "," : "") + hour + ":" + min + ":" + s + "." + sss;
    }


    public static String dataFormTransform(String dataStr, String oldForm, String newFrom) {
        SimpleDateFormat format = new SimpleDateFormat(oldForm);
        String newStr = "";
        try {
            Date date = format.parse(dataStr);
            format = new SimpleDateFormat(newFrom);
            newStr = format.format(date);
        } catch (ParseException e) {
            System.out.println("---1---" + e.getMessage());
        }
        return newStr;
    }

    /**
     * 获取当年的第一天
     * @return
     */
    public static Date getCurrYearFirst(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearFirst(currentYear);
    }

    /**
     * 获取当年的最后一天
     * @return
     */
    public static Date getCurrYearLast(){
        Calendar currCal=Calendar.getInstance();
        int currentYear = currCal.get(Calendar.YEAR);
        return getYearLast(currentYear);
    }

    /**
     * 获取某年第一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearFirst(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        Date currYearFirst = calendar.getTime();
        return currYearFirst;
    }

    /**
     * 获取某年最后一天日期
     * @param year 年份
     * @return Date
     */
    public static Date getYearLast(int year){
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(Calendar.YEAR, year);
        calendar.roll(Calendar.DAY_OF_YEAR, -1);
        Date currYearLast = calendar.getTime();

        return currYearLast;
    }

    /**
     * 获取本月第一天
     * */
    public static String getMonthFirstDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format(calendar.getTime());
        return  first + Constant.ZERO_POINT;
    }

    /**
     * 获取本月最后一天
     * */
    public static String getMonthLastDay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format(calendar.getTime());
        return  last + Constant.TWENTY_FOUR_POINTS;
    }

    /**
     * 获取上个月的第一天
     * */
    public static String getLastMonthFirstDay() {
        //获取前月的第一天
        Calendar   cal_1=Calendar.getInstance();//获取当前日期
        cal_1.add(Calendar.MONTH, -1);
        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String firstDay = format(cal_1.getTime());
        System.out.println("-----1------firstDay:"+firstDay);
        return firstDay + Constant.ZERO_POINT;
    }

    /**
     * 获取上个月最后一天
     */
    public static String getLastMonthLastDay() {
        //获取前月的最后一天
        Calendar cale = Calendar.getInstance();
        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
        String lastDay = format(cale.getTime());
        System.out.println("-----2------lastDay:"+lastDay);
        return lastDay + Constant.TWENTY_FOUR_POINTS;
    }

    /**
     * 获取今年第一天
     * */
    public static String getCurrYearFirstDay() {
        Calendar cale = Calendar.getInstance();
        cale.setTime(new Date());
        cale.set(Calendar.MONTH, Constant.ZERO);
        cale.set(Calendar.DAY_OF_MONTH, Constant.ONE);
        String firstDay = format(cale.getTime());
        System.out.println("-----1------firstDay:"+firstDay);
        return firstDay + Constant.ZERO_POINT;
    }

    /**
    * 获取明天
    * */
    public static  String getTomorrow() {
        Calendar cale = Calendar.getInstance();
        cale.setTime(new Date());
        cale.add(Calendar.DAY_OF_MONTH, Constant.ONE);
        String firstDay = format(cale.getTime());
        System.out.println("-----1------firstDay:"+firstDay);
        return firstDay + Constant.ZERO_POINT;
    }

    public static void main(String [] orgs) {
        System.out.println(getMonthFirstDay());
        System.out.println(getMonthLastDay());
        System.out.println(getLastMonthFirstDay());
        System.out.println(getLastMonthLastDay());
    }
}
