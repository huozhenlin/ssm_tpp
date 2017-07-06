package com.cloudsoft.dto;

public class MsgResult {

	private String msg;
	private Object data;
	private boolean success;
	
	
	
	public MsgResult() {
		super();
	}
	
	
	public MsgResult(String msg, Object data, boolean success) {
		super();
		this.msg = msg;
		this.data = data;
		this.success = success;
	}


	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	public boolean isSuccess() {
		return success;
	}
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	
	
	
}
