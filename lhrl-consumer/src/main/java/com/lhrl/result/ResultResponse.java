package com.lhrl.result;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 返回的response
 * @author liu lang
 *
 * @param <T>
 * 2017年5月5日上午6:00:29
 */
public class ResultResponse<T>{
	
	protected static final String CODE_OK = "200";
    protected static final String MSG_OK = "OK";
    
	private String code;
	
	private String message;
	
	private T data;
	
	@JsonIgnore
	public  boolean isSuccess(){
		return "0".equals(code);
	}
	
	public ResultResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public ResultResponse(String code, String message, T data) {
		super();
		this.code = code;
		this.message = message;
		this.data = data;
	}

	
	public ResultResponse(String code, T data) {
		super();
		this.code = code;
		this.data = data;
	}
	
	public static <T> ResultResponse<T> buildSuccess(){
		return new ResultResponse<T>(CODE_OK, MSG_OK);
	}
	
	public static <T> ResultResponse<T> buildSuccess(T data){
		return new ResultResponse<T>(CODE_OK, MSG_OK, data);
	}
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	

}
