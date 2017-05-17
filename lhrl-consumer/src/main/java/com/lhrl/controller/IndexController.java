package com.lhrl.controller;


import com.alibaba.dubbo.config.annotation.Reference;
import com.lhrl.common.query.Filter;
import com.lhrl.common.query.Filter.Operaion;
import com.lhrl.common.query.Filters;
import com.lhrl.insurance.api.CityProviderService;
import com.lhrl.repository.InsuranceCity;
import com.lhrl.repository.InsuranceProvider;
import com.lhrl.result.ResultResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("lhrl")
public class IndexController {
	
	@Reference
	private CityProviderService cityProviderService;
	
	/**
	 * 创建一个城市和保险公司组合
	 * @return
	 */
	@ResponseBody
	@RequestMapping("create")
	public Object create(){
		cityProviderService.createCityProvider(InsuranceCity.SHANGHAI, InsuranceProvider.PINGAN, "浦东新区", "测试版");
		return ResultResponse.buildSuccess("success");
	}
	
	/**
	 * 查找城市和保险公司组合列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findCityProviderList")
	public Object findCityProviderList(){
		System.out.println(" 查找城市列表");
		System.out.println(cityProviderService==null);
		return ResultResponse.buildSuccess(cityProviderService.findCityProviderList());
	}
	
	/**
	 * 获取总记录数
	 * @return
	 */
	@ResponseBody
	@RequestMapping("getCityProviderCount")
	public Object getCityProviderCount(){
		return ResultResponse.buildSuccess(cityProviderService.getCount());
	}
	
	/**
	 * 查找某个城市的保险公司组合列表
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findList")
	public Object findList(@RequestParam("city")String city){
		Filters filters=new Filters();
		filters.addFilter(new Filter("city", InsuranceCity.valueOf(city), Operaion.eq));
		return ResultResponse.buildSuccess(cityProviderService.findCityProviderList(filters));
	}
}
