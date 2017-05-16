package com.lhrl.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.ForeignKey;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.lhrl.common.domain.Order;
import com.lhrl.common.domain.SellItem;
import com.lhrl.common.domain.SystemType;
import com.lhrl.base.AbstractBaseEntity;
import com.lhrl.repository.InsuranceProvider;

@Entity
@Table(name = "insurance_order")
public class InsuranceOrder extends AbstractBaseEntity implements Order<Long>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4163432942693309694L;

	
	@OneToOne(targetEntity = Insurance.class)
    @JoinColumn(name = "insurance_id")
    @ForeignKey(name = "null")
    private Insurance insurance;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private InsuranceOrderState state = InsuranceOrderState.WAIT_PAY;

    @Column(name = "provider")
    @Enumerated(EnumType.STRING)
    private InsuranceProvider provider;

    @Column(name = "total_price")
    private BigDecimal totalPrice = new BigDecimal(0);

    @Column(name = "discount")
    private BigDecimal discount = new BigDecimal(0);


    //交强险订单号
    @Column(name = "forced_insurance_order_no")
    private String forcedInsuranceOrderNo;
    
    //商业险订单号
    @Column(name = "commercial_insurance_order_no")
    private String commercialInsuranceOrderNo;

    //交强险生效时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "forced_insurance_effect_date")
    private Date forcedInsuranceEffectDate;

    //商业险生效时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "commercial_insurance_effect_date")
    private Date commercialInsuranceEffectDate;

    //支付时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "pay_date")
    private Date payDate;

    //完成时间
    @Temporal(value = TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Column(name = "finish_date")
    private Date finishDate;
    //值对象 收件人信息
    @Embedded
    private DeliveryInfo deliveryInfo;

    @Column(name = "commissioned")
    private boolean commissioned;

    @OneToOne(targetEntity = CityProvider.class)
    @JoinColumn(name = "city_provider_id")
    @ForeignKey(name = "null")
    private CityProvider cityProvider;
    
    
    
    /**
     * setter getter
     * @return
     */
    
	public Insurance getInsurance() {
		return insurance;
	}

	public void setInsurance(Insurance insurance) {
		this.insurance = insurance;
	}

	public InsuranceOrderState getState() {
		return state;
	}

	public void setState(InsuranceOrderState state) {
		this.state = state;
	}

	public InsuranceProvider getProvider() {
		return provider;
	}

	public void setProvider(InsuranceProvider provider) {
		this.provider = provider;
	}

	public BigDecimal getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(BigDecimal totalPrice) {
		this.totalPrice = totalPrice;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getForcedInsuranceOrderNo() {
		return forcedInsuranceOrderNo;
	}

	public void setForcedInsuranceOrderNo(String forcedInsuranceOrderNo) {
		this.forcedInsuranceOrderNo = forcedInsuranceOrderNo;
	}

	public String getCommercialInsuranceOrderNo() {
		return commercialInsuranceOrderNo;
	}

	public void setCommercialInsuranceOrderNo(String commercialInsuranceOrderNo) {
		this.commercialInsuranceOrderNo = commercialInsuranceOrderNo;
	}

	public Date getForcedInsuranceEffectDate() {
		return forcedInsuranceEffectDate;
	}

	public void setForcedInsuranceEffectDate(Date forcedInsuranceEffectDate) {
		this.forcedInsuranceEffectDate = forcedInsuranceEffectDate;
	}

	public Date getCommercialInsuranceEffectDate() {
		return commercialInsuranceEffectDate;
	}

	public void setCommercialInsuranceEffectDate(Date commercialInsuranceEffectDate) {
		this.commercialInsuranceEffectDate = commercialInsuranceEffectDate;
	}

	public Date getPayDate() {
		return payDate;
	}

	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}

	public Date getFinishDate() {
		return finishDate;
	}

	public void setFinishDate(Date finishDate) {
		this.finishDate = finishDate;
	}

	public DeliveryInfo getDeliveryInfo() {
		return deliveryInfo;
	}

	public void setDeliveryInfo(DeliveryInfo deliveryInfo) {
		this.deliveryInfo = deliveryInfo;
	}

	public boolean isCommissioned() {
		return commissioned;
	}

	public void setCommissioned(boolean commissioned) {
		this.commissioned = commissioned;
	}

	public CityProvider getCityProvider() {
		return cityProvider;
	}

	public void setCityProvider(CityProvider cityProvider) {
		this.cityProvider = cityProvider;
	}

	@Override
	public String getOrderNo() {
		return null;
	}

	@Override
	public List<SellItem<Long>> getSellItems() {
		return null;
	}

	@Override
	public SystemType getSystemType() {
		return null;
	}

}
