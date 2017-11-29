package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopShouhouDetalisService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopShouhouDetalisAppController<br>
 * <br>
 */
@Controller
public class ShopShouhouDetalisAppController {

	private final static Logger log = Logger.getLogger(ShopShouhouDetalisAppController.class);
	@Autowired(required = false)
	private ShopShouhouDetalisService shopShouhouDetalisService;

}
