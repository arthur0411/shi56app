package com.flf.util;

public class HajRechargeUtil {

	/**
	 * 账户充值
	 */
	public static final int RECHARGE = 0;

	/**
	 * 押金
	 */
	public static final int ANNUAL_FEE = 1;

	/**
	 * 支付
	 */
	public static final int ORDER_PAYMENT = 2;

	/**
	 * 处理调用第三方支付平台的原因
	 */
	public static String getRechargeFor(Integer rechargeFor) {
		switch (rechargeFor) {
		case RECHARGE:
			return "账户充值";
		case ANNUAL_FEE:
			return "成为会员";
		default:
			return "续费";
		}
	}

}
