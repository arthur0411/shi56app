package com.flf.entity;

import java.math.BigDecimal;
import java.util.List;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderEntity<br>
 */
public class ShopOrder extends BaseEntity {

	private java.lang.Integer orderId;// 订单
	private java.lang.String orderClass;
	private java.lang.String orderCode;// 快递单号
	private java.lang.String userId;// 购买的用户账户
	private java.lang.Object deliveryStatus;// 是否发货（作废）
	private java.lang.Object returnStatus;// 是否归还（作废）
	private java.lang.String createTime;//
	private java.lang.String deliveryTime;//
	private java.lang.Object isAssess;// 是否评价
	private java.lang.Object isPayment;// 是否付款
	private java.lang.String orderNummmm;// 订单号
	private java.lang.String express;// 快递公司
	private java.lang.String expressNum;// 快递公司编号
	private java.lang.String commodityNum;// 订单商品数量
	private java.lang.Integer orderStatus;// 订单状态(1确认中2配送中3佩戴中4返回中5验收中6违规处理7验收完成8已取消)
	private java.lang.Object querenOk;// 确认收货（试戴中）（作废）
	private java.lang.String name;// 收货人
	private java.lang.String phone;// 收货人联系电话
	private java.lang.String address;// 收货人地址
	private java.lang.String jiname;// 寄件人
	private java.lang.String jiaddress;// 寄件人地址
	private java.lang.String returnTime;// 归还时间
	private java.lang.Object isShouhou;// 售后状态

	private BigDecimal totalMoney;// 订单总价
	private BigDecimal actualPayment;// 实付价格
	private BigDecimal feeWaiver;// 优惠金额
	private BigDecimal postFee;// 运费
	private java.lang.Integer paymentWay; // 订单支付方式(1:微信;2:支付宝)
	private String paymentTime;// 支付时间
	private String cancelTime;// 订单取消时间
	private String distributionTime; // 配送时间（填写物流单号）
	private Integer wearingDays; // 佩戴天数（从配送时间3天后计算）

	private List<ShopOrderDetails> orderDetailList;

	private String mobilePhone;

	private String beginTime;
	private String endTime;
	private String userInfo;

	private String returnExpress;// 归还快递公司
	private String returnExpressCode;// 归还快递公司单号

	private Page page;

	public java.lang.Integer getOrderId() {
		return this.orderId;
	}

	public void setOrderId(java.lang.Integer orderId) {
		this.orderId = orderId;
	}

	public java.lang.String getOrderCode() {
		return this.orderCode;
	}

	public void setOrderCode(java.lang.String orderCode) {
		this.orderCode = orderCode;
	}

	public java.lang.String getUserId() {
		return this.userId;
	}

	public void setUserId(java.lang.String userId) {
		this.userId = userId;
	}

	public java.lang.Object getDeliveryStatus() {
		return this.deliveryStatus;
	}

	public void setDeliveryStatus(java.lang.Object deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	public java.lang.Object getReturnStatus() {
		return this.returnStatus;
	}

	public void setReturnStatus(java.lang.Object returnStatus) {
		this.returnStatus = returnStatus;
	}

	public java.lang.String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.Object getIsAssess() {
		return this.isAssess;
	}

	public void setIsAssess(java.lang.Object isAssess) {
		this.isAssess = isAssess;
	}

	public java.lang.Object getIsPayment() {
		return this.isPayment;
	}

	public void setIsPayment(java.lang.Object isPayment) {
		this.isPayment = isPayment;
	}

	public java.lang.String getOrderNummmm() {
		return this.orderNummmm;
	}

	public void setOrderNummmm(java.lang.String orderNummmm) {
		this.orderNummmm = orderNummmm;
	}

	public java.lang.String getExpress() {
		return this.express;
	}

	public void setExpress(java.lang.String express) {
		this.express = express;
	}

	public java.lang.String getExpressNum() {
		return this.expressNum;
	}

	public void setExpressNum(java.lang.String expressNum) {
		this.expressNum = expressNum;
	}

	public java.lang.String getCommodityNum() {
		return this.commodityNum;
	}

	public void setCommodityNum(java.lang.String commodityNum) {
		this.commodityNum = commodityNum;
	}

	public java.lang.Object getQuerenOk() {
		return this.querenOk;
	}

	public void setQuerenOk(java.lang.Object querenOk) {
		this.querenOk = querenOk;
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

	public java.lang.String getJiname() {
		return this.jiname;
	}

	public void setJiname(java.lang.String jiname) {
		this.jiname = jiname;
	}

	public java.lang.String getJiaddress() {
		return this.jiaddress;
	}

	public void setJiaddress(java.lang.String jiaddress) {
		this.jiaddress = jiaddress;
	}

	public java.lang.String getReturnTime() {
		return this.returnTime;
	}

	public void setReturnTime(java.lang.String returnTime) {
		this.returnTime = returnTime;
	}

	public java.lang.Object getIsShouhou() {
		return this.isShouhou;
	}

	public void setIsShouhou(java.lang.Object isShouhou) {
		this.isShouhou = isShouhou;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public List<ShopOrderDetails> getOrderDetailList() {
		return orderDetailList;
	}

	public void setOrderDetailList(List<ShopOrderDetails> orderDetailList) {
		this.orderDetailList = orderDetailList;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public java.lang.Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(java.lang.Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	public BigDecimal getTotalMoney() {
		return totalMoney;
	}

	public void setTotalMoney(BigDecimal totalMoney) {
		this.totalMoney = totalMoney;
	}

	public BigDecimal getActualPayment() {
		return actualPayment;
	}

	public void setActualPayment(BigDecimal actualPayment) {
		this.actualPayment = actualPayment;
	}

	public BigDecimal getFeeWaiver() {
		return feeWaiver;
	}

	public void setFeeWaiver(BigDecimal feeWaiver) {
		this.feeWaiver = feeWaiver;
	}

	public BigDecimal getPostFee() {
		return postFee;
	}

	public void setPostFee(BigDecimal postFee) {
		this.postFee = postFee;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public java.lang.Integer getPaymentWay() {
		return paymentWay;
	}

	public void setPaymentWay(java.lang.Integer paymentWay) {
		this.paymentWay = paymentWay;
	}

	public String getPaymentTime() {
		return paymentTime;
	}

	public void setPaymentTime(String paymentTime) {
		this.paymentTime = paymentTime;
	}

	public String getCancelTime() {
		return cancelTime;
	}

	public void setCancelTime(String cancelTime) {
		this.cancelTime = cancelTime;
	}

	public String getDistributionTime() {
		return distributionTime;
	}

	public void setDistributionTime(String distributionTime) {
		this.distributionTime = distributionTime;
	}

	public Integer getWearingDays() {
		return wearingDays;
	}

	public void setWearingDays(Integer wearingDays) {
		this.wearingDays = wearingDays;
	}

	public String getReturnExpress() {
		return returnExpress;
	}

	public void setReturnExpress(String returnExpress) {
		this.returnExpress = returnExpress;
	}

	public String getReturnExpressCode() {
		return returnExpressCode;
	}

	public void setReturnExpressCode(String returnExpressCode) {
		this.returnExpressCode = returnExpressCode;
	}

	public java.lang.String getOrderClass() {
		return orderClass;
	}

	public void setOrderClass(java.lang.String orderClass) {
		this.orderClass = orderClass;
	}

	public java.lang.String getDeliveryTime() {
		return deliveryTime;
	}

	public void setDeliveryTime(java.lang.String deliveryTime) {
		this.deliveryTime = deliveryTime;
	}

}
