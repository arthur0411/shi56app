package com.flf.service;

import com.base.service.BaseService;
import com.flf.entity.ShopBill;


/**
 * @author Arthur
 *
 */
public interface ShopAccountService extends BaseService {

	void updateUserBalance(Integer id);

	void updateBalance(ShopBill sb);
	
	double queryBalance(Integer id);
	
}
