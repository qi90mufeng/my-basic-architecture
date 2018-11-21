package com.my.base.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import jcifs.smb.SmbFileOutputStream;

/**
 * 注意jcifs-1.3.17.jar 需要 远程计算机在局域网内，并且开启共享磁盘权限！
 * @version $Id: RemoteFileUtils.java, v 0.1 2017年9月2日 上午10:27:59 longyuxiang Exp $
 */
public class RemoteFileUtils {

    /**
     *  Description: 从本地上传文件到共享目录
     *  @param remoteUrl 共享文件目录
     *  @param localFile 本地文件绝对路径
     * @throws Exception 
     */
    public static void smbPut(String remoteUrl, String localFile) throws Exception {
        InputStream in = null;
        OutputStream out = null;
        //创建file类 传入本地文件路径
        File file = new File(localFile);
        //获得本地文件的名字
        String fileName = file.getName();
        //将本地文件的名字和远程目录的名字拼接在一起
        //确保上传后的文件于本地文件名字相同
        //如远程计算机有用户名和密码的限制的话 请按一下格式填写 smb://{user}:{password}@{host}/{path}
        SmbFile remoteFile = new SmbFile(remoteUrl + "/" + fileName);
        //创建读取缓冲流把本地的文件与程序连接在一起
        in = new BufferedInputStream(new FileInputStream(localFile));
        //创建一个写出缓冲流(注意jcifs-1.3.17.jar包 类名为Smb开头的类为控制远程共享计算机"io"包)
        //将远程的文件路径传入SmbFileOutputStream中 并用 缓冲流套接
        out = new BufferedOutputStream(new SmbFileOutputStream(remoteFile));
        //创建中转字节数组
        byte[] buffer = new byte[1024];
        while (in.read(buffer) != -1) {//in对象的read方法返回-1为 文件以读取完毕
            out.write(buffer);
            buffer = new byte[1024];
        }
        out.close();
        in.close();
    }

    /**  
     * 对远程共享文件进行读取 到本地目录
     * @param filepath
     * @param fileName
     * @param remoteFile  共享远程文件  说明：参数为共享目录下的相对路径  
     *  若远程文件的路径为：shareDoc\test.txt,则参数为test.txt(其中shareDoc为共享目录名称);  
     *  若远程文件的路径为：shareDoc\doc\text.txt,则参数为doc\text.txt;  
     * @return  文件的所有行  
     * @throws Exception 
     */
    public static void smbGet(String filepath, String fileName,
                              String remoteFile) throws Exception {
        SmbFile smbFile = null;
        BufferedReader reader = null;
        //构建连接字符串,并取得文件连接   
        //如远程计算机有用户名和密码的限制的话 请按一下格式填写 smb://{user}:{password}@{host}/{path}
        smbFile = new SmbFile(remoteFile);
        //创建reader   
        reader = new BufferedReader(new InputStreamReader(new SmbFileInputStream(smbFile)));
        String result = "";
        String read = "";
        while ((read = reader.readLine()) != null) {
            result = result + read + "\n";
        }
        reader.close();
        FileUtils.write(filepath, fileName, result.getBytes());
    }

    /**
     *  在本地为共享计算机创建文件夹
     * @param remoteUrl 远程计算机路径
     * @throws Exception 
    */
    public static void smbMkDir(String remoteUrl) throws Exception {
        //注意使用jcifs-1.3.17.jar的时候 操作远程计算机的时候所有类前面须要增加Smb
        //创建一个远程文件对象
        SmbFile remoteFile = new SmbFile(remoteUrl);

        if (!remoteFile.exists()) {
            //创建远程文件夹
            remoteFile.mkdir();
        }
    }

    /**
     * @param args
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        FileUtils.read("\\\\PC-20170529PIVB/share/", "withhold-middleware-dev.properties");
        
        RemoteFileUtils.smbGet("D:\\", "withhold-middleware.properties",
            "smb://PC-20170529PIVB/share/withhold-middleware-dev.properties");
        //注意： 创建远程文件的远程文件路径需要按以下格式写。 如我的ip为172.16.50.38 我需要在d盘创建一个叫Scan6C的文件夹
        RemoteFileUtils.smbMkDir("smb://PC-20170529PIVB/share/Scan62C");
        //如远程计算机有用户名和密码的限制的话 请按一下格式填写 smb://{user}:{password}@{host}/{path}
        RemoteFileUtils.smbPut("smb://PC-20170529PIVB/share/Scan62C",
            "d://withhold-middleware-dev.properties");
    }

}
