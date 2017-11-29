package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopComplainService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopComplainAppController<br>
 * <br>
 */
@Controller
public class ShopComplainAppController {

	private final static Logger log = Logger.getLogger(ShopComplainAppController.class);
	@Autowired(required = false)
	private ShopComplainService shopComplainService;

}
