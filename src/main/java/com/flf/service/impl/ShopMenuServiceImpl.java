package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopMenuMapper;
import com.flf.service.ShopMenuService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopMenuService<br>
 */
@Service("shopMenuService")
public class  ShopMenuServiceImpl  extends BaseServiceImpl implements ShopMenuService {
  private final static Logger log= Logger.getLogger(ShopMenuServiceImpl.class);
	

	@Autowired
    private ShopMenuMapper dao;

		
	public ShopMenuMapper getDao() {
		return dao;
	}

	

}
