package org.hdj.ssm.exception;
/**
 * �Զ����쳣
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
