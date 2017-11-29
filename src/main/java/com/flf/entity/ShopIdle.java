package com.flf.entity;

import java.util.List;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopIdleEntity<br>
 */
public class ShopIdle extends BaseEntity {

	private java.lang.Integer id;//
	private java.lang.Integer userId;// 用户id
	private java.lang.String brand;// 品牌
	private java.lang.String material;// 材质
	private java.lang.String catagory;
	private java.lang.String useYears;
	private java.lang.String status;
	private java.lang.String panoramicPic;
	private java.lang.String partPic;
	private java.lang.String voucherPic;
	private java.lang.String createTime;// 提交时间
	private java.lang.String mobile;
	private java.lang.String orderNum;
	private java.lang.String express;
	private java.lang.String expressCode;
	private java.lang.String returnExpress;
	private java.lang.String returnCode;
	private java.lang.String address;
	private java.lang.String residueDay;
	
	public java.lang.String getExpress() {
		return express;
	}

	public void setExpress(java.lang.String express) {
		this.express = express;
	}

	public java.lang.String getExpressCode() {
		return expressCode;
	}

	public void setExpressCode(java.lang.String expressCode) {
		this.expressCode = expressCode;
	}

	public java.lang.String getReturnExpress() {
		return returnExpress;
	}

	public void setReturnExpress(java.lang.String returnExpress) {
		this.returnExpress = returnExpress;
	}

	public java.lang.String getReturnCode() {
		return returnCode;
	}

	public void setReturnCode(java.lang.String returnCode) {
		this.returnCode = returnCode;
	}

	public java.lang.String getAddress() {
		return address;
	}

	public void setAddress(java.lang.String address) {
		this.address = address;
	}

	public java.lang.String getResidueDay() {
		return residueDay;
	}

	public void setResidueDay(java.lang.String residueDay) {
		this.residueDay = residueDay;
	}

	public java.lang.Integer getId() {
		return id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getUserId() {
		return userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.String getBrand() {
		return brand;
	}

	public void setBrand(java.lang.String brand) {
		this.brand = brand;
	}

	public java.lang.String getMaterial() {
		return material;
	}

	public void setMaterial(java.lang.String material) {
		this.material = material;
	}

	public java.lang.String getCatagory() {
		return catagory;
	}

	public void setCatagory(java.lang.String catagory) {
		this.catagory = catagory;
	}

	public java.lang.String getUseYears() {
		return useYears;
	}

	public void setUseYears(java.lang.String useYears) {
		this.useYears = useYears;
	}

	public java.lang.String getStatus() {
		return status;
	}

	public void setStatus(java.lang.String status) {
		this.status = status;
	}

	public java.lang.String getPanoramicPic() {
		return panoramicPic;
	}

	public void setPanoramicPic(java.lang.String panoramicPic) {
		this.panoramicPic = panoramicPic;
	}

	public java.lang.String getPartPic() {
		return partPic;
	}

	public void setPartPic(java.lang.String partPic) {
		this.partPic = partPic;
	}

	public java.lang.String getVoucherPic() {
		return voucherPic;
	}

	public void setVoucherPic(java.lang.String voucherPic) {
		this.voucherPic = voucherPic;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getMobile() {
		return mobile;
	}

	public void setMobile(java.lang.String mobile) {
		this.mobile = mobile;
	}

	public java.lang.String getOrderNum() {
		return orderNum;
	}

	public void setOrderNum(java.lang.String orderNum) {
		this.orderNum = orderNum;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	private Page page; // 分页

}