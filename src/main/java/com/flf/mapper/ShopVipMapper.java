package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopVip;

/**
 * 
 * <br>
 * <b>功能：</b>ShopVipDao<br>
 */
public interface ShopVipMapper extends BaseDao {

	List<Map<String, Object>> getShopVipList();

	List<ShopVip> listPage(ShopVip vo);

	ShopVip getShopVipByName(String vipName);

}
