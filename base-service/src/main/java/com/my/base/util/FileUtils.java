package com.my.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.UUID;

import com.my.base.exception.BizException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUtils {

    private static Logger logger         = LoggerFactory.getLogger(FileUtils.class);

    public static String  uploadFileRoot = "";

    /**
     * 构建文件路径
     * @param relatedObj
     * 关联对象 代理商，商户，门店，销售经理，店员
     * @param souceFileName
     * 文件名称 如 aa.jpg
     * @return
     * @create_time 2016年7月14日
     */
    public static String buildFilePath(String relatedObj, String souceFileName) {
        StringBuilder sBuilder = new StringBuilder();
        if (StringUtils.isBlank(relatedObj)) {
            relatedObj = "00000";
        }
        Calendar calendar = Calendar.getInstance();
        sBuilder.append(relatedObj);
        sBuilder.append(File.separator);
        sBuilder.append(calendar.get(Calendar.YEAR));
        sBuilder.append(File.separator);
        sBuilder.append(calendar.get(Calendar.MONTH) + 1);
        sBuilder.append(File.separator);
        sBuilder.append(calendar.get(Calendar.DATE));
        sBuilder.append(File.separator);
        if (souceFileName.indexOf(".") == -1) {
            souceFileName = souceFileName + ".jpg";
        }
        sBuilder.append(souceFileName.replaceAll("(.+)(?=\\..*)", UUID.randomUUID().toString()));
        return sBuilder.toString();
    }

    /**
     * 构建文件路径
     * @param relatedObj
     * 关联对象 代理商，商户，门店，销售经理，店员
     * @param souceFileName
     * 文件名称 如 aa.jpg
     * @return
     * @create_time 2016年7月14日
     */
    public static String buildFilePath2(String relatedObj, String souceFileName) {
        StringBuilder sBuilder = new StringBuilder();
        if (StringUtils.isBlank(relatedObj)) {
            relatedObj = "00000";
        }
        Calendar calendar = Calendar.getInstance();
        sBuilder.append(relatedObj);
        sBuilder.append(File.separator);
        sBuilder.append(calendar.get(Calendar.YEAR));
        sBuilder.append(File.separator);
        sBuilder.append(calendar.get(Calendar.MONTH) + 1);
        sBuilder.append(File.separator);
        sBuilder.append(calendar.get(Calendar.DATE));
        sBuilder.append(File.separator);
        sBuilder.append(souceFileName);
        return sBuilder.toString();
    }

    /**
     * 文件上传
     * @param is 
     * 文件输入流
     * @param filePath
     * 前置路径 + FileUtils.buildFilePath方法生成
     * @return
     * @create_time 2016年7月14日 
     */
    public static boolean uploadFile(InputStream is, String filePath) {
        boolean retCode = false;
        byte[] buffer = new byte[1024];
        FileOutputStream fos = null;
        try {
            File file = new File(filePath.substring(0, filePath.lastIndexOf(File.separator)));
            if (!file.exists()) {
                file.mkdirs();
            }
            fos = new FileOutputStream(new File(filePath));
            int n = -1;
            while ((n = is.read(buffer, 0, buffer.length)) != -1) {
                fos.write(buffer, 0, n);
            }
            fos.flush();
            retCode = true;
            logger.info("文件上传成功：{}", filePath);
        } catch (Exception e) {
            logger.error(String.format("文件上传失败:%s", new Object[] { filePath }), e);
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                    fos = null;
                } catch (IOException e) {
                    logger.error(String.format("文件上传关闭流失败:%s", new Object[] { filePath }), e);
                }
            }
            if (is != null) {
                try {
                    is.close();
                    is = null;
                } catch (IOException e) {
                    logger.error(String.format("文件上传关闭流失败:%s", new Object[] { filePath }), e);
                }
            }
        }
        return retCode;
    }

    public static String uploadFile(MultipartFile file, String uploadFileRoot, String relatedObj) {
        try {
            String fileUploadPath = buildFilePath(relatedObj, file.getOriginalFilename());
            boolean uploadFlag = uploadFile(file.getInputStream(), uploadFileRoot + fileUploadPath);

            if (uploadFlag) {
                return uploadFileRoot + fileUploadPath;
            }
        } catch (IOException e) {
            logger.error("文件上传失败", e.toString());
            throw new BizException("", "文件上传失败");
        }
        return "";
    }

    public static void write(String filepath, String fileName, byte[] data) throws Exception {
        FileOutputStream fos = null;
        try {
            File folder = new File(filepath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            File file = new File(folder, fileName);
            fos = new FileOutputStream(file);
            fos.write(data);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }

    }

    public static byte[] read(String filepath, String filename) throws Exception {
        byte[] ret = null;
        File file = new File(new File(filepath), filename);
        if (!file.exists())
            throw new Exception();
        long fileLen = file.length();
        ret = new byte[(int) fileLen];
        long numRead = 0;
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(file);
            numRead = fis.read(ret, 0, (int) fileLen);
        } catch (IOException e) {
            logger.error(e.getMessage());
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }
        if (numRead != fileLen) {
            throw new Exception("读取文件出错");
        }
        return ret;
    }

    /* public static void main(String[] args) {
        System.out.println(buildFilePath(FileUtils.related_sales, "stored"));
    
        File file = new File("d:\\testupload\\00000\\20160714\\ddd");
        if (!file.exists()) {
            file.mkdirs();
        }
    }
    */
}
