package com.et.auditServer.common.utils;

import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import javax.servlet.http.HttpServletResponse;


/**
 * 文本文件转pdf并上传到fastDFS
 * 并返回相应的参数
 *
 * @author dxy
 */
public class TxtToPdf {

    public static void txtToPdf( InputStream is,HttpServletResponse response) throws Exception {
        Document document = new Document();
        PdfWriter.getInstance(document, response.getOutputStream());
        document.open();

        BaseFont bf = com.itextpdf.text.pdf.BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", com.itextpdf.text.pdf.BaseFont.EMBEDDED);
        Font font = new com.itextpdf.text.Font(bf);


        InputStreamReader isr = new InputStreamReader(is, "GBK");
        BufferedReader bufferedReader = new BufferedReader(isr);
        String str = "";
        while ((str = bufferedReader.readLine()) != null) {
            document.add(new Paragraph(str, font));
        }
        document.close();

    }
}