package com.flf.controller.app;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.flf.entity.ShopRecharge;
import com.flf.entity.ShopRenew;
import com.flf.entity.ShopUser;
import com.flf.entity.ShopVip;
import com.flf.service.ShopOrderPaymentService;
import com.flf.service.ShopRechargeService;
import com.flf.service.ShopRenewService;
import com.flf.service.ShopUserService;
import com.flf.service.ShopVipService;
import com.flf.util.DateUtil;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderPaymentController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/renewapp")
public class ShopRenewAppController {

	private final static Logger log = Logger.getLogger(ShopRenewAppController.class);
	
	
	@Autowired(required = false)
	private ShopUserService shopUserService;
	
	@Autowired(required = false)
	private ShopVipService shopVipService;
	
	@Autowired(required = false)
	private ShopRenewService shopRenewService;
	
	@Autowired(required = false)
	private ShopRechargeService shopRechargeService;


	@RequestMapping(value = "/update")
	public void update(Integer rechargeId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<>();
		try {
			ShopRecharge recharge = shopRechargeService.findById(rechargeId);
			if(recharge != null) {
				ShopRenew renew = new ShopRenew();
				renew.setUserId(recharge.getUserId());
				renew.setVipId(Integer.parseInt(recharge.getPaymentTarget()));
				renew.setRechargeId(rechargeId);
				
				if(renew.getUserId() != null) {
					ShopUser user = shopUserService.queryById(renew.getUserId());
					if(renew.getVipId() != null) {
						ShopVip vip = shopVipService.queryById(renew.getVipId());
						/*int count = shopRenewService.queryByUserId(renew.getUserId());
						if(count == 0 || renew.getVipId() != 2) {
							ShopVip vip = shopVipService.queryById(renew.getVipId());
							
							if(renew.getRechargeId() !=null) {
								
								Integer userVip = user.getUserVip();
								
								if(userVip <= vip.getVipId()) {
									user.setUserVip(vip.getVipId());
								}*/
						if(renew.getRechargeId() !=null) {
							user.setUserVip(vip.getVipId());
							if(user.getReductionDays() == null) {
								user.setReductionDays(0);
							}
									
							renew.setCreateTime(DateUtil.date2Str(new Date()));
							renew.setEndTime(DateUtil.dateAddInt(new Date(), vip.getVipPrice()));
							shopRenewService.add(renew);
									
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
									
							Date timeover = new Date();
							user.setReductionDays(user.getReductionDays() + vip.getVipPrice());
							user.setTimeOver(DateUtil.dateAddInt(timeover,user.getReductionDays()));
							shopUserService.updateBySelective(user);
							jsonMap.put("error", "0");
							jsonMap.put("msg", "成功");
							jsonMap.put("user", user);
							log.info("月卡续费成功");
						}else {
							jsonMap.put("error", "1");
							jsonMap.put("msg", "交易编号rechargeId不能为空");
								
						}
						/*}else {
							jsonMap.put("error", "1");
							jsonMap.put("msg", "该用户不能购买体验卡");
						}*/
						
					}else {
						jsonMap.put("error", "1");
						jsonMap.put("msg", "月卡类型vipId不能为空");
					}
				}else {
					jsonMap.put("error", "1");
					jsonMap.put("msg", "用户编号userId不能为空");
				}
			}else {
				jsonMap.put("error", "1");
				jsonMap.put("msg", "rechargeId错误");
			}
		} catch (Exception e) {
			jsonMap.put("error", "100");
			jsonMap.put("msg", "未知异常");
			log.error(e.getMessage(), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	
	

}
