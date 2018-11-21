package com.my.base.convert;

import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.time.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class DateConvert implements Converter<String, Date>{
	private static Logger logger = LoggerFactory.getLogger(DateConvert.class);
	@Override
	public Date convert(String source) {
		if (source!=null && source.length()>0) {
			try {
				return DateUtils.parseDate(source, com.my.base.util.DateUtils.DATE_TIME_FORMAT, com.my.base.util.DateUtils.DATA_FORMAT_PATTERN_0);
			} catch (ParseException e) {
				logger.error("日期转换异常");
			}
		}
		return null;
	}
}
