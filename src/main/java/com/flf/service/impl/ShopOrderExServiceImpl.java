package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopOrderExMapper;
import com.flf.service.ShopOrderExService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderExService<br>
 */
@Service("shopOrderExService")
public class  ShopOrderExServiceImpl  extends BaseServiceImpl implements ShopOrderExService {
  private final static Logger log= Logger.getLogger(ShopOrderExServiceImpl.class);
	

	@Autowired
    private ShopOrderExMapper dao;

		
	public ShopOrderExMapper getDao() {
		return dao;
	}

	

}
