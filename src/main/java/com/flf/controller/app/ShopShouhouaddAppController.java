package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopShouhouaddService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopShouhouaddAppController<br>
 * <br>
 */
@Controller
public class ShopShouhouaddAppController {

	private final static Logger log = Logger.getLogger(ShopShouhouaddAppController.class);
	@Autowired(required = false)
	private ShopShouhouaddService shopShouhouaddService;

}
