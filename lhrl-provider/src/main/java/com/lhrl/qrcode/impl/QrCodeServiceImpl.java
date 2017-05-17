package com.lhrl.qrcode.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.cache.CacheManager;
import org.springframework.util.StringUtils;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhrl.domain.QrCode;
import com.lhrl.qrcode.api.QrCodeService;

/**
 * 
 * @author liu lang
 *
 */

public class QrCodeServiceImpl implements QrCodeService{
	
	private static final String CACHE_NAME_URL = "qrcode";
	
	@Resource
	private CacheManager cacheManager;
	
	@Override
	public String encode(String type, String action, String urlParams) {
		return enCode(new QrCode(type, action, urlParams));
	}

	@Override
	public String encode(String type, String action, Map<String, String> params) {
		return enCode(new QrCode(type, action, params));
	}
	
	
	@Override
	public QrCode decode(String code) {
		QrCode qrCode= StringUtils.hasText(code)?this.cacheGet(code):null;
		return qrCode;
	}
	
	@Override
	public void remove(String code){
		cacheManager.getCache(CACHE_NAME_URL).evict(code);//移除
	}
	/**
	 * 缓存QrCode 如果缓存存在 则重新生成code
	 * @param qrCode
	 * @return
	 */
	public String enCode(QrCode qrCode){
		if(null!=this.cacheGet(qrCode.getCode())){
			qrCode.initCode();
			return enCode(qrCode);
		}
		this.cacheManager.getCache(CACHE_NAME_URL).put(qrCode.getCode(), qrCode);
		return qrCode.getCode();
	}
	
	/**
	 * 从缓存中获取到QrCode
	 * @param code
	 * @return
	 */
	private QrCode cacheGet(String code){
		return this.cacheManager.getCache(CACHE_NAME_URL).get(code, QrCode.class);
	}

	

	
}
