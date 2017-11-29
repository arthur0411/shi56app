package com.flf.service.impl;

import java.math.BigDecimal;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flf.mapper.ShopUserMapper;
import com.flf.service.IShopSMSPushService;
import com.flf.service.RedisSpringProxy;
import com.flf.util.Config;
import com.flf.util.SMSUtil;

@Service("hajSMSPushService")
public class ShopSMSPushServiceImpl implements IShopSMSPushService {
	private final static Logger log = Logger.getLogger(ShopSMSPushServiceImpl.class);

	@Autowired
	private ShopUserMapper userDao;

	@Autowired
	private RedisSpringProxy redisSpringProxy;

	public static final String SMS_URL = Config.getSmsProperties("sms.url");
	public static final String SMS_APPKEY = Config.getSmsProperties("sms.appkey");
	public static final String SMS_SECRET = Config.getSmsProperties("sms.secret");
	public static final String SMS_FREE_SIGN_NAME = Config.getSmsProperties("sms.free.sign.name");

	/** 会员激活短信通知模版ID */
	public static final String SMS_TEMPLATE_ACTIVATION_NOTIFY = Config
			.getSmsProperties("sms.template.activation.notify");

	/** 登录短信验证码模版ID */
	public static final String SMS_TEMPLATE_LOGIN_CODE = Config.getSmsProperties("sms.template.login.code");

	/** 充值成功短信验证码模版ID */
	public static final String SMS_TEMPLATE_RECHARGE_DONE = Config.getSmsProperties("sms.template.recharge.done");

	/** 小区激活短信推模版ID */
	public static final String SMS_TEMPLATE_VILLAGE_ACTIVE = Config.getSmsProperties("sms.template.village.active");

	/** 用户上报小区短信模板ID */
	public static final String SMS_TEMPLATE_VILLAGE_REPORT = Config.getSmsProperties("sms.template.village.report");

	/** 用户签到满10次短信通知 */
	public static final String SMS_TEMPLATE_SIGNIN10 = Config.getSmsProperties("sms.template.signin10");

	/** 用户上报的小区已成功录入到后台的短信模板ID */
	public static final String SMS_TEMPLATE_COMMUNITY_ENTRY = Config.getSmsProperties("sms.template.community.entry");

	/** 用户状态改为一元购后的短信模板ID */
	public static final String SMS_TEMPLATE_BECAME_VIP = Config.getSmsProperties("sms.template.became.vip");

	/** 用户状态改为会员取消的短信模板ID */
	public static final String SMS_TEMPLATE_CANCLE_VIP = Config.getSmsProperties("sms.template.cancle.vip");

	/** 余额提现办理短信模板ID */
	public static final String SMS_TEMPLATE_WITHDRAWAL = Config.getSmsProperties("sms.template.withdrawal");

	/** 重复下单退款短信模板ID */
	public static final String SMS_TEMPLATE_ORDER_REFUND = Config.getSmsProperties("sms.template.order.refund");

	@Override
	@Deprecated
	public String userSignSMSPush(String tel, String username) {
		log.info("用户签到每满10次短信推送...");
		String smsParamString = "{\"username\":\"" + username + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_SIGNIN10, tel);
	}

	@Override
	public String getLoginVerificationCode(String mobile) {
		String code = RandomStringUtils.randomNumeric(4);
		log.info("给手机号" + mobile + "生成的验证码:" + code);
		redisSpringProxy.saveEx("login_code_" + mobile, 5 * 60L, code);
		String smsParamString = "{\"code\":\"" + code + "\",\"product\":\"" + SMS_FREE_SIGN_NAME + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_LOGIN_CODE, mobile);
	}

	@Override
	public String rechargeSMSPush(int money, String mobile) {
		log.info("充值短信推送短信接口...");
		String smsParamString = "{\"money\":\"" + money + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_RECHARGE_DONE, mobile);
	}

	@Override
	public String rechargeDone(String mobile, BigDecimal money) {
		log.info("充值成功短信通知短信接口...");
		String service = (String) redisSpringProxy.read("SystemConfiguration_xiao_er_wei_xin");
		String smsParamString = "{\"money\":\"" + money + "\",\"service\":\"" + service + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_RECHARGE_DONE, mobile);
	}

	@Override
	public String cancleVIP(String mobile, String community) {
		log.info("用户状态改为会员取消短信接口...");
		String service = (String) redisSpringProxy.read("SystemConfiguration_xiao_er_wei_xin");
		String smsParamString = "{\"community\":\"" + community + "\",\"service\":\"" + service + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_CANCLE_VIP, mobile);
	}

	private String phoneList2String(String[] phoneList) {
		StringBuilder tel = new StringBuilder();
		String reg = ",";
		// 封装需要推送的用户电话号码字符串
		for (int i = 0; i < phoneList.length; i++) {
			tel.append(phoneList[i]);
			if (i < phoneList.length - 1) {
				tel.append(reg);
			}
		}
		return tel.toString();
	}

	@Override
	public String withdrawal(String mobile, BigDecimal amount) {
		log.info("申请退押金办理短信接口...");
		String smsParamString = "{\"amount\":\"" + amount + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_WITHDRAWAL, mobile);
	}

	@Override
	public String orderRefund(String mobile, BigDecimal money, String datetime) {
		log.info("重复下单退款短信接口...");
		String smsParamString = "{\"time\":\"" + datetime + "\",\"money\":\"" + money + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_ORDER_REFUND, mobile);
	}

}
