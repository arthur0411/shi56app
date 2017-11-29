package com.flf.controller.app;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.flf.entity.ShopBill;
import com.flf.entity.ShopTradingHistory;
import com.flf.entity.ShopUser;
import com.flf.entity.ShopWithdrawals;
import com.flf.service.ShopAccountService;
import com.flf.service.ShopTradingHistoryService;
import com.flf.service.ShopUserService;
import com.flf.service.ShopWithdrawalsService;
import com.flf.util.DateUtil;
import com.flf.util.JSONUtils;


/**
 * @author Arthur
 *
 */
@Controller
@RequestMapping(value = "/shopAccountApp")
public class ShopAccountAppController {

	private final static Logger log = Logger.getLogger(ShopAccountAppController.class);
	
	@Autowired
	private ShopAccountService shopAccountService;
	
	@Autowired(required = false)
	private ShopWithdrawalsService shopWithdrawalsService;

	@Autowired(required = false)
	private ShopUserService shopUserService;

	@Autowired(required = false)
	private ShopTradingHistoryService shopTradingHistoryService;
	
	
	@RequestMapping(value = "/showAccount")
	public void showAccount(Integer userId,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("查询账户余额");
			
			ShopUser su = shopUserService.queryById(userId);
			jsonMap.put("balance", su.getBalance());
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			try {
				
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 更改账户余额
	 */
	@RequestMapping(value = "/updateAccount")
	public void updateAccount(ShopBill sb,
			HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("修改账户账单");

			shopAccountService.update(sb);
			
			ShopUser su = shopUserService.queryById(sb.getUserId());
			
			jsonMap.put("user", su);
		} catch (Exception e) {
		
			e.printStackTrace();
		} finally {
			try {
				
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	 
	/*
	 * 账户提现
	 */
	@RequestMapping(value = "/clearAccount")
	public void clearAccount(Integer id,HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("账户提现");
			if(shopAccountService.queryBalance(id) < 200) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "不足200，提现失败");
			}else {
				ShopUser user = shopUserService.queryById(id);
				ShopWithdrawals withdrawals = new ShopWithdrawals();
				withdrawals.setUserId(id);
				withdrawals.setWclass(1);
				withdrawals.setCreateTime(DateUtil.datetime2Str(new Date()));
				withdrawals.setIsConfirm(0); // 财务是否确认（1是0否）
				withdrawals.setStatus(0); // 是否成功（1是0否）
				withdrawals.setMoney(user.getBalance());
				
				int count = shopWithdrawalsService.add(withdrawals);
				if (count > 0) {
					// 更新用户金额为0
					shopAccountService.updateUserBalance(id);

					// 增加用户流水
					ShopTradingHistory trading = new ShopTradingHistory();
					trading.setTradingContent("余额提现中：" + withdrawals.getMoney());
					trading.setCreateTime(DateUtil.dateToString(new Date()));
					trading.setMoney(withdrawals.getMoney());
					trading.setTradingStatus(1);
					trading.setUserId(withdrawals.getUserId());
					trading.setType(0);// 减少
					shopTradingHistoryService.saveTradeRecord(trading);

					jsonMap.put("user", user);
					jsonMap.put("error", "0");
					jsonMap.put("msg", "提现成功");
				} else {
					jsonMap.put("msg", "失败");
				}
				
			}
			
		} catch (Exception e) {
			jsonMap.put("msg", "失败");
			e.printStackTrace();
		} finally {
			try {
				
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		 
	 }

}
