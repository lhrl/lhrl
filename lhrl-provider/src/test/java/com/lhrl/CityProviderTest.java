package com.lhrl;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Commit;

import com.lhrl.domain.CityProvider;
import com.lhrl.insurance.api.CityProviderService;
import com.lhrl.repository.InsuranceCity;
import com.lhrl.repository.InsuranceProvider;

public class CityProviderTest extends TestSupport{

	@Autowired
	private CityProviderService cityProviderService;
	
	@Commit
	//@Test
	public void testAdd(){
		cityProviderService.createCityProvider(InsuranceCity.BEIJING,InsuranceProvider.PEOPLE, "松江区", "测试版");
	}
	
	@Test
	public void testFind(){
		List<CityProvider> cityProviders=cityProviderService.findCityProviderList();
		for (CityProvider cityProvider : cityProviders) {
			System.out.println(cityProvider.getCity().getName()+","+cityProvider.getProvider().getFullName());
		}
	}
}
