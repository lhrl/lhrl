package com.lhrl.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.lhrl.base.AbstractBaseEntity;

/**
 * 保险对象
 * @author liu lang
 *
 */
@Entity
@Table(name = "insurance")
public class Insurance extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = 370841311259132033L;

	@ManyToOne(targetEntity = Car.class)
    @JoinColumn(name = "car_id")
    @ForeignKey(name = "null")
    private Car car;


    /**
     *投保人
     */
    @ManyToOne(targetEntity = Insured.class)
    @JoinColumn(name = "insured_id")
    @ForeignKey(name = "null")
    private Insured insured;

    /**
     * 保险项目列表
     */
    @OneToMany(mappedBy = "insurance")
    private List<InsuranceItem> insuranceItems = new ArrayList<InsuranceItem>();


    /**
     * 关联订单
     */
    @OneToOne(mappedBy = "insurance")
    private InsuranceOrder order;

    /**
     * 保险状态̬
     */
    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private InsuranceState state = InsuranceState.ASKPRICE;
    
    /**
     * 保险订单号
     */
    @Column(name = "order_no", length = 50)
    private String orderNo;

    
    
    
    
    /**
     * getter /setter
     * @return
     */
	public Car getCar() {
		return car;
	}

	public void setCar(Car car) {
		this.car = car;
	}

	public Insured getInsured() {
		return insured;
	}

	public void setInsured(Insured insured) {
		this.insured = insured;
	}

	public List<InsuranceItem> getInsuranceItems() {
		return insuranceItems;
	}

	public void setInsuranceItems(List<InsuranceItem> insuranceItems) {
		this.insuranceItems = insuranceItems;
	}

	public InsuranceOrder getOrder() {
		return order;
	}

	public void setOrder(InsuranceOrder order) {
		this.order = order;
	}

	public InsuranceState getState() {
		return state;
	}

	public void setState(InsuranceState state) {
		this.state = state;
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
    
    
}
