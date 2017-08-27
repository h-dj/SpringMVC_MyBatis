package org.hdj.ssm.controller.convertor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 
 * @author H_DJ
 *Converter<String, Date>
 *ʵ�ֵĽӿ��еķ��Ͳ���
 *��һ������Ҫ��Ҫת���Ĳ���������ͬ
 *�ڶ�������Ҫ��ת����Ĳ���������ͬ
 */
public class CustomConverter implements Converter<String, Date>{

	@Override
	public Date convert(String source) {
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			return simpleDateFormat.parse(source);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
