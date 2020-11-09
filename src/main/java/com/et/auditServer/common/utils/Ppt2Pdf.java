package com.et.auditServer.common.utils;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

import org.apache.poi.hslf.usermodel.HSLFSlide;
import org.apache.poi.hslf.usermodel.HSLFSlideShow;
import org.apache.poi.xslf.usermodel.XMLSlideShow;
import org.apache.poi.xslf.usermodel.XSLFSlide;

import com.itextpdf.text.Document;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import javax.servlet.http.HttpServletResponse;

public class Ppt2Pdf {


    public static void main(String[] args) throws Exception {

/*
        File ppt = new File("/Users/huangwenhui/Desktop/aa.ppt");
        File pdf = new File("/Users/huangwenhui/Desktop/bb.pdf");
        if (!pdf.exists()){
            pdf.createNewFile();

        }
        convertPPTToPDF(ppt,pdf);*/
    }

    public static boolean convertPPTToPDF(InputStream inputStream,String type, HttpServletResponse response) {
        HSLFSlideShow hslfSlideShow = null;
        XMLSlideShow ppt = null;
        Document pdfDocument = new Document();
        PdfWriter pdfWriter = null;
        try {

            pdfWriter = PdfWriter.getInstance(pdfDocument, response.getOutputStream());
            if ("PPTX".equals(type)) {
                int zoom = 2;
                ppt = convertPPTToPDFByPPTX(inputStream);
                if (ppt == null) {
                    throw new NullPointerException("This PPTX get data is error....");
                }
                Dimension pgsize = ppt.getPageSize();
                List<XSLFSlide> slide = ppt.getSlides();
                AffineTransform at = new AffineTransform();
                at.setToScale(zoom, zoom);
                pdfDocument.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
                pdfWriter.open();
                pdfDocument.open();
                PdfPTable table = new PdfPTable(1);
                for (XSLFSlide xslfSlide : slide) {
                    BufferedImage img = new BufferedImage(pgsize.width * zoom, pgsize.height * zoom, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = img.createGraphics();
                    graphics.setTransform(at);

                    graphics.setPaint(Color.white);

                    graphics.fill(new Rectangle2D.Float(0L, 0L, pgsize.width, pgsize.height));
                    xslfSlide.draw(graphics);
                    graphics.getPaint();
                    Image slideImage = Image.getInstance(img, null);
                    table.addCell(new PdfPCell(slideImage, true));
                }

                pdfDocument.add(table);

                pdfWriter.close();
                pdfDocument.close();
                ppt.close();

                return true;
            }
            if("PPT".equals(type)){
                int zoom = 4;
                hslfSlideShow = convertPPTToPDFByPPT(inputStream);

                assert hslfSlideShow != null;
                Dimension pgsize = hslfSlideShow.getPageSize();
                List<HSLFSlide> slides = hslfSlideShow.getSlides();
                pdfDocument.setPageSize(new Rectangle((float) pgsize.getWidth(), (float) pgsize.getHeight()));
                pdfWriter.open();
                pdfDocument.open();
                AffineTransform at = new AffineTransform();
                PdfPTable table = new PdfPTable(1);
                for (HSLFSlide hslfSlide : slides) {
                    BufferedImage img = new BufferedImage(pgsize.width * zoom, pgsize.height * zoom, BufferedImage.TYPE_INT_RGB);
                    Graphics2D graphics = img.createGraphics();
                    graphics.setTransform(at);

                    graphics.setPaint(Color.white);
                    graphics.scale(zoom, zoom);
                    graphics.fill(new Rectangle2D.Float(0L, 0L, pgsize.width * zoom, pgsize.height * zoom));
                    hslfSlide.draw(graphics);
                    graphics.getPaint();
                    Image slideImage = Image.getInstance(img, null);
                    table.addCell(new PdfPCell(slideImage, true));
                }

                pdfDocument.add(table);
                pdfWriter.close();
                pdfDocument.close();
                hslfSlideShow.close();
                return true;
            }


        } catch (Exception e) {
            return false;
        } finally {

        }
        return true;
    }

    private static XMLSlideShow convertPPTToPDFByPPTX(InputStream is) {
        try {
            return new XMLSlideShow(is);
        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }

    private static HSLFSlideShow convertPPTToPDFByPPT(InputStream is) {
        try {
            return new HSLFSlideShow(is);
        } catch (Exception e) {
            return null;
        }
    }

}
