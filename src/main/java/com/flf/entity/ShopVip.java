package com.flf.entity;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopVipEntity<br>
 */
public class ShopVip extends BaseEntity {

	private java.lang.Integer vipId;//
	private java.lang.String vipName;//
	private java.lang.Integer vipPrice;//
	private String createTime;//

	private int number;// 可选数量
	private int commodityPrice;// 用于商品的价格V1 《=500 V2 《1000 V3不限制

	private Page page;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getCommodityPrice() {
		return commodityPrice;
	}

	public void setCommodityPrice(int commodityPrice) {
		this.commodityPrice = commodityPrice;
	}

	public java.lang.Integer getVipId() {
		return this.vipId;
	}

	public void setVipId(java.lang.Integer vipId) {
		this.vipId = vipId;
	}

	public java.lang.String getVipName() {
		return this.vipName;
	}

	public void setVipName(java.lang.String vipName) {
		this.vipName = vipName;
	}

	

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public java.lang.Integer getVipPrice() {
		return vipPrice;
	}

	public void setVipPrice(java.lang.Integer vipPrice) {
		this.vipPrice = vipPrice;
	}

}
