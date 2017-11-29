package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopCommentService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommentAppController<br>
 * <br>
 */
@Controller
public class ShopCommentAppController {

	private final static Logger log = Logger.getLogger(ShopCommentAppController.class);
	@Autowired(required = false)
	private ShopCommentService shopCommentService;

}
