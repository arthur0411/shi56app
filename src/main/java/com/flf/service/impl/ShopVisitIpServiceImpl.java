package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopVisitIpMapper;
import com.flf.service.ShopVisitIpService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopVisitIpService<br>
 */
@Service("shopVisitIpService")
public class  ShopVisitIpServiceImpl  extends BaseServiceImpl implements ShopVisitIpService {
  private final static Logger log= Logger.getLogger(ShopVisitIpServiceImpl.class);
	

	@Autowired
    private ShopVisitIpMapper dao;

		
	public ShopVisitIpMapper getDao() {
		return dao;
	}

	

}
