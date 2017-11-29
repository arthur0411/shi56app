package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopRenew;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagDao<br>
 */
public interface ShopRenewMapper extends BaseDao {

	List<Map<String, Object>> listPage(ShopRenew vo);
	
	int queryByUserId(Integer id);

	List<Map<String, Object>> exportRecord();


}
