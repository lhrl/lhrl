package com.lhrl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import com.lhrl.base.AbstractBaseEntity;
import com.lhrl.repository.InsuranceCity;
import com.lhrl.repository.InsuranceProvider;

/**
 * 保险分公司
 * @author liu lang
 *
 */
@Entity
@Table(name = "insurance_city_provider")
public class CityProvider extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8023555159568100234L;

	/**
	 * 保险公司
	 */
	@Column(name="provider")
	@Enumerated(EnumType.STRING)
	private InsuranceProvider provider;
	
	/**
	 * 开通城市
	 */
	@Column(name="city")
	@Enumerated(EnumType.STRING)
	private InsuranceCity city;
	
	/**
	 * 子公司
	 */
	@Column(name="subsidiary")
	private String subsidiary;
	/**
	 * 描述
	 */
	@Column(name="description")
	private String desc;
	
	/**
	 * 构建保险对象
	 * @param provider
	 * @param city
	 * @param subsidiary
	 * @param desc
	 */
	public CityProvider(InsuranceProvider provider, InsuranceCity city,
			String subsidiary, String desc) {
		super();
		this.provider = provider;
		this.city = city;
		this.subsidiary = subsidiary;
		this.desc = desc;
	}
	
	CityProvider(){
		
	}
	/**
	 * getter/setter
	 * @return
	 */
	public InsuranceProvider getProvider() {
		return provider;
	}
	public void setProvider(InsuranceProvider provider) {
		this.provider = provider;
	}
	public InsuranceCity getCity() {
		return city;
	}
	public void setCity(InsuranceCity city) {
		this.city = city;
	}
	public String getSubsidiary() {
		return subsidiary;
	}
	public void setSubsidiary(String subsidiary) {
		this.subsidiary = subsidiary;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	
}
