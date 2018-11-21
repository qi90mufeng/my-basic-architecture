package com.my.base.util;

import java.util.Random;
import java.util.UUID;

public class RandomUtil {
	
	static char[] charArray = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '0', 
			'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'J', 'K', 'L', 'M', 'N', 
			'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 
			'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'm', 'n',
			 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z'};
	static Integer charArrayLength = charArray.length;
	
	/*************
	 * 生成随机的uuid
	 * @return
	 */
	public static String getUUID() {
		return UUID.randomUUID().toString();
	}
	
	/******************
	 * 生成指定长度的验证码
	 * @param length 验证码位数
	 * @param includeLetter 是否包括字母
	 * @return
	 */
	public static String getValidateCode(Integer length, Boolean includeLetter) {
		char[] chars = new char[length];
		Random random = new Random();
		for(int i=0; i<chars.length; i++){
			chars[i] = charArray[random.nextInt(charArrayLength)];
		}
		
		return new String(chars);
	}
	
	/*********
	 * 按约定规则生成指定系统指定模块的流水号
	 * @param sysCode 系统编码
	 * @param modelCode 模块编码
	 * @return
	 */
	public static String getSerialNo(String sysCode, String modelCode) {
		//TODO 生成流水号
		return null;
	}
	
	/***********
	 * 根据token获取真实的id
	 * @param type token类型
	 * @param token 
	 * @return
	 */
	public static String getIdFromToken(String type, String token) {
		//TODO 到redis里捞，需要根据不同type分别处理
		return null;
	}
	
	/**************
	 * 根据真实id按规则生成相应的token
	 * @param type
	 * @param token
	 * @return
	 */
	public static String getTokenFromId(String type, String token) {
		//TODO 按规则生成token，塞redis
		return null;
	}
}
