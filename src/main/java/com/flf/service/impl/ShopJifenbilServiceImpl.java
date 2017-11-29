package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopJifenbilMapper;
import com.flf.service.ShopJifenbilService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopJifenbilService<br>
 */
@Service("shopJifenbilService")
public class  ShopJifenbilServiceImpl  extends BaseServiceImpl implements ShopJifenbilService {
  private final static Logger log= Logger.getLogger(ShopJifenbilServiceImpl.class);
	

	@Autowired
    private ShopJifenbilMapper dao;

		
	public ShopJifenbilMapper getDao() {
		return dao;
	}

	

}
