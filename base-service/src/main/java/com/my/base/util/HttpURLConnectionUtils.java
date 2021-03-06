/**
 * 
 */
package com.my.base.util;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * 读服务器文件
 * 
 *
 */
public class HttpURLConnectionUtils {
    public static String getHttpFile(String urlget) throws IOException {
        String result = "";
        URL url = new URL(urlget);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.connect();
        if (200 == httpURLConnection.getResponseCode()) {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
            String read;
            while ((read = br.readLine()) != null) {
                result = result + read + "\n";
            }
            br.close();
        }
        return result;
    }

    /**
     * 远程获取文件到本地目录
     * 
     * @param filepath
     * @param fileName
     * @param urlget
     * @throws Exception 
     */
    public static void getHttpFile(String filepath, String fileName,
                                   String urlget) throws Exception {
        URL url = new URL(urlget);
        HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setDoInput(true);
        httpURLConnection.setRequestMethod("GET");
        httpURLConnection.setConnectTimeout(5000);
        httpURLConnection.setReadTimeout(5000);
        httpURLConnection.connect();
        if (200 == httpURLConnection.getResponseCode()) {
            BufferedReader br = new BufferedReader(
                new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
            String result = "";
            String read = "";
            while ((read = br.readLine()) != null) {
                result = result + read + "\n";
            }

            br.close();
            FileUtils.write(filepath, fileName, result.getBytes());
        }
    }

    /**
     * 多文件上传的方法
     * 
     * @param actionUrl：上传的路径
     * @param uploadFilePaths：需要上传的文件路径，数组
     * @return
     */
    @SuppressWarnings("finally")
    public static String uploadFile(String actionUrl, String[] uploadFilePaths) {
        String end = "\r\n";
        String twoHyphens = "--";
        String boundary = "*****";

        DataOutputStream ds = null;
        InputStream inputStream = null;
        InputStreamReader inputStreamReader = null;
        BufferedReader reader = null;
        StringBuffer resultBuffer = new StringBuffer();
        String tempLine = null;

        try {
            // 统一资源
            URL url = new URL(actionUrl);
            // 连接类的父类，抽象类
            URLConnection urlConnection = url.openConnection();
            // http的连接类
            HttpURLConnection httpURLConnection = (HttpURLConnection) urlConnection;

            // 设置是否从httpUrlConnection读入，默认情况下是true;
            httpURLConnection.setDoInput(true);
            // 设置是否向httpUrlConnection输出
            httpURLConnection.setDoOutput(true);
            // Post 请求不能使用缓存
            httpURLConnection.setUseCaches(false);
            // 设定请求的方法，默认是GET
            httpURLConnection.setRequestMethod("POST");
            // 设置字符编码连接参数
            httpURLConnection.setRequestProperty("Connection", "Keep-Alive");
            // 设置字符编码
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            // 设置请求内容类型
            httpURLConnection.setRequestProperty("Content-Type", "multipart/form-data;boundary=" + boundary);

            // 设置DataOutputStream
            ds = new DataOutputStream(httpURLConnection.getOutputStream());
            for (int i = 0; i < uploadFilePaths.length; i++) {
                String uploadFile = uploadFilePaths[i];
                String filename = uploadFile.substring(uploadFile.lastIndexOf("//") + 1);
                ds.writeBytes(twoHyphens + boundary + end);
                ds.writeBytes("Content-Disposition: form-data; " + "name=\"file" + i + "\";filename=\"" + filename
                        + "\"" + end);
                ds.writeBytes(end);
                FileInputStream fStream = new FileInputStream(uploadFile);
                int bufferSize = 1024;
                byte[] buffer = new byte[bufferSize];
                int length = -1;
                while ((length = fStream.read(buffer)) != -1) {
                    ds.write(buffer, 0, length);
                }
                ds.writeBytes(end);
                /* close streams */
                fStream.close();
            }
            ds.writeBytes(twoHyphens + boundary + twoHyphens + end);
            /* close streams */
            ds.flush();
            if (httpURLConnection.getResponseCode() >= 300) {
                throw new Exception(
                        "HTTP Request is not success, Response code is " + httpURLConnection.getResponseCode());
            }

            if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = httpURLConnection.getInputStream();
                inputStreamReader = new InputStreamReader(inputStream);
                reader = new BufferedReader(inputStreamReader);
                tempLine = null;
                resultBuffer = new StringBuffer();
                while ((tempLine = reader.readLine()) != null) {
                    resultBuffer.append(tempLine);
                    resultBuffer.append("\n");
                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (ds != null) {
                try {
                    ds.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStreamReader != null) {
                try {
                    inputStreamReader.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }

            return resultBuffer.toString();
        }
    }

    public static void main(String[] args) throws Exception {
        // 上传文件测试
        String str = uploadFile("http://10.20.2.11:7000/withhold-middleware-dev1.properties",new String[] { "D:\\withhold-middleware-dev.properties"});
        System.out.println(str);

        HttpURLConnectionUtils.getHttpFile("D:\\", "withhold-middleware-dev.properties",
            "http://10.20.2.11:7000/withhold-middleware-dev.properties");
    }
}
