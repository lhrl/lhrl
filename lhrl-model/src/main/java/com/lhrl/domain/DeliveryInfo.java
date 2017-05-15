package com.lhrl.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class DeliveryInfo {
	//收货地址
    @Column(name = "address")
    private String address;

    //收货人name
    @Column(name = "recipents_name")
    private String recipentsName;

    //收货人mobile
    @Column(name = "recipents_mobile")
    private String recipentsMobile;

    //收货人pricePic
    @Column(name = "pricePic")
    private String pricePic;

    //省
    @Column(name = "province")
    private String province;

    //城市
    @Column(name = "city")
    private String city;

    //区
    @Column(name = "area")
    private String area;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getRecipentsName() {
		return recipentsName;
	}

	public void setRecipentsName(String recipentsName) {
		this.recipentsName = recipentsName;
	}

	public String getRecipentsMobile() {
		return recipentsMobile;
	}

	public void setRecipentsMobile(String recipentsMobile) {
		this.recipentsMobile = recipentsMobile;
	}

	public String getPricePic() {
		return pricePic;
	}

	public void setPricePic(String pricePic) {
		this.pricePic = pricePic;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}
    
    
}
