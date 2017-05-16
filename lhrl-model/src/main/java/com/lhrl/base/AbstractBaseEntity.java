package com.lhrl.base;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import com.lhrl.common.domain.AbstractBizEntity;

@MappedSuperclass
public class AbstractBaseEntity extends AbstractBizEntity<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7673396193777472530L;
	@Id
	@Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Override
	public Long getId() {
		return null;
	}

	@Override
	public void setId(Long id) {
		
	}

}
