package com.my.base.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @（#）:FTPUtil.java
 * @description:FTP服务（文件上传、删除、下载）
 */
public class FTPUtil {
	private static final Logger LOGGER = LoggerFactory.getLogger(FTPUtil.class);
	/**
	 * ftp.host.name=192.168.30.158 ftp.host.port=21 ftp.user.name=Administrator
	 * ftp.user.password=Seattle ftp.path.name=/images/snatch/18/
	 * 此工具类先在搭建ftp环境后修改属性（暂时写死）
	 */
	private static final String hostName = "192.168.30.158";
	private static final int port = 21;
	private static final String userName = "Administrator";
	private static final String password = "Seattle";
	private static final String pathName = "";

	public static boolean uploadFile(String fileName, InputStream inputStream, String key) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		ftpClient.setControlEncoding("UTF-8");
		try {
			// 连接FTP服务器
			ftpClient.connect(hostName, port);
			// 登录FTP服务器
			ftpClient.login(userName, password);
			// 是否成功登录FTP服务器
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			ftpClient.setFileType(FTPClient.BINARY_FILE_TYPE);
			if (key != null) {
				createDirectory(ftpClient, key);
			} else if (StringUtils.isNotEmpty(pathName)) {
				createDirectory(ftpClient, pathName);
			}
			ftpClient.storeFile(fileName, inputStream);
			inputStream.close();
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOGGER.error("ftp上传文件失败", e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return flag;
	}

	public static boolean uploadFileFromProduction(String fileName, String originFileName) {
		InputStream inputStream = null;
		try {
			inputStream = new FileInputStream(new File(originFileName));
		} catch (Exception e) {
			LOGGER.error("ftp上传的参数文件地址错误", e);
			return false;
		}
		return uploadFile(fileName, inputStream, null);
	}

	public static boolean uploadFileFromProduction(String originFileName) {
		InputStream inputStream = null;
		String fileName = null;
		try {
			fileName = new File(originFileName).getName();
			inputStream = new FileInputStream(new File(originFileName));
		} catch (Exception e) {
			LOGGER.error("ftp上传的参数文件地址错误", e);
			return false;
		}
		return uploadFile(fileName, inputStream, null);
	}

	public static boolean deleteFile(String pathName, String fileName) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			ftpClient.connect(hostName, port);
			// 登录FTP服务器
			ftpClient.login(userName, password);
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// 切换FTP目录
			if (pathName != null)
				ftpClient.changeWorkingDirectory(pathName);
			ftpClient.dele(fileName);
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOGGER.error("ftp删除文件失败", e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}

	public static boolean downloadFile(String pathName, String fileName, String localPath) {
		boolean flag = false;
		FTPClient ftpClient = new FTPClient();
		try {
			// 连接FTP服务器
			ftpClient.connect(hostName, port);
			// 登录FTP服务器
			ftpClient.login(userName, password);
			// 验证FTP服务器是否登录成功
			int replyCode = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(replyCode)) {
				return flag;
			}
			// 切换FTP目录
			ftpClient.changeWorkingDirectory(pathName);
			FTPFile[] ftpFiles = ftpClient.listFiles();
			for (FTPFile file : ftpFiles) {
				if (fileName.equalsIgnoreCase(file.getName())) {
					File localFile = new File(localPath + File.pathSeparator + file.getName());
					OutputStream os = new FileOutputStream(localFile);
					ftpClient.retrieveFile(file.getName(), os);
					os.close();
				}
			}
			ftpClient.logout();
			flag = true;
		} catch (Exception e) {
			flag = false;
			LOGGER.error("ftp下载文件失败", e);
		} finally {
			if (ftpClient.isConnected()) {
				try {
					ftpClient.logout();
				} catch (IOException e) {

				}
			}
		}
		return flag;
	}

	/**
	 * 创建目录， /images/snatch/26/20160918095703.jpg
	 * 
	 * @param ftpClient
	 * @param path
	 * @return
	 * @throws IOException
	 */
	private static void createDirectory(FTPClient ftpClient, String path) throws IOException {
		if (path == null || ftpClient == null)
			return;
		int x = path.lastIndexOf("/");
		if (x > 0) {
			path = path.substring(path.startsWith("/") ? 1 : 0, x);
			String[] temp = path.split("/");
			int length = temp.length;
			String dir = "";
			for (int i = 0; i < length; i++) {
				dir = dir + temp[i] + "/";
				ftpClient.makeDirectory(dir);
			}
			ftpClient.changeWorkingDirectory(dir);
		}
	}
}
