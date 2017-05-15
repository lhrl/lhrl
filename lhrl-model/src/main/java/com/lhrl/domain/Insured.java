package com.lhrl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import com.lhrl.repository.InsuranceCity;

@Entity
public class Insured extends Person{

	 /**
	 * 
	 */
	private static final long serialVersionUID = -6040251185864638215L;

	/**
     * 关联的客户userid
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 城市
     */
    @Column(name = "city")
    @Enumerated(value = EnumType.STRING)
    private InsuranceCity city;

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public InsuranceCity getCity() {
		return city;
	}

	public void setCity(InsuranceCity city) {
		this.city = city;
	}
    
    
}
