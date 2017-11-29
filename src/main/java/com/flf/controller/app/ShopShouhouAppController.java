package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopShouhouService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopShouhouAppController<br>
 * <br>
 */
@Controller
public class ShopShouhouAppController {

	private final static Logger log = Logger.getLogger(ShopShouhouAppController.class);
	@Autowired(required = false)
	private ShopShouhouService shopShouhouService;

}
