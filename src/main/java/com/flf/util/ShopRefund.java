package com.flf.util;

import java.math.BigDecimal;
import java.util.Date;

import org.apache.log4j.Logger;

import com.alipay.util.AlipayUtil;
import com.flf.entity.ShopRecharge;
import com.flf.service.ShopRechargeService;
import com.weixin.PayUtil;

/**
 * Created by SevenWong on 2017/2/16 17:16. 查询退款金额工具类
 */
public class ShopRefund extends Thread {

	private Logger log = Logger.getLogger(ShopRefund.class);

	private ShopRecharge ShopRecharge;

	private ShopRechargeService ShopRechargeService;

	public ShopRefund(ShopRecharge ShopRecharge, ShopRechargeService ShopRechargeService) {
		this.ShopRecharge = ShopRecharge;
		this.ShopRechargeService = ShopRechargeService;
	}

	@Override
	public synchronized void run() {
		log.info(currentThread().getName() + "启动准备处理数据");
		try {
			if (null != ShopRecharge) {
				// 2支付宝 1微信
				int rechargeType = ShopRecharge.getRechargeType();
				if (rechargeType == 1) {
					// 微信
					BigDecimal refundWeiXin = PayUtil.refundQuery(ShopRecharge.getBankNo());
					if (!refundWeiXin.equals(BigDecimal.ZERO)) {
						ShopRecharge.setRefundNum(refundWeiXin);
						ShopRecharge.setIsRefund("1");
						ShopRecharge.setRefundUpdateTime(DateUtil.datetime2Str(new Date()));
						ShopRechargeService.updateBySelective(ShopRecharge);
					}
				} else {
					// 支付宝
					String refundAlipay = AlipayUtil.alipayRefundStatus(ShopRecharge.getBankNo());
					if (refundAlipay != null) {
						ShopRecharge.setIsRefund("1");
						ShopRecharge.setRefundNum(new BigDecimal(refundAlipay));
						ShopRecharge.setRefundUpdateTime(DateUtil.datetime2Str(new Date()));
						ShopRechargeService.updateBySelective(ShopRecharge);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			log.error(currentThread().getName() + "处理数据时出错" + e);
		} finally {
			log.info(currentThread().getName() + "处理完成");
		}
	}
}
