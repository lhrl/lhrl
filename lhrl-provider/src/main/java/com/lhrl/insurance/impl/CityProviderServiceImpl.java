package com.lhrl.insurance.impl;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.alibaba.dubbo.config.annotation.Service;
import com.lhrl.common.dao.DaoFactory;
import com.lhrl.common.query.Filters;
import com.lhrl.domain.CityProvider;
import com.lhrl.insurance.api.CityProviderService;
import com.lhrl.repository.InsuranceCity;
import com.lhrl.repository.InsuranceProvider;

/**
 * 
 * @author liu lang
 *
 * 2017年5月4日下午8:47:55
 */
@Service
@Transactional(readOnly=true)
public class CityProviderServiceImpl implements CityProviderService{

	@Transactional
	public void createCityProvider(InsuranceCity city,
			InsuranceProvider provider, String subsidiary, String desc) {
		CityProvider cp=new CityProvider(provider, city, subsidiary, desc);
		cp.save();
	}
	
	@Override
	public List<CityProvider> findCityProviderList() {
		List<CityProvider> list=DaoFactory.getDao().find(CityProvider.class, null);
		return list;
	}

	@Override
	public Long getCount() {
		String hql="select count(cityProvider.id) from CityProvider cityProvider";
		Long count=(Long)DaoFactory.getDao().getHQLQuery().find(hql).get(0);
		return count;
	}
	
	@Override
	public List<CityProvider> findCityProviderList(Filters filters) {
		List<CityProvider> list=DaoFactory.getDao().find(CityProvider.class, filters);
		return list;
	}
}
