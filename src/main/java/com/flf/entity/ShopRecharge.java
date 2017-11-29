package com.flf.entity;

import java.math.BigDecimal;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopRechargeEntity<br>
 */
public class ShopRecharge extends BaseEntity {

	private java.lang.Integer id;//
	private java.lang.Integer userId;// 用户表id（外键）
	private java.lang.Integer rechargeType;// 2支付宝 1微信
	private java.util.Date createTime;// 充值时间
	private java.lang.Integer rechargeStatus;// 1成功0失败
	private BigDecimal money;// 充值金额
	private java.lang.String bankNo;// 流水号
	private java.lang.String prepayId;// 流水号
	private java.lang.Integer rechargeFor;// 0:余额充值; 1:押金 2正常支付
	private java.lang.Integer notifyStatus;// 是否通知（微信支付成功通知）1需要 0不需要
	private java.lang.String remark3;//
	private java.lang.String isRefund;// 1(已退款)0（未退款）
	private BigDecimal refundNum;// 退款金额
	private java.lang.String refundUpdateTime;// 退款更新时间
	private java.lang.String alipayTradeNo;// 支付宝的交易号
	private String paymentTarget;// 针对此次支付的目标对象(订单编号)

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.Integer getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.Integer userId) {
		this.userId = userId;
	}

	public java.lang.Integer getRechargeType() {
		return this.rechargeType;
	}

	public void setRechargeType(java.lang.Integer rechargeType) {
		this.rechargeType = rechargeType;
	}

	public java.util.Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.util.Date createTime) {
		this.createTime = createTime;
	}

	public java.lang.Integer getRechargeStatus() {
		return this.rechargeStatus;
	}

	public void setRechargeStatus(java.lang.Integer rechargeStatus) {
		this.rechargeStatus = rechargeStatus;
	}

	public BigDecimal getMoney() {
		return this.money;
	}

	public void setMoney(BigDecimal money) {
		this.money = money;
	}

	public java.lang.String getBankNo() {
		return this.bankNo;
	}

	public void setBankNo(java.lang.String bankNo) {
		this.bankNo = bankNo;
	}

	public java.lang.String getPrepayId() {
		return this.prepayId;
	}

	public void setPrepayId(java.lang.String prepayId) {
		this.prepayId = prepayId;
	}

	public java.lang.Integer getRechargeFor() {
		return this.rechargeFor;
	}

	public void setRechargeFor(java.lang.Integer rechargeFor) {
		this.rechargeFor = rechargeFor;
	}

	public java.lang.Integer getNotifyStatus() {
		return this.notifyStatus;
	}

	public void setNotifyStatus(java.lang.Integer notifyStatus) {
		this.notifyStatus = notifyStatus;
	}

	public java.lang.String getRemark3() {
		return this.remark3;
	}

	public void setRemark3(java.lang.String remark3) {
		this.remark3 = remark3;
	}

	public java.lang.String getIsRefund() {
		return this.isRefund;
	}

	public void setIsRefund(java.lang.String isRefund) {
		this.isRefund = isRefund;
	}

	public BigDecimal getRefundNum() {
		return this.refundNum;
	}

	public void setRefundNum(BigDecimal refundNum) {
		this.refundNum = refundNum;
	}

	public java.lang.String getRefundUpdateTime() {
		return this.refundUpdateTime;
	}

	public void setRefundUpdateTime(java.lang.String refundUpdateTime) {
		this.refundUpdateTime = refundUpdateTime;
	}

	public java.lang.String getAlipayTradeNo() {
		return this.alipayTradeNo;
	}

	public void setAlipayTradeNo(java.lang.String alipayTradeNo) {
		this.alipayTradeNo = alipayTradeNo;
	}

	public String getPaymentTarget() {
		return this.paymentTarget;
	}

	public void setPaymentTarget(String paymentTarget) {
		this.paymentTarget = paymentTarget;
	}
}
