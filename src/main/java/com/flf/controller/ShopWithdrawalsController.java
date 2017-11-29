package com.flf.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.alipay.util.AlipayUtil;
import com.base.util.HttpUtil;
import com.flf.entity.ShopRecharge;
import com.flf.entity.ShopTradingHistory;
import com.flf.entity.ShopUser;
import com.flf.entity.ShopWithdrawals;
import com.flf.service.IShopSMSPushService;
import com.flf.service.ShopRechargeService;
import com.flf.service.ShopTradingHistoryService;
import com.flf.service.ShopUserService;
import com.flf.service.ShopWithdrawalsService;
import com.flf.util.DateUtil;
import com.flf.util.ExcelUtil;
import com.flf.util.JSONUtils;
import com.weixin.PayUtil;

/**
 * 
 * <br>
 * <b>功能：</b>ShopWithdrawalsController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/withdrawals")
public class ShopWithdrawalsController {

	private final static Logger log = Logger.getLogger(ShopWithdrawalsController.class);
	@Autowired(required = false)
	private ShopWithdrawalsService shopWithdrawalsService;

	@Autowired(required = false)
	private ShopUserService shopUserService;

	@Autowired(required = false)
	private ShopTradingHistoryService shopTradingHistoryService;

	@Autowired(required = false)
	private IShopSMSPushService shopSMSPushService;

	@Autowired(required = false)
	private ShopRechargeService shopRechargeService;
	
	/**
	 * 后台退押金列表
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/getAll")
	public ModelAndView list(HttpSession session, ShopWithdrawals withdrawals) {
		ModelAndView mv = new ModelAndView();
		try {
			List<Map<String, Object>> withdrawalsList = shopWithdrawalsService.listAllOrder(withdrawals);
			mv.addObject("withdrawalsList", withdrawalsList);
			mv.addObject("withdrawals", withdrawals);
			mv.setViewName("withdrawalsList");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 确认提现
	 * 
	 */
	@RequestMapping(value = "/confirmWithdrawals")
	public void confirmWithdrawals(ShopWithdrawals withdrawals, HttpServletResponse httpServletResponse) {
		try {
			ShopRecharge cr = null;
			cr = shopRechargeService.queryById(withdrawals.getUserId());
			
			ShopWithdrawals sw = shopWithdrawalsService.queryById(withdrawals.getId());
			int wclass = sw.getWclass();
			sw.setRemark(withdrawals.getRemark());
			if(cr != null) {
				boolean flag = false;
				if(cr.getRechargeType() == 1) {
					//充值记录为微信支付
					flag = PayUtil.refund(cr.getBankNo(), "shiwuliu"+sw.getId(), (sw.getMoney().multiply(BigDecimal.valueOf(100))).intValue(), sw.getMoney().multiply(BigDecimal.valueOf(100)).intValue());
				}else {
					//充值记录为支付宝支付
					flag = AlipayUtil.refundFastpayByPlatformNopwd(cr.getAlipayTradeNo(), sw.getMoney().toString(), 1, "shiwuliu"+sw.getId(), sw.getRemark());
				}
				
				//退款成功
				if(flag) {
					
					//若为退押金则修改充值记录
					if(wclass == 0) {
						cr.setIsRefund("1");
						cr.setRefundUpdateTime(DateUtil.datetime2Str(new Date()));
						cr.setRefundNum(sw.getMoney());
						shopRechargeService.updateBySelective(cr);
					}
					
					sw.setIsConfirm(1);
					sw.setOperationTime(DateUtil.datetime2Str(new Date()));
					sw.setStatus(1);
					sw.setWclass(wclass);
					int id = shopWithdrawalsService.updateWithdrawals(sw);
					
					if (id > 0) {
						withdrawals = shopWithdrawalsService.queryById(withdrawals.getId());
						ShopTradingHistory tarding = new ShopTradingHistory();
						// 增加用户流水
						tarding.setTradingContent("余额提现成功：" + withdrawals.getMoney());
						tarding.setCreateTime(DateUtil.dateToString(new Date()));
						tarding.setMoney(withdrawals.getMoney());
						tarding.setTradingStatus(1);
						tarding.setUserId(withdrawals.getUserId());
						tarding.setType(0);// 减少
						shopTradingHistoryService.saveTradeRecord(tarding);
						
						// 提现后短信通知
						ShopUser frontUser = shopUserService.queryById(withdrawals.getUserId());
						shopSMSPushService.withdrawal(frontUser.getMobilePhone(), withdrawals.getMoney());
						
						JSONUtils.printStr("1", httpServletResponse);
					}else {
						JSONUtils.printStr("3", httpServletResponse);
					}
				}else {
						JSONUtils.printStr("3", httpServletResponse);
					}
			}else {
				JSONUtils.printStr("2", httpServletResponse);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 弹出提现备注窗口
	 */
	@RequestMapping(value = "/withdrawalsPage")
	public ModelAndView withdrawalsPage(int id, int userId, HttpServletResponse httpServletResponse) {
		ModelAndView mv = new ModelAndView();
		mv.addObject("id", id);
		mv.addObject("userId", userId);
		mv.setViewName("withdrawalsPage");
		return mv;
	}

	/**
	 * 导出excel
	 */

	@RequestMapping(value = "/excel")
	public void exportWithdrawals(ShopWithdrawals withdrawals, HttpServletRequest request, HttpServletResponse response) {
		try {
			XSSFWorkbook wb = shopWithdrawalsService.exportWithdrawals(withdrawals);
			String filename = HttpUtil.encodeFilename(request, "提现申请");
			ExcelUtil.output(response, filename, wb);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
