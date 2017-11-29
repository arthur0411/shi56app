package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopShoppingCommodityService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopShoppingCommodityAppController<br>
 * <br>
 */
@Controller
public class ShopShoppingCommodityAppController {

	private final static Logger log = Logger.getLogger(ShopShoppingCommodityAppController.class);
	@Autowired(required = false)
	private ShopShoppingCommodityService shopShoppingCommodityService;

}
