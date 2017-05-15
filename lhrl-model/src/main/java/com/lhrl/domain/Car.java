package com.lhrl.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

import com.lhrl.base.AbstractBaseEntity;

@Entity
@Table(name = "insurance_car")
public class Car extends AbstractBaseEntity{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5244021948058739670L;

	 /**
     * 车牌
     */
    @Column(name = "plate_no")
    private String plateNo;

    /**
     * 车架号
     */
    @Column(name = "vin")
    private String vin;

    /**
     * 发动机号
     */
    @Column(name = "engine_code")
    private String engineCode;

    /**
     * 车主
     */
    @ManyToOne(targetEntity = Person.class)
    @JoinColumn(name = "person_id")
    @ForeignKey(name = "null")
    private Person Owner;

    /**
     * 行驶证照片
     */
    @Column(name = "license_photo_path")
    private String licensePhotoPath;


}
