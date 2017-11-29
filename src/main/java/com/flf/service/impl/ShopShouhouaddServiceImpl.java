package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopShouhouaddMapper;
import com.flf.service.ShopShouhouaddService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopShouhouaddService<br>
 */
@Service("shopShouhouaddService")
public class  ShopShouhouaddServiceImpl  extends BaseServiceImpl implements ShopShouhouaddService {
  private final static Logger log= Logger.getLogger(ShopShouhouaddServiceImpl.class);
	

	@Autowired
    private ShopShouhouaddMapper dao;

		
	public ShopShouhouaddMapper getDao() {
		return dao;
	}

	

}
