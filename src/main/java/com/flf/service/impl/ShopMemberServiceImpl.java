package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopMemberMapper;
import com.flf.service.ShopMemberService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopMemberService<br>
 */
@Service("shopMemberService")
public class  ShopMemberServiceImpl  extends BaseServiceImpl implements ShopMemberService {
  private final static Logger log= Logger.getLogger(ShopMemberServiceImpl.class);
	

	@Autowired
    private ShopMemberMapper dao;

		
	public ShopMemberMapper getDao() {
		return dao;
	}

	

}
