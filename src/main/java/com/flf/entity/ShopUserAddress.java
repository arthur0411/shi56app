package com.flf.entity;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserAddressEntity<br>
 */
public class ShopUserAddress extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Integer addressId;//
	private java.lang.String name;// 收货人
	private java.lang.String phone;// 联系电话
	private java.lang.String address;// 收货地址
	private java.lang.Integer userId;// 用户ID
	private java.lang.Object isDefault;// 是否默认 0为默认
	private String area;// 地区

	private Page page;

	public java.lang.Integer getAddressId() {
		return this.addressId;
	}

	public void setAddressId(java.lang.Integer addressId) {
		this.addressId = addressId;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public java.lang.String getPhone() {
		return this.phone;
	}

	public void setPhone(java.lang.String phone) {
		this.phone = phone;
	}

	public java.lang.String getAddress() {
		return this.address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.Integer getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Object getIsDefault() {
		return this.isDefault;
	}

	public void setIsDefault(java.lang.Object isDefault) {
		this.isDefault = isDefault;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

}
