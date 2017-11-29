package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopFollowMapper;
import com.flf.service.ShopFollowService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopFollowService<br>
 */
@Service("shopFollowService")
public class  ShopFollowServiceImpl  extends BaseServiceImpl implements ShopFollowService {
  private final static Logger log= Logger.getLogger(ShopFollowServiceImpl.class);
	

	@Autowired
    private ShopFollowMapper dao;

		
	public ShopFollowMapper getDao() {
		return dao;
	}

	

}
