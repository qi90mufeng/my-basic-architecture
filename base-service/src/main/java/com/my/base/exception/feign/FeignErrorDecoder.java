package com.my.base.exception.feign;

import java.io.IOException;

import com.my.base.dto.ResultDTO;
import com.my.base.exception.BizException;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;

import feign.Response;
import feign.codec.ErrorDecoder.Default;

@Component
public class FeignErrorDecoder extends Default {
	private static Logger logger = LoggerFactory.getLogger(FeignErrorDecoder.class);
	@Override
	public Exception decode(String methodKey, Response response) {
		logger.info("<<--------------- [methodKey:{}] [resp:{}]", methodKey, response);
		if ( response.status() == ResultDTO.ERROR_HTTP_STATUS ) {
			try {
				JSONObject json = JSONObject.parseObject(IOUtils.toString(response.body().asInputStream()));
				if ( json.containsKey("ok") && json.containsKey("code") ) {
					ResultDTO<?> result = json.toJavaObject(ResultDTO.class);
					return new BizException(result);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return super.decode(methodKey, response);
	}

}
