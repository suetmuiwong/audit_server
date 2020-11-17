package com.et.auditServer.common.utils;

//import org.csource.common.MyException;
//import org.csource.common.NameValuePair;
//import org.csource.fastdfs.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.security.NoSuchAlgorithmException;

public class FastDFSClient {
/**
    private static final Logger logger = LoggerFactory.getLogger(FastDFSClient.class);
    private static TrackerClient trackerClient;
    private static TrackerServer trackerServer;
    private static StorageServer storageServer;
    private static StorageClient storageClient;

    public static String getTrackerUrl() throws IOException {
        return null;//Constant.HTTP + trackerServer.getInetSocketAddress().getHostString() + Constant.COLON  + ClientGlobal.getG_tracker_http_port() + Constant.LEFT_SLASH;
    }

    public static String getSecretKey() {
        return ClientGlobal.getG_secret_key();
    }

    /*static {
        try {
            ClientGlobal.initByProperties("fastdfs-client.properties");
            //logger.info("ClientGlobal.configInfo(): " + ClientGlobal.configInfo());
            trackerClient = new TrackerClient();
            trackerServer = trackerClient.getConnection();
            storageServer = trackerClient.getStoreStorage(trackerServer);
        } catch (Exception e) {
            logger.error("FastDFS Client Init Fail!",e);
        }
    }*/
/**
    //上传
    public static String[] upload(FastDFSFile file) {
        logger.info("File Name: " + file.getName() + "File Length:" + file.getContent().length);

        NameValuePair[] meta_list = new NameValuePair[1];
        meta_list[0] = new NameValuePair("author", file.getAuthor());

        long startTime = System.currentTimeMillis();
        String[] uploadResults = null;
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            uploadResults = storageClient.upload_file(file.getContent(), file.getExt(), meta_list);
        } catch (IOException e) {
            logger.error("IO Exception when uploadind the file:" + file.getName(), e);
        } catch (Exception e) {
            logger.error("Non IO Exception when uploadind the file:" + file.getName(), e);
        }
        logger.info("upload_file time used:" + (System.currentTimeMillis() - startTime) + " ms");

        if (uploadResults!=null){
            String groupName = uploadResults[0];
            String remoteFileName = uploadResults[1];
            logger.info("upload file successfully!!!" + "group_name:" + groupName + ", remoteFileName:" + " " + remoteFileName);
        }else{
            logger.error("upload file fail, error code:" + storageClient.getErrorCode());
        }

        return uploadResults;
    }

    //获取文件
    public static FileInfo getFile(String groupName, String remoteFileName) {
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            return storageClient.get_file_info(groupName, remoteFileName);
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    //下载文件
    public static InputStream downFile(String groupName, String remoteFileName) {
        try {
            storageClient = new StorageClient(trackerServer, storageServer);
            byte[] fileByte = storageClient.download_file(groupName, remoteFileName);
            InputStream ins = new ByteArrayInputStream(fileByte);
            return ins;
        } catch (IOException e) {
            logger.error("IO Exception: Get File from Fast DFS failed", e);
        } catch (Exception e) {
            logger.error("Non IO Exception: Get File from Fast DFS failed", e);
        }
        return null;
    }

    //删除文件
    public static void deleteFile(String groupName, String remoteFileName)
            throws Exception {
        storageClient = new StorageClient(trackerServer, storageServer);
        int i = storageClient.delete_file(groupName, remoteFileName);
        logger.info("delete file successfully!!!" + i);
    }

    /**
     2      * 获取访问服务器的token，拼接到地址后面
     3      *
     4      * @param filepath 文件路径 group1/M00/00/00/wKgzgFnkTPyAIAUGAAEoRmXZPp876.jpeg
     5      * @param httpSecretKey 密钥
     6      * @return 返回token，如： token=078d370098b03e9020b82c829c205e1f&ts=1508141521
     7      */
/**
      public static String getToken(String filepath, String httpSecretKey){
           // unix seconds
           int ts = (int) (System.currentTimeMillis() / 1000);
           // token
           String token = "null";
           try {
               token = ProtoCommon.getToken(filepath, ts, httpSecretKey);
           } catch (UnsupportedEncodingException e) {
               e.printStackTrace();
           } catch (NoSuchAlgorithmException e) {
               e.printStackTrace();
           } catch (MyException e) {
               e.printStackTrace();
           }

           StringBuilder sb = new StringBuilder();
           sb.append("token=").append(token);
           sb.append("&ts=").append(ts);

           return sb.toString();
      }
*/

}
