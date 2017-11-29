package com.flf.mapper;

import com.base.dao.BaseDao;


/**
 * @author Arthur
 *
 */
public interface ShopAccountMapper extends BaseDao {

	void updateUserBalance(Integer id);
	
	double queryBalance(Integer id);

}
