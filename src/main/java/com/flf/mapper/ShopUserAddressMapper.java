package com.flf.mapper;

import java.util.List;

import com.base.dao.BaseDao;
import com.flf.entity.ShopUserAddress;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserAddressDao<br>
 */
public interface ShopUserAddressMapper extends BaseDao {

	List<ShopUserAddress> getShopAddressList(Integer userId);

	int updateDefaultAddressByUserId(Integer userId);

}
