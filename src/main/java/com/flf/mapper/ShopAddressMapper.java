package com.flf.mapper;

import java.util.List;

import com.base.dao.BaseDao;
import com.flf.entity.ShopAddress;

/**
 * 
 * <br>
 * <b>功能：</b>ShopAddressDao<br>
 */
public interface ShopAddressMapper extends BaseDao {

	List<ShopAddress> getAreaProvinceList();

	List<ShopAddress> getAreaByPCode(String code);

}
