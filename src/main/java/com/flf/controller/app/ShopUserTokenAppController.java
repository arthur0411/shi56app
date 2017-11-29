package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopUserTokenService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserTokenAppController<br>
 * <br>
 */
@Controller
public class ShopUserTokenAppController {

	private final static Logger log = Logger.getLogger(ShopUserTokenAppController.class);
	@Autowired(required = false)
	private ShopUserTokenService shopUserTokenService;

}
