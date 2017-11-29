package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopJifenbilService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopJifenbilAppController<br>
 * <br>
 */
@Controller
public class ShopJifenbilAppController {

	private final static Logger log = Logger.getLogger(ShopJifenbilAppController.class);
	@Autowired(required = false)
	private ShopJifenbilService shopJifenbilService;

}
