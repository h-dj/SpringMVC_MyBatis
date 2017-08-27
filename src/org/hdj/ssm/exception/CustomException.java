package org.hdj.ssm.exception;

/**
 * 自定义异常
 * @author H_DJ
 *
 */
public class CustomException  extends Exception{
	//异常消息
	private  String msg;

	public CustomException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
