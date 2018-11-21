package com.my.base.convert;

import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToBigDecimalConvert implements Converter<String, BigDecimal> {
	private static Logger logger = LoggerFactory.getLogger(StringToBigDecimalConvert.class);
	@Override
	public BigDecimal convert(String source) {
		try {
			if (source != null && !"".equals(source.trim())) {
				BigDecimal result = new BigDecimal(source);
				return result;
			}
		} catch (Exception e) {
			logger.error("转换异常", e);
		}
		return null;
	}

}
