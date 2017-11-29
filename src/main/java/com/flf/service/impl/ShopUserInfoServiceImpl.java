package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopUserInfoMapper;
import com.flf.service.ShopUserInfoService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserInfoService<br>
 */
@Service("shopUserInfoService")
public class  ShopUserInfoServiceImpl  extends BaseServiceImpl implements ShopUserInfoService {
  private final static Logger log= Logger.getLogger(ShopUserInfoServiceImpl.class);
	

	@Autowired
    private ShopUserInfoMapper dao;

		
	public ShopUserInfoMapper getDao() {
		return dao;
	}

	

}
