package com.flf.entity;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>ShopRechargerecordEntity<br>
 */
public class ShopRechargerecord extends BaseEntity {
	
		private java.lang.Integer recordId;//   	private java.lang.Integer userId;//   用户ID	private java.lang.Float money;//   用户消费金额累计	private java.lang.Integer createTime;//   用户首次消费时间	private java.lang.Integer endTime;//   用户最后一次消费时间	private java.lang.String detalis;//   消费详情	public java.lang.Integer getRecordId() {	    return this.recordId;	}	public void setRecordId(java.lang.Integer recordId) {	    this.recordId=recordId;	}	public java.lang.Integer getUserId() {	    return this.userId;	}	public void setUserId(java.lang.Integer userId) {	    this.userId=userId;	}	public java.lang.Float getMoney() {	    return this.money;	}	public void setMoney(java.lang.Float money) {	    this.money=money;	}	public java.lang.Integer getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Integer createTime) {	    this.createTime=createTime;	}	public java.lang.Integer getEndTime() {	    return this.endTime;	}	public void setEndTime(java.lang.Integer endTime) {	    this.endTime=endTime;	}	public java.lang.String getDetalis() {	    return this.detalis;	}	public void setDetalis(java.lang.String detalis) {	    this.detalis=detalis;	}
}

