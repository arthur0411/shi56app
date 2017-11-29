package com.flf.entity;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderPaymentEntity<br>
 */
public class ShopOrderPayment extends BaseEntity {
	
		private java.lang.Integer id;//   	private java.lang.String orderNo;//   订单表外键	private java.lang.Integer rechargeId;//   充值表外键	public java.lang.Integer getId() {	    return this.id;	}	public void setId(java.lang.Integer id) {	    this.id=id;	}	public java.lang.String getOrderNo() {	    return this.orderNo;	}	public void setOrderNo(java.lang.String orderNo) {	    this.orderNo=orderNo;	}	public java.lang.Integer getRechargeId() {	    return this.rechargeId;	}	public void setRechargeId(java.lang.Integer rechargeId) {	    this.rechargeId=rechargeId;	}
}

