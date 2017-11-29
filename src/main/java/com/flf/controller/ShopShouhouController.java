package com.flf.controller;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;


import com.flf.entity.ShopShouhou;
 import com.flf.service.ShopShouhouService;
/**
 * 
 * <br>
 * <b>功能：</b>ShopShouhouController<br>
 *   <br>
 */ 
@Controller
public class ShopShouhouController {
	
	private final static Logger log= Logger.getLogger(ShopShouhouController.class);
	@Autowired(required=false) 
	private ShopShouhouService shopShouhouService; 
	
	
	
	
	

}
