package com.flf.controller.app;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.base.criteria.Criteria;
import com.flf.entity.ShopOrder;
import com.flf.entity.ShopOrderDetails;
import com.flf.entity.ShopUser;
import com.flf.service.RedisSpringProxy;
import com.flf.service.ShopOrderDetailsService;
import com.flf.service.ShopOrderService;
import com.flf.service.ShopUserService;
import com.flf.service.SystemConfigurationService;
import com.flf.util.DateUtil;
import com.flf.util.JSONUtils;
import com.flf.util.MakeOrderNum;

import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.PropertyFilter;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopOrderApp")
public class ShopOrderAppController {

	private final static Logger log = Logger.getLogger(ShopOrderAppController.class);
	@Autowired(required = false)
	private ShopOrderService shopOrderService;
	
	@Autowired(required = false)
	private ShopUserService shopUserService;
	
	@Autowired(required = false)
	private SystemConfigurationService systemConfigurationService;

	@Autowired
	private RedisSpringProxy redisSpringProxy;

	@Autowired(required = false)
	private ShopOrderDetailsService shopOrderDetailsService;

	@RequestMapping(value = "/orderDetail")
	public void orderDetail(@RequestParam int orderId,HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		// 获取订单信息
		jsonMap = shopOrderService.getOrderInfoById(orderId);

		// 获取订单详情信息
		List<Map<String, Object>> detailsList = shopOrderDetailsService.listAllOrderDetails(orderId);

		
		jsonMap.put("HajOrder", detailsList);
		
		JSONUtils.printObject(jsonMap, response);
	}
	
	/**
	 * 获取订单详情接口
	 *
	 * @param userId
	 * @param sign
	 * @param createTime
	 * @param orderNo
	 * @param response
	 * @author SevenWong<br>
	 * @date 2016年9月30日上午11:53:06
	 */
	@RequestMapping(value = "/getOrderInfo")
	public void getOrderInfo(String orderNo, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			ShopOrder order = shopOrderService.getOrderByOrderNo(orderNo);

			JsonConfig config = new JsonConfig();

			config.setJsonPropertyFilter(new PropertyFilter() {
				@Override
				public boolean apply(Object arg0, String arg1, Object arg2) {
					if (arg1.equals("orderId")) {
						return true;
					} else {
						return false;
					}
				}
			});

			if (null != order) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
				jsonMap.put("HajOrder", order);
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
				jsonMap.put("HajOrder", "");
			}

			JSONUtils.printObject(jsonMap, response, config);

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

