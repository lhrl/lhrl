package com.lhrl.qrcode.api;

import java.util.Map;

import com.lhrl.domain.QrCode;

/**
 * 
 * @author liu lang
 *
 */
public interface QrCodeService {

	/**
	 * 编码
	 * @param type 目前支持TEXT和LINK两种类型
	 * @param action
	 * @param urlParams
	 * @return
	 */
	public String encode(String type,String action,String urlParams);
	
	 /**
     * 编码
     *
     * @param type 目前支持TEXT和LINK两种类型
     * @param action
     * @param params
     * @return
     */
    public String encode(String type, String action, Map<String, String> params);
    
    /**
     * 解码
     *
     * @param code
     * @return
     */
    public QrCode decode(String code);
    
    /**
     * 移除验证码
     * @param code
     */
    public void remove(String code);
}
