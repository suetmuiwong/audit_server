package com.et.auditServer.common.utils;

import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExcelUtil {

    /**
     * 生成普通excel表
     * @param headers 表头，格式如下
     *         header.put("label","显示值")
     *         header.put("prop","对应的数据key值")
     *         header.put("width",20)
     * @param dataList 数据集
     * @param outputStream 输出流
     * @throws Exception 异常
     */
    public static void generateExcel(List<Map<String,Object>> headers,List<Map<String,Object>> dataList,OutputStream outputStream) throws Exception {
        //声明一个工作簿
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("Sheet1");
        //设置表格列宽度为10个字节
        sheet.setDefaultColumnWidth(15);
        //创建标题的显示样式
        HSSFCellStyle headerStyle = workbook.createCellStyle();
        headerStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.LIGHT_CORNFLOWER_BLUE.getIndex());
        headerStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerStyle.setFillBackgroundColor(HSSFColor.HSSFColorPredefined.YELLOW.getIndex());
        headerStyle.setAlignment(HorizontalAlignment.CENTER);//水平居中
        headerStyle.setVerticalAlignment(VerticalAlignment.CENTER);//垂直居中
        HSSFFont font = workbook.createFont();
        font.setFontHeightInPoints((short) 12);
        font.setColor(HSSFColor.HSSFColorPredefined.GREY_80_PERCENT.getIndex());
        font.setBold(true);
        font.setFontName("宋体");
        // 把字体 应用到当前样式
        headerStyle.setFont(font);
        //创建第一行表头
        HSSFRow headRow = sheet.createRow(0);
        //遍历添加表头
        for (int i = 0; i < headers.size(); i++) {
            Map<String, Object> objectMap = headers.get(i);
            String label = objectMap.get("label").toString();
            //创建一个单元格
            HSSFCell cell = headRow.createCell(i);
            //创建一个内容对象
            HSSFRichTextString text = new HSSFRichTextString(label);
            //将内容对象的文字内容写入到单元格中
            cell.setCellValue(text);
            cell.setCellStyle(headerStyle);
            if (objectMap.get("width")!=null){
                sheet.setColumnWidth(i, 256*Integer.parseInt(objectMap.get("width").toString())+184);
            }
        }
        //插入数据
        for(int i=0;i<dataList.size();i++){
            HSSFRow row = sheet.createRow(i+1);
            for(int j=0;j<headers.size();j++){
                String key = headers.get(j).get("prop").toString();
                Map<String, Object> objectMap = dataList.get(i);
                HSSFCell cell = row.createCell(j);
                if (objectMap.get(key)!=null){
                    cell.setCellValue(new HSSFRichTextString(objectMap.get(key).toString()));
                }else {
                    cell.setCellValue("");
                }

            }
        }
        workbook.write(outputStream);
    }

    public static void main(String[] args) throws Exception{
        OutputStream outputStream = new FileOutputStream("E://test.xlsx");
        List<Map<String,Object>> headers = new ArrayList<>();
        Map<String,Object> header1 = new HashMap<>();
        header1.put("label","名称");
        header1.put("prop","projName");
        Map<String,Object> header2 = new HashMap<>();
        header2.put("label","类型");
        header2.put("prop","investTypeName");
        Map<String,Object> header3 = new HashMap<>();
        header3.put("label","名称");
        header3.put("prop","investName");
        Map<String,Object> header4 = new HashMap<>();
        header4.put("label","有效申购总数");
        header4.put("prop","bidQtySum");
        Map<String,Object> header5 = new HashMap<>();
        header5.put("label","总获配量");
        header5.put("prop","winInterestQtySum");
        Map<String,Object> header6 = new HashMap<>();
        header6.put("label","期次");
        header6.put("prop","stageName");
        Map<String,Object> header7 = new HashMap<>();
        header7.put("label","机构有效申购总数");
        header7.put("prop","bidQty");
        header7.put("width",20);
        Map<String,Object> header8 = new HashMap<>();
        header8.put("label","机构有效总获配量");
        header8.put("prop","winInterestQty");
        header8.put("width",20);
        headers.add(header1);
        headers.add(header2);
        headers.add(header3);
        headers.add(header4);
        headers.add(header5);
        headers.add(header6);
        headers.add(header7);
        headers.add(header8);
        List<Map<String,Object>> dataList = new ArrayList<>();
        Map<String,Object> dataMap = new HashMap<>();
        dataMap.put("projName","项目名称A");
        dataMap.put("investTypeName","类型A");
        dataMap.put("investName","名称A");
        dataMap.put("bidQtySum","100");
        dataMap.put("winInterestQtySum","1000");
        dataMap.put("stageName","次A");
        dataMap.put("bidQty","100");
        dataMap.put("winInterestQty","1000");
        dataList.add(dataMap);
        ExcelUtil.generateExcel(headers,dataList,outputStream);
    }

}
