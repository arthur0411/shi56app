package com.flf.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopRecharge;
import com.flf.entity.ShopRechargeVo;
import com.flf.entity.User;
import com.flf.service.ShopRechargeService;
import com.flf.util.Const;
import com.flf.util.JSONUtils;
import com.flf.util.ShopRefund;

/**
 * 
 * <br>
 * <b>功能：</b>ShopRechargeController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/recharge")
public class ShopRechargeController {

	private final static Logger log = Logger.getLogger(ShopRechargeController.class);
	@Autowired(required = false)
	private ShopRechargeService shopRechargeService;

	@RequestMapping(value = "/list")
	public ModelAndView list(HttpSession session, ShopRechargeVo vo) throws Exception {
		ModelAndView mv = new ModelAndView();

		List<ShopRecharge> rechargeList = shopRechargeService.list(vo);
		mv.addObject("rechargeList", rechargeList);
		mv.addObject("vo", vo);
		mv.setViewName("rechargeList");
		Map<String, Object> totalRechargee = shopRechargeService.queryTotalRecharge(vo);
		Map<String, Object> totalRefund = new HashMap<>();
		if (vo.getRechargeStatus() != null && vo.getRechargeStatus() == 0) {
			totalRefund.put("sumRefundNum", "0.00");
		} else {
			totalRefund = shopRechargeService.queryTotalRefund(vo);
		}

		mv.addObject("totalRechargee", totalRechargee);
		mv.addObject("totalRefund", totalRefund);
		return mv;
	}

	/**
	 * 根据查询条件查询退款总金额
	 * 
	 */
	@RequestMapping(value = "/querryRefund")
	public void querryRefundTotalMoney(HttpSession session, ShopRechargeVo vo, HttpServletResponse response) {
		// 是否需要从客户端查询(1需要0不需要)
		int needQuery = 1;
		Map<String, Object> totalRefund = new HashMap<>();
		if (vo != null && vo.getRechargeStatus() != null) {
			needQuery = vo.getRechargeStatus();
		}

		if (needQuery == 1) {
			User user = (User) session.getAttribute(Const.SESSION_USER);
			if (null != user) {
				if (vo == null) {
					vo = new ShopRechargeVo();
				}
			}

			List<ShopRecharge> refundList = shopRechargeService.queryRechargeListForRerund(vo);
			if (refundList != null && refundList.size() > 0) {
				// 创建线程池
				ExecutorService executor = Executors.newCachedThreadPool();
				for (ShopRecharge ShopRecharge : refundList) {
					ShopRefund myTask = new ShopRefund(ShopRecharge, shopRechargeService);
					executor.execute(myTask);
				}
				executor.shutdown();
				System.out.println("Finished all threads");
			}
			totalRefund = shopRechargeService.queryTotalRefund(vo);
		} else {
			totalRefund.put("sumRefundNum", "0.00");
		}
		try {
			JSONUtils.printObject("{totalRefund:" + totalRefund + "}", response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
