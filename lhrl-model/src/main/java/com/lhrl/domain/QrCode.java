package com.lhrl.domain;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * url编码解码
 * @author liu lang
 *
 */
public class QrCode implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6233881170219358206L;
	
	private static final String BASE_CODE="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
	
	/**
	 * encode后的编码
	 */
	@JsonIgnore
	private String code;
	
	/**
	 * 类型
	 */
	private QrCodeType qrCodeType;
	
	/**
	 * 操作
	 */
	private String action;
	
	private final Map<String, String> params;

	public QrCode(String type,String action,String urlParams){
		this(type, action, parseQString(urlParams));
	}
	public QrCode(String type, String action,
			Map<String, String> params) {
		this.qrCodeType = QrCodeType.valueOf(type);
		this.action = action;
		this.params = params;
		this.code=createCode(this.qrCodeType.getCodePrefix());
	}
	
	public void initCode(){
		this.code=createCode(this.qrCodeType.getCodePrefix());
	}
	
	public static String createCode(String prefix){
		Random random=new Random();
		StringBuilder sb=new StringBuilder(prefix);
		for(int i=0;i<10;i++){
			sb.append(BASE_CODE.charAt(random.nextInt(BASE_CODE.length())));
		}
		return sb.toString();
	}
	
	
	public static Map<String, String> parseQString(String str){
		Map<String, String> map=new HashMap<String, String>();
		int len=str.length();
		StringBuilder temp=new StringBuilder();
		char curChar;
		String key=null;
		boolean isKey=true;
		
		for(int i=0;i<len;i++){
			curChar=str.charAt(i);//取当前字符
			if(curChar=='&'){//如果读到&分隔符
				putKeyValueToMap(temp, isKey, key, map);
				temp.setLength(0);
				isKey=true;
			}else if(isKey){
				if(curChar=='='){//如果读到==分隔符
					key=temp.toString();
					temp.setLength(0);
					isKey=false;
				}else{
					temp.append(curChar);
				}
			}else{
				temp.append(curChar);
			}
		}
		
		putKeyValueToMap(temp, isKey, key, map);
		
		return map;
	}
	
	
	public static void putKeyValueToMap(StringBuilder temp,boolean isKey,String key,Map<String, String> map){
		if(isKey){
			key=temp.toString();
			if(key.length()==0){
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, "");
		}else{
			if(key.length()==0){
				throw new RuntimeException("QString format illegal");
			}
			map.put(key, temp.toString());
		}
	}


	public String getCode() {
		return code;
	}


	public QrCodeType getQrCodeType() {
		return qrCodeType;
	}


	public String getAction() {
		return action;
	}


	public Map<String, String> getParams() {
		return params;
	}

	//{name=admin, userType=SYSYEM, userId=1}
	public static void main(String[] args) {
		String paramsStr="userId=1&userType=SYSYEM&name=admin";
		Map<String, String> map=parseQString(paramsStr);
		System.out.println(map);
	}
	
}
