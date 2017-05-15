package com.lhrl.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.lhrl.base.AbstractBaseEntity;

@Entity
@Table(name = "insurance_item")
public class InsuranceItem extends AbstractBaseEntity{

	 /**
	 * 
	 */
	private static final long serialVersionUID = 4111898666861050810L;

	/**
     * 保险产品
     */
    @Column(name = "product")
    @Enumerated(EnumType.STRING)
    private InsuranceProduct product;

    /**
     * 价格
     */
    @Column(name = "price")
    private BigDecimal price = BigDecimal.ZERO;

    /**
     * 产品选项
     */
    @Column(name = "options_str")
    private String optionsStr;

    /**
     * 所属保险对象
     */
    @ManyToOne(targetEntity = Insurance.class)
    @JoinColumn(name = "insurance_id")
    @ForeignKey(name = "null")
    private Insurance insurance;

}
