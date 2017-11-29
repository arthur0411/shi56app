package com.flf.service;

import java.util.List;

import com.base.service.BaseService;
import com.flf.entity.ShopUserAddress;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserAddressService<br>
 */
public interface ShopUserAddressService extends BaseService {

	List<ShopUserAddress> getShopAddressList(Integer id);

	int updateDefaultAddressByUserId(Integer userId);

}