	/**
	 * 查询订单时间app接口(历史订单)<br>
	 *
	 * @param userId
	 * @param currentPage
	 *            当前页码
	 * @param pageSize
	 *            当前页大小
	 * @param sign
	 * @param response
	 * @date 2016年6月8日下午6:57:49
	 */
	@RequestMapping(value = "/getOrderTime")
	public void getOrderTime(@RequestParam Integer userId, @RequestParam Integer currentPage,
			@RequestParam Integer pageSize, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Criteria criteria = new Criteria();
		criteria.setPageSize(pageSize);
		criteria.setCurrentPage(currentPage);

		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("userId", userId);
		criteria.setCondition(condition);
		try {
			// 签名认证
			List<Map<String, Object>> HajOrderTime = shopOrderService.getOrderTimeByUserId(criteria);

			if (HajOrderTime.size() > 0) {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
				jsonMap.put("HajOrderTime", HajOrderTime);
				jsonMap.put("page", criteria);
			} else {
				jsonMap.put("error", "0");
				jsonMap.put("msg", "为空");
				jsonMap.put("HajOrderTime", "");
				jsonMap.put("page", criteria);
			}

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			jsonMap.put("page", criteria);
			log.error(e.getMessage(), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 添加订单接口，确认下单
	 * @author Arthur
	 *
	 */
	@RequestMapping(value = "/addOrderapp")
	public void addOrderApp(@RequestParam String orderStr, HttpServletResponse response) {
		JSONObject jsonObj = JSONObject.fromObject(orderStr);

		Map<String, Class> classMap = new HashMap<>();
		classMap.put("orderDetailList", ShopOrderDetails.class);
		// 将JSON转换成Order
		ShopOrder order = (ShopOrder) JSONObject.toBean(jsonObj, ShopOrder.class, classMap);
		order.setOrderClass("1");
		String orderNo = null;
		Map<String, Object> jsonMap = new HashMap<>();
		try {
			orderNo = MakeOrderNum.makeOrderNum();
			// 添加订单
			Map<String, Object> map = shopOrderService.saveShopOrder(order, orderNo);
			int addStatus = Integer.parseInt(map.get("resultId").toString());
			log.info("【" + orderNo + "】下单成功!addStatus：" + addStatus);

			String msg;
			switch (addStatus) {
			case 0:
				msg = "成功";
				break;
			case 1:
				msg = "账户未缴押金，无法下单";
				break;
			case 2:
				msg = "您的账号未认证";
				break;
			case 3:
				msg = "订单中包含下架商品";
				break;
			case 4:
				msg = "您的账号有未完成的订单";
				break;
			case 5:
				msg = "您的年卡待续费";
				break;
			case 6:
				msg = map.get("commodityName") + "暂无库存！";
				break;
			case 7:
				msg = "仅可租用一件商品";
				break;
			case 8:
				msg = "您的年卡不足三天不能下单";
				break;
			default:
				msg = "失败";
				break;
			}
			jsonMap.put("error", String.valueOf(addStatus));
			jsonMap.put("msg", msg);

			jsonMap.put("orderNo", orderNo);
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

	/*
	 * 添加购买订单
	 *  @author Arthur
	 * 
	 * 
	 */
	@RequestMapping(value = "/addSaleOrderapp")
	public void addSaleOrderApp(@RequestParam String orderStr, HttpServletResponse response) {
		JSONObject jsonObj = JSONObject.fromObject(orderStr);

		Map<String, Class> classMap = new HashMap<>();
		classMap.put("orderDetailList", ShopOrderDetails.class);
		// 将JSON转换成Order
		ShopOrder order = (ShopOrder) JSONObject.toBean(jsonObj, ShopOrder.class, classMap);
		order.setOrderClass("2");
		String orderNo = null;
		Map<String, Object> jsonMap = new HashMap<>();
		try {
			orderNo = MakeOrderNum.makeOrderNum();
			// 添加订单
			Map<String, Object> map = shopOrderService.saveSaleOrder(order, orderNo);
			int addStatus = Integer.parseInt(map.get("resultId").toString());
			log.info("【" + orderNo + "】下单成功!addStatus：" + addStatus);

			String msg;
			switch (addStatus) { 
			case 0:
				msg = "下单成功，请于5分钟内支付，过时订单将自动取消";
				break;
			case 3:
				msg = "订单中包含下架商品";
				break;
			case 6:
				msg = map.get("commodityName") +  "暂无库存！" ;
				break;
			default:
				msg = "失败";
				break;
			}
			jsonMap.put("error", String.valueOf(addStatus));
			jsonMap.put("msg", msg);

			jsonMap.put("orderNo", orderNo);
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
	 
	
	@RequestMapping(value = "/updateSaleOrder")
	public void updateSaleOrder(@RequestParam String orderNo, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<>();
		try {
			ShopOrder order = shopOrderService.getOrderByOrderNo(orderNo);
			order.setOrderStatus(1);
			order.setIsPayment("yes");
			//order.setPaymentTime(DateUtil.datetime2Str(new Date()));
			boolean inventoryIsEnough = shopOrderService.updateInventoryReduce(order);
			
			if(!inventoryIsEnough) {
				log.info("该订单包含库存不足的商品");
				jsonMap.put("error", "1");
				jsonMap.put("msg"," 该订单包含库存不足的商品,请重新下单");
				shopOrderService.delete(order.getOrderId());
			}else {
				jsonMap.put("error", "0");
				jsonMap.put("msg"," 成功");
				shopOrderService.updateBySelective(order);
			}
				jsonMap.put("orderNo", orderNo);
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
	
	
	/**
	 * 验证用户是否能继续下单
	 * 
	 * @param userId
	 */
	@RequestMapping(value = "/getOrderNumbserByUserId")
	public void getOrderNumbserByUserId(Integer userId, HttpServletResponse response) {
		boolean isTrue = false;
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (null != userId && userId > 0) {

			String disableOrder = (String) redisSpringProxy.read("SystemConfiguration_disableOrder");
			boolean disableOrderFlag = Boolean.parseBoolean(disableOrder);
			if (disableOrderFlag) {
				int orderNumber = shopOrderService.isOrderByUserId(userId);
				if (orderNumber > 0) {
					log.info("userId：" + userId + "还有未完成订单！");
				} else {
					isTrue = true;
				}
			} else {
				isTrue = true;
			}
			jsonMap.put("error", "0");
			jsonMap.put("msg", "成功");
		} else {
			jsonMap.put("error", "0");
			jsonMap.put("msg", "成功");
		}
		jsonMap.put("isTrue", isTrue);
		try {
			JSONUtils.printObject(jsonMap, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}

	}

	/*
	 * 结算账单
	 */
	@RequestMapping(value = "/settlementOrder")
	public void settlementOrder(@RequestParam String orderNo,@RequestParam Double actualPayment, @RequestParam String returnExpress,
			@RequestParam String returnExpressCode, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try{
			ShopOrder order = shopOrderService.getOrderByOrderNo(orderNo);
			if (null != order && order.getOrderStatus() == 3) {
				order.setReturnExpress(returnExpress);
				order.setReturnExpressCode(returnExpressCode);
				order.setOrderStatus(4);
				order.setReturnTime(DateUtil.date2Str(new Date()));
				shopOrderService.updateBySelective(order);
				log.info("订单[" + orderNo + "],结算成功！");
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
				jsonMap.put("orderNo", orderNo);
			}else {
				jsonMap.put("error", "3");
				jsonMap.put("msg", "订单[" + orderNo + "]不是佩戴中的订单！");
			}
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			log.error(jsonMap.get("msg"), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	/**
	 * 返还orderNo 订单号 actualPayment 结算金额 returnExpress 物流公司 returnExpressCode
	 * 物流公司编号
	 */
	/*@RequestMapping(value = "/settlementOrder")
	public void settlementOrder(@RequestParam String orderNo,@RequestParam Double actualPayment,@RequestParam int paymentWay, @RequestParam String returnExpress,
			@RequestParam String returnExpressCode, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			ShopOrder order = shopOrderService.getOrderByOrderNo(orderNo);
			if (null != order && order.getOrderStatus() == 3) {
				// 订单佩戴天
				log.info("结算并返回：系统计算的消费金额：" + order.getActualPayment());
				ShopOrder orderN = new ShopOrder();
				orderN.setOrderNummmm(orderNo);
				orderN.setActualPayment(BigDecimal.valueOf(actualPayment));
				orderN.setReturnTime(DateUtil.datetime2Str(new Date()));
				orderN.setReturnExpress(returnExpress);
				orderN.setReturnExpressCode(returnExpressCode);
				orderN.setIsPayment("yes");
				orderN.setOrderStatus(4);
				orderN.setPaymentWay(paymentWay);
				orderN.setPaymentTime(DateUtil.datetime2Str(new Date()));
				orderN.setOrderId(order.getOrderId());
				shopOrderService.updateBySelective(orderN);
				log.info("订单[" + orderNo + "],更新成功！");
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
				jsonMap.put("orderNo", orderNo);
			
			} else {
				jsonMap.put("error", "3");
				jsonMap.put("msg", "订单[" + orderNo + "]不是佩戴中的订单！");
			}
				int wearingDay = this.getOrderWearingDays(order);
				int wearingDays = Integer.parseInt(redisSpringProxy.read("SystemConfiguration_order_wearingDays")
						.toString());
				
				double totalPayment = this.getOrderTotalPayment(order);
				log.info("结算并返回：系统计算的消费金额：" + totalPayment + ",APP结算的费用：" + actualPayment);
				if (totalPayment == actualPayment) {
						ShopOrder orderN = new ShopOrder();
						orderN.setOrderNummmm(orderNo);
						// orderN.setWearingDays(0);
						orderN.setWearingDays(wearingDay);
						orderN.setReturnTime(DateUtil.datetime2Str(new Date()));
						orderN.setReturnExpress(returnExpress);
						orderN.setReturnExpressCode(returnExpressCode);
						orderN.setOrderStatus(4);
						orderN.setPaymentWay(paymentWay);
						orderN.setPaymentTime(DateUtil.datetime2Str(new Date()));
						orderN.setOrderId(order.getOrderId());
						orderN.setTotalMoney(BigDecimal.valueOf(totalPayment));
						
					if ((order.getWearingDays()-3) >= wearingDays) {
						orderN.setActualPayment(BigDecimal.valueOf(actualPayment));
						log.info("订单[" + orderNo + "],结算成功！");
						jsonMap.put("error", "0");
						jsonMap.put("msg", "成功");
						jsonMap.put("orderNo", orderNo);
					} else {
						orderN.setActualPayment(BigDecimal.valueOf(actualPayment).add(BigDecimal.valueOf(10)));
						orderN.setPostFee(BigDecimal.valueOf(10));
						jsonMap.put("error", "2");
						jsonMap.put("msg", "订单[" + orderNo + "]佩戴未超过【" + wearingDays + "】天，结算邮费自理！");
						jsonMap.put("orderNo", orderNo);
					}
					shopOrderService.updateBySelective(orderN);
				} else {
					jsonMap.put("error", "4");
					jsonMap.put("msg", "订单[" + orderNo + "]结算金额异常！");
					jsonMap.put("totalPayment", totalPayment);
				}

			} else {
				jsonMap.put("error", "3");
				jsonMap.put("msg", "订单[" + orderNo + "]不是佩戴中的订单！");
			}

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			log.error(jsonMap.get("msg"), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}*/

	private double getOrderTotalPayment(ShopOrder order) {
		
		BigDecimal commodityrent = BigDecimal.ZERO;
		List<Map<String, Object>> mapList = shopOrderDetailsService.getCommodityrentByOrderId(order.getOrderId());
		for (Map<String, Object> map : mapList) {
			if (null != map.get("commodityrent")) {
				commodityrent = BigDecimal.valueOf(Double.parseDouble(map.get("commodityrent").toString())).add(
						commodityrent);
			}
		}
		return commodityrent.doubleValue() * (order.getWearingDays()-3);
	}

	
	private int getOrderWearingDays(ShopOrder order) {
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			long s1 = sdf.parse(order.getCreateTime()).getTime();
			long s2 = new Date().getTime();
			int  day = (int) ((s2-s1)/1000/60/60/24);
			
			return day;
		} catch (ParseException e) {
			e.printStackTrace();
			return 0;
		}
		
	}
	
	@RequestMapping(value = "/discountOrder")
	public void discountOrder(@RequestParam String userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		String discount = "1";
		try{
			ShopUser shopUser = shopUserService.queryById(userId);
			int num = shopOrderService.queryOrderByUserId(Integer.parseInt(userId));
			if(shopUser.getUserVip() == 2) {
				if(num == 0) {
					discount = systemConfigurationService.getValueByName("vip_firstDiscount");
					jsonMap.put("msg", "会员首单尊享"+ Double.parseDouble(discount)*10+ "折");
				}else {
					discount = systemConfigurationService.getValueByName("vip_discount");
					jsonMap.put("msg", "会员非首单享受"+ Double.parseDouble(discount)*10+ "折");
				}
			}else {
				if(num == 0) {
					discount = systemConfigurationService.getValueByName("common_firstDiscount");
					jsonMap.put("msg", "非会员首单享受"+ Double.parseDouble(discount)*10+ "折");
				}else {
					discount = systemConfigurationService.getValueByName("common_discount");
					jsonMap.put("msg", "非会员非首单享受"+ Double.parseDouble(discount)*10+ "折");
				}
			}
			jsonMap.put("error", "0");
			jsonMap.put("discount", discount);
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			log.error(jsonMap.get("msg"), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
