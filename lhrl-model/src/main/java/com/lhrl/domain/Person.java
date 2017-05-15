package com.lhrl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.lhrl.base.AbstractBaseEntity;

@Entity
@Table(name="insurance_person")
public abstract class Person extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2839199979598194247L;

	/**
	 * 姓名
	 */
	@Column(name="name")
	private String name;
	
	/**
	 * 手机
	 */
	@Column(name="mobile")
	private String mobile;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	
	
}
