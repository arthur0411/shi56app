package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopRecommendService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopRecommendAppController<br>
 * <br>
 */
@Controller
public class ShopRecommendAppController {

	private final static Logger log = Logger.getLogger(ShopRecommendAppController.class);
	@Autowired(required = false)
	private ShopRecommendService shopRecommendService;

}
