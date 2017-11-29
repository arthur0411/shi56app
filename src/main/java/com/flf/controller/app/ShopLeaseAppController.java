package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopLeaseService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopLeaseAppController<br>
 * <br>
 */
@Controller
public class ShopLeaseAppController {

	private final static Logger log = Logger.getLogger(ShopLeaseAppController.class);
	@Autowired(required = false)
	private ShopLeaseService shopLeaseService;

}
