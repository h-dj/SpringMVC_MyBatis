package org.hdj.ssm.exception;

/**
 * �Զ����쳣
 * @author H_DJ
 *
 */
public class CustomException  extends Exception{
	//�쳣��Ϣ
	private  String msg;

	public CustomException(String msg) {
		super(msg);
		this.msg = msg;
	}
}
