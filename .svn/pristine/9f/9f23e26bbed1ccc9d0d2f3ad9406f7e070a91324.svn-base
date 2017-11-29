package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopBill;
import com.flf.mapper.ShopAccountMapper;
import com.flf.service.ShopAccountService;

/**
 * @author Arthur
 *
 */
@Service("shopAccountService")
public class ShopAccountServiceImpl extends BaseServiceImpl implements ShopAccountService {
	private final static Logger log = Logger.getLogger(ShopAccountServiceImpl.class);

	@Autowired
	private ShopAccountMapper dao;

	@Override
	public ShopAccountMapper getDao() {
		return dao;
	}

	@Override
	public void updateUserBalance(Integer id) {
		dao.updateUserBalance(id);
	}

	@Override
	public void updateBalance(ShopBill sb) {
		dao.update(sb);
	}

	@Override
	public double queryBalance(Integer id) {
		return dao.queryBalance(id);
	}

}