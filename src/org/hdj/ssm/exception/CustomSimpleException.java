package org.hdj.ssm.exception;
/**
 * 自定义异常
 * @author H_DJ
 *
 */
public class CustomSimpleException extends Exception{
	private String msg;
	public CustomSimpleException(String msg) {
		super();
		this.msg = msg;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
}
