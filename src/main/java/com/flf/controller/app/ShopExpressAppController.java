package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopExpressService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopExpressAppController<br>
 * <br>
 */
@Controller
public class ShopExpressAppController {

	private final static Logger log = Logger.getLogger(ShopExpressAppController.class);
	@Autowired(required = false)
	private ShopExpressService shopExpressService;

}
