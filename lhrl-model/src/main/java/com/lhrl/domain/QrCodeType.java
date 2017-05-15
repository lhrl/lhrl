package com.lhrl.domain;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * 
 * @author liu lang
 *
 */
public enum QrCodeType {

	TEXT{

		@Override
		public String getCodePrefix() {
			return "KT_";
		}
		
	},
	LINK{

		@Override
		public String getCodePrefix() {
			String prefix="";
			try {
				prefix=URLEncoder.encode("http://lhrl.com/qr/","utf-8");
			} catch (UnsupportedEncodingException e) {
			}
			return prefix;
		}
		
	};
	
	public abstract String getCodePrefix();
}
