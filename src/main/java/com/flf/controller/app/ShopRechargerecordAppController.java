package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopRechargerecordService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopRechargerecordAppController<br>
 * <br>
 */
@Controller
public class ShopRechargerecordAppController {

	private final static Logger log = Logger.getLogger(ShopRechargerecordAppController.class);
	@Autowired(required = false)
	private ShopRechargerecordService shopRechargerecordService;

}
