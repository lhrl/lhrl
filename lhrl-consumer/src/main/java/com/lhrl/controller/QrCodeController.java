package com.lhrl.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lhrl.qrcode.api.QrCodeService;
import com.lhrl.result.ResultResponse;

@Controller
@RequestMapping("qrcode")
public class QrCodeController {
	
	@Autowired
	private QrCodeService qrCodeService;
	
	/**
     * 生成二维码字符
     *
     * @param type
     * @param action
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("encodeQrCode")
    public Object encodeQrCode(@RequestParam(value = "type", required = true) String type, @RequestParam(value = "action", required = true) String action,
            @RequestParam(value = "params", required = true) String params) {
        return ResultResponse.buildSuccess(qrCodeService.encode(type, action, params));
    }
    
    /**
     * 解析二维码
     *
     * @param type
     * @param action
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("decodeQrCode")
    public Object encodeQrCode(@RequestParam(value = "code", required = true) String code) {
        return ResultResponse.buildSuccess(qrCodeService.decode(code));
    }
    
    /**
     * 移除二维码
     *
     * @param type
     * @param action
     * @param params
     * @return
     */
    @ResponseBody
    @RequestMapping("removeQrCode")
    public Object removeQrCode(@RequestParam(value = "code", required = true) String code) {
    	qrCodeService.remove(code);
        return ResultResponse.buildSuccess();
    }
}
