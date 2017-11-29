package com.flf.controller.app;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.flf.service.ShopMemberService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopMemberAppController<br>
 * <br>
 */
@Controller
public class ShopMemberAppController {

	private final static Logger log = Logger.getLogger(ShopMemberAppController.class);
	@Autowired(required = false)
	private ShopMemberService shopMemberService;

}
