package com.my.base.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.apache.commons.io.IOUtils;

import com.caucho.hessian.io.HessianSerializerInput;
import com.caucho.hessian.io.HessianSerializerOutput;
import com.my.base.exception.BizException;

/**
 * hessian序列化&反序列化
 * 
 */
public abstract class HessianUtils {

	/**
	 * hessian序列化
	 * 
	 * @param object
	 * @return
	 */
	public static <T> byte[] serialize(T object) {
		if (object == null) {
			throw new NullPointerException();
		}
		byte[] results = null;
		ByteArrayOutputStream os = null;
		HessianSerializerOutput hessianOutput = null;
		try {
			os = new ByteArrayOutputStream();
			hessianOutput = new HessianSerializerOutput(os);
			// write本身是线程安全的
			hessianOutput.writeObject(object);
			hessianOutput.flush();
			results = os.toByteArray();
		} catch (Exception e) {
			throw new BizException(e);
		} finally {
			IOUtils.closeQuietly(os);
		}
		return results;
	}

	/**
	 * 纯hessian反序列化
	 * 
	 * @param bytes
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static <T> T deserialize(byte[] bytes) {
		if(bytes == null || bytes.length == 0)
			return null;
		T result = null;
		ByteArrayInputStream is = null;
		try {
			is = new ByteArrayInputStream(bytes);
			HessianSerializerInput hessianInput = new HessianSerializerInput(is);
			result = (T) hessianInput.readObject();

		} catch (Exception e) {
			throw new BizException(e);
		} finally {
			IOUtils.closeQuietly(is);
		}
		return result;

	}
	
}
