package com.lhrl.insurance.api;

import java.util.List;

import com.lhrl.common.query.Filters;
import com.lhrl.domain.CityProvider;
import com.lhrl.repository.InsuranceCity;
import com.lhrl.repository.InsuranceProvider;

public interface CityProviderService {
	
	/**
	 * 创建一个城市和保险公司组合
	 * @param city
	 * @param provider
	 * @param subsidiary
	 * @param desc
	 */
	void createCityProvider(InsuranceCity city,InsuranceProvider provider,String subsidiary,String desc);
	
	/**
	 * 查找城市和保险公司组合列表
	 * @return
	 */
	List<CityProvider> findCityProviderList();
	
	/**
	 * 获取总数
	 * @return
	 */
	Long getCount();
	
	/**
	 * 根据条件查询列表
	 * @param filters
	 * @return
	 */
	List<CityProvider> findCityProviderList(Filters filters);
}
