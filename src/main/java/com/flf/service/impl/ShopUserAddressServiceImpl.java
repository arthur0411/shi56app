package com.flf.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopUserAddress;
import com.flf.mapper.ShopUserAddressMapper;
import com.flf.service.ShopUserAddressService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserAddressService<br>
 */
@Service("shopUserAddressService")
public class ShopUserAddressServiceImpl extends BaseServiceImpl implements ShopUserAddressService {
	private final static Logger log = Logger.getLogger(ShopUserAddressServiceImpl.class);

	@Autowired
	private ShopUserAddressMapper dao;

	@Override
	public ShopUserAddressMapper getDao() {
		return dao;
	}

	@Override
	public List<ShopUserAddress> getShopAddressList(Integer userId) {
		return dao.getShopAddressList(userId);
	}

	@Override
	public int updateDefaultAddressByUserId(Integer userId) {
		return dao.updateDefaultAddressByUserId(userId);
	}

}
