package com.flf.entity;

import com.base.entity.BaseEntity;

public class FreeBuy extends BaseEntity{

	
	private static final long serialVersionUID = 1L;

	private Integer buyId;
	
	private Integer commodityId;
	
	private Integer number;

	public Integer getBuyId() {
		return buyId;
	}

	public void setBuyId(Integer buyId) {
		this.buyId = buyId;
	}

	public Integer getCommodityId() {
		return commodityId;
	}

	public void setCommodityId(Integer commodityId) {
		this.commodityId = commodityId;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
	
	
	
	
}
