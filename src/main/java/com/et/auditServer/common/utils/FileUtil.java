package com.et.auditServer.common.utils;

import com.et.auditServer.common.constant.Constant;
import com.et.auditServer.common.exception.BusinessException;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import static com.et.auditServer.common.utils.Word2Pdf.*;

public class FileUtil {



//    private static final Logger logger = LoggerFactory.getLogger(SynchronizationDataScheduledJob.class);

   /* public static String saveFile(MultipartFile multipartFile) throws IOException {
        String[] fileAbsolutePath = {};
        String fileName = multipartFile.getOriginalFilename();
        String ext = fileName.substring(fileName.lastIndexOf(".") + 1);
        byte[] file_buff = null;
        InputStream inputStream = multipartFile.getInputStream();
        try {
            if (inputStream != null) {
//                int len1 = 1000;
//                     len1 = inputStream.available();
                file_buff = new byte[100];
                inputStream.read(file_buff);
            }
        } catch (Exception e) {
            logger.error("error:" + e);
        } finally {
            if(inputStream!=null){
                inputStream.close();
            }
        }


        FastDFSFile file = new FastDFSFile(fileName, file_buff, ext);
        try {
            fileAbsolutePath = FastDFSClient.upload(file);  //upload to fastdfs
        } catch (Exception e) {
            System.out.println("upload file Exception!" + e.getMessage());
        }
        String path = "";
        if (fileAbsolutePath == null) {
            System.out.println("upload file failed,please upload again!");
        }
        try {
            if (fileAbsolutePath != null) {
                path = FastDFSClient.getTrackerUrl() + fileAbsolutePath[0] + Constant.LEFT_SLASH + fileAbsolutePath[1];
            }
        } catch (Exception e) {

        }

        return path;
    }*/

