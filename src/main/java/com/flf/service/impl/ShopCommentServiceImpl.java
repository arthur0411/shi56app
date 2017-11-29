package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.ShopCommentMapper;
import com.flf.service.ShopCommentService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommentService<br>
 */
@Service("shopCommentService")
public class  ShopCommentServiceImpl  extends BaseServiceImpl implements ShopCommentService {
  private final static Logger log= Logger.getLogger(ShopCommentServiceImpl.class);
	

	@Autowired
    private ShopCommentMapper dao;

		
	public ShopCommentMapper getDao() {
		return dao;
	}

	

}
