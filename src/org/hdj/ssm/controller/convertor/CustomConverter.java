package org.hdj.ssm.controller.convertor;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;
/**
 * 
 * @author H_DJ
 *Converter<String, Date>
 *实现的接口中的泛型参数
 *第一个参数要与要转换的参数类型相同
 *第二个参数要与转换后的参数类型相同
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