    public static void savePic(InputStream inputStream, String fileName, String path) {
        OutputStream os = null;
        try {
            // 2、保存到临时文件
            // 1K的数据缓冲
            byte[] bs = new byte[1024];
            // 读取到的数据长度
            int len;
            // 输出的文件流保存到本地文件
            File tempFile = new File(path);
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            os = new FileOutputStream(tempFile.getPath() + Constant.LEFT_SLASH + fileName);

            // 开始读取
            while ((len = inputStream.read(bs)) != -1) {
                os.write(bs, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 完毕，关闭所有链接
            try {
                if(os !=null){
                    os.close();
                }
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

    }

    public static void delFile(String fileUrl) {
        File file = new File(fileUrl);
        file.deleteOnExit();
    }

    /**
     * 从网络Url中下载文件
     *
     * @param urlStr
     * @param fileName
     * @param response
     * @throws IOException
     */
    public static void downLoadFromUrl(String urlStr, String fileName, HttpServletRequest request, HttpServletResponse response) throws IOException {
        URL url = new URL(urlStr);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        //设置超时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //防止屏蔽程序抓取而返回403错误
        //conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

        //得到输入流
        InputStream inputStream = conn.getInputStream();

        // 配置文件下载
        //获得浏览器代理信息
        final String userAgent = request.getHeader("USER-AGENT");
        //判断浏览器代理并分别设置响应给浏览器的编码格式
        if (userAgent.contains("msie") || userAgent.contains("like gecko") ||
                userAgent.contains("Trident")) {
            // win10 ie edge 浏览器 和其他系统的ie
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //解决下载时，空格变加号
            fileName = StringUtils.replace(fileName, "+", "%20");
        } else {
            // fe
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

            //解决下载时，空格变加号
            fileName = StringUtils.replace(fileName, "+", "%20");
        }
        //设置HTTP响应头
        response.addHeader("Content-Disposition", "attachment;filename=" + fileName);//下载文件的名称
        //application/octet-stream ： 二进制流数据（最常见的文件下载）。
        response.setContentType("");
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            while ((len = inputStream.read(bs)) != -1) {
                out.write(bs, 0, len);
            }
            System.out.println("Download the song successfully!");
        } catch (Exception e) {
            System.out.println("Download the song failed!");
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }


    /**
     * 打包ZIP下载,图片不是本地,而是URL路径
     *
     * @param fileNamelst 文件名称list
     * @param lstStr      文件URLlist
     * @param response
     * @throws Exception
     */
    public static void urldownloadZip(List<String> fileNamelst, List<Map<String,String>> lstStr,
                                      String zipFileName,
                                      HttpServletRequest request,
                                      HttpServletResponse response,
                                      Boolean isAll) throws Exception {
        fileNamelst.removeIf(StringUtil::isEmpty);
        lstStr.removeIf(
                item -> StringUtil.isEmpty(item.get("url"))
        );
        Map<String, Integer> has = new HashMap<>();
        for (int i = 0; i < fileNamelst.size(); i++) {
            if (has.get(fileNamelst.get(i)) != null) {
                String n = fileNamelst.get(i).substring(0, fileNamelst.get(i).lastIndexOf("."));
                String suffix = fileNamelst.get(i).substring(fileNamelst.get(i).lastIndexOf(".") + 1);
                has.put(fileNamelst.get(i), has.get(fileNamelst.get(i)) + 1);
                fileNamelst.set(i, n + has.get(fileNamelst.get(i)) + "." + suffix);
            } else {
                has.put(fileNamelst.get(i), 1);
            }
        }

        //ConfigConstants.ZIPNAME为项目中配置的URL路径
        String fileName = zipFileName + ".zip";//new String(ConfigConstants.ZIPNAME.getBytes("UTF-8"),"UTF-8");
        //响应头的设置
        response.reset();
        response.setContentType("application/force-download");
        //获得浏览器代理信息
        final String userAgent = request.getHeader("USER-AGENT");
        //判断浏览器代理并分别设置响应给浏览器的编码格式
        if (userAgent.contains("msie") || userAgent.contains("like gecko") ||
                userAgent.contains("Trident")) {
            // win10 ie edge 浏览器 和其他系统的ie
            fileName = URLEncoder.encode(fileName, "UTF-8");
            //解决下载时，空格变加号
            fileName = StringUtils.replace(fileName, "+", "%20");
        } else {
            // fe
            fileName = new String(fileName.getBytes("UTF-8"), "ISO8859-1");

            //解决下载时，空格变加号
            fileName = StringUtils.replace(fileName, "+", "%20");
        }
        response.addHeader("Content-Disposition", "attachment;fileName=" + fileName);
        response.setContentType("application/octet-stream;charset=utf-8");
        //设置压缩流：直接写入response，实现边压缩边下载
        ZipOutputStream zipos = null;
        zipos = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream()));
        //设置压缩方法
        zipos.setMethod(ZipOutputStream.DEFLATED);
        DataOutputStream os = null;
        try {
            //循环将文件写入压缩流
            for (int i = 0; i < lstStr.size(); i++) {
                URL url = new URL(lstStr.get(i).get("url"));
                URLConnection conn = url.openConnection();
                InputStream inputStream = conn.getInputStream();
                //添加ZipEntry，并ZipEntry中写入文件流
                if(isAll){
                    zipos.putNextEntry(new ZipEntry(zipFileName+"/"+lstStr.get(i).get("applyMainName")+"/"+fileNamelst.get(i)));
                }else{
                    zipos.putNextEntry(new ZipEntry(zipFileName+"/"+fileNamelst.get(i)));
                }
                os = new DataOutputStream(zipos);
                byte[] buff = new byte[1024 * 10];
                int len = 0;
                //循环读写
                while ((len = inputStream.read(buff)) > -1) {
                    os.write(buff, 0, len);
                }
                //关闭此文件流
                inputStream.close();
                //关闭当前ZIP项，并将流放置到写入的位置。下一个条目。
                zipos.closeEntry();
            }
            //释放资源
            if(os != null){
                os.flush();
                os.close();
            }
            zipos.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //释放资源
            if(os !=null){
                os.close();
            }
            zipos.close();
            //释放资源
            if(os != null){
                os.close();
            }
        }
    }


    /**
     * 下载项目下src/main/resources/download
     *
     * @param response response
     * @return 返回结果 成功或者文件不存在
     */
    public static String downloadFile(HttpServletResponse response, HttpServletRequest request, String osType,String version, Boolean isTest, boolean notIE) throws FileNotFoundException {


        String fileName = null;
        if (osType.equals("win")) {
            fileName = "簿记_Setup_"+version+".exe";
            if (isTest) {
                fileName = "簿记_Setup_"+version+"-test.exe";
            }
            fileName = "windows/"+fileName;
        } else if (osType.equals("mac")) {
            fileName = "簿记_Setup_"+version+".zip";
            if (isTest) {
                fileName = "簿记_Setup_"+version+"-test.zip";
            }
            fileName = "mac/"+fileName;
        }


        InputStream stream = FileUtil.class.getClassLoader().getResourceAsStream("static/download/" + fileName);
        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            String name = null;
            String agent = request.getHeader("USER-AGENT");
            if (null != agent && -1 != agent.indexOf("MSIE") || null != agent
                    && -1 != agent.indexOf("Trident") || null != agent && -1 != agent.indexOf("Edge")) {// ie浏览器及Edge浏览器
                name = java.net.URLEncoder.encode(fileName, "UTF-8");
            } else if (null != agent && -1 != agent.indexOf("Mozilla")) {// 火狐,Chrome等浏览器
                name = new String(fileName.getBytes("UTF-8"), "iso-8859-1");
            }

          /*  String name = java.net.URLEncoder.encode(fileName, "UTF-8");
            if (notIE) {
                name = java.net.URLDecoder.decode(name, "ISO-8859-1");
            }*/
            response.setHeader("Content-Disposition", "attachment;filename=" + name);
            response.setHeader("charset", "utf-8");
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            bis = new BufferedInputStream(stream);
            int len = 0;
            while ((len = bis.read(buff)) > 0) {
                os.write(buff, 0, len);
            }
            bis.close();
//            fis.close();
        } catch (FileNotFoundException e1) {
            //e1.getMessage()+"系统找不到指定的文件";
            return "系统找不到指定的文件";
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return "success";
    }

    public static String saveSingleFile(MultipartFile file) {

        try {
            String fileName = file.getOriginalFilename();
            String filePath = "/Users/huangwenhui/code/aguangrui/temp/";
            String fileFullPath = filePath + fileName;

            byte[] bytes = file.getBytes();
            Path path = Paths.get(fileFullPath);
            Files.write(path, bytes);

            return fileFullPath;
        } catch (IOException e) {
            throw new BusinessException("上传文件失败！", e);
        }
    }


    public static byte[] getFileByte(String filePath) {
        int HttpResult; // 服务器返回的状态
        byte[] bytes = new byte[1000];
        try {
            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                int filesize = urlconn.getContentLength(); // 取数据长度
                System.out.println("取数据长度====" + filesize);
                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();

                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                int ch;
                while ((ch = inputStream.read()) != -1) {
                    swapStream.write(ch);
                }
                bytes = swapStream.toByteArray();
            }
            System.out.println("文件大小====" + bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }

//
//        File file = new File(url);
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return JsonResult.success(bytes);
        return bytes;

    }

    public static void getPDFFile(String filePath, HttpServletResponse response) {
        int HttpResult; // 服务器返回的状态
//        byte[] bytes = new byte[1000];
        try {
            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                int filesize = urlconn.getContentLength(); // 取数据长度
                System.out.println("取数据长度====" + filesize);
                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();
                OutputStream os = response.getOutputStream();
//                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
                int ch;
                while ((ch = inputStream.read()) != -1) {
                    os.write(ch);
                }
//                bytes = os.toByteArray();
            }
//            System.out.println("文件大小===="+bytes.length);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public static void DOCX2Pdf(String filePath, HttpServletRequest request, HttpServletResponse response) {
        int HttpResult; // 服务器返回的状态
        try {
            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                String tempPath = getPath("pic_temp/");


                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();

//                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

                String docxHtml = docx2Html(inputStream, tempPath);
                docxHtml = formatHtml(docxHtml);
                htmlToPdf(docxHtml, response);

//                htmlToPdf1(docxHtml,getPath()+"/aaa.pdf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void DOC2Pdf(String filePath, HttpServletRequest request, HttpServletResponse response) {
        int HttpResult; // 服务器返回的状态
        try {
            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {
                String tempPath = getPath("pic_temp/");

                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();

//                ByteArrayOutputStream swapStream = new ByteArrayOutputStream();

                String docHtml = doc2Html(inputStream, tempPath);
                docHtml = formatHtml(docHtml);
                htmlToPdf(docHtml, response);

//                htmlToPdf1(docxHtml,getPath()+"/aaa.pdf");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PPT2Pdf(String filePath,String type, HttpServletRequest request, HttpServletResponse response) {
        int HttpResult; // 服务器返回的状态
        try {
            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {

                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();

                Ppt2Pdf.convertPPTToPDF(inputStream,type,response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void TXT2Pdf(String filePath, HttpServletRequest request, HttpServletResponse response) {
        int HttpResult; // 服务器返回的状态
        try {
            URL url = new URL(filePath); // 创建URL
            URLConnection urlconn = url.openConnection(); // 试图连接并取得返回状态码
            urlconn.connect();
            HttpURLConnection httpconn = (HttpURLConnection) urlconn;
            HttpResult = httpconn.getResponseCode();
            if (HttpResult != HttpURLConnection.HTTP_OK) {
                System.out.print("无法连接到");
            } else {

                urlconn.getInputStream();
                InputStream inputStream = urlconn.getInputStream();

                TxtToPdf.txtToPdf(inputStream,response);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取临时文件路径
     *
     * @return
     */
    public static String getPath(String dir) {
        Properties properties = System.getProperties();
        String path = properties.getProperty("user.dir");
        if (properties.getProperty("os.name").toLowerCase().contains("win")) {
            path += "\\";
        } else {
            path += "/";
        }
        return path+dir;
    }

    public static void writerPdfToResponse(String text, HttpServletResponse response) {
        com.itextpdf.text.Document document = null;

        try {
            document = new com.itextpdf.text.Document(PageSize.A4);
            // 笔
            PdfWriter writer = PdfWriter.getInstance(document, response.getOutputStream());
            document.open();
            Font font = null;
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.EMBEDDED);
            font = new Font(bf);

            document.add(new Paragraph(text,font));
            document.close();
        } catch (DocumentException | IOException e) {
            e.printStackTrace();
        }

    }

    //删除文件夹
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); //删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            java.io.File myFilePath = new java.io.File(filePath);
            myFilePath.deleteOnExit(); //删除空文件夹
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除指定文件夹下的所有文件
    public static boolean delAllFile(String path) {
        boolean flag = false;
        File file = new File(path);
        if (!file.exists()) {
            return flag;
        }
        if (!file.isDirectory()) {
            return flag;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < Objects.requireNonNull(tempList).length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.deleteOnExit();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);//先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);//再删除空文件夹
                flag = true;
            }
        }
        return flag;
    }


}
