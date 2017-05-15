package com.lhrl;

import static org.junit.Assert.assertNotNull;

import javax.annotation.Resource;

import org.junit.Test;

import com.lhrl.qrcode.api.QrCodeService;

public class QrCodeServiceTest extends TestSupport{
	
	@Resource
	private QrCodeService qrCodeService;
	
	@Test
	public void testEncode(){
		System.out.println("encodeUrl");
        String type = "TEXT";
        String action = "kuanter://hhb/product/list?id=1";
        String paramStr = "userId = 128211&userType=CUSTOMER";

        String result = qrCodeService.encode(type, action, paramStr);
        assertNotNull(result);
        System.out.println(result);
	}
}
