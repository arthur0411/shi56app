package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopContentService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopContentAppController<br>
 * <br>
 */
@Controller
public class ShopContentAppController {

	private final static Logger log = Logger.getLogger(ShopContentAppController.class);
	@Autowired(required = false)
	private ShopContentService shopContentService;

}
