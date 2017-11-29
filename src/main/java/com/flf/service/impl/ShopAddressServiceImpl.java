package com.flf.service.impl;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopAddress;
import com.flf.mapper.ShopAddressMapper;
import com.flf.service.ShopAddressService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopAddressService<br>
 */
@Service("shopAddressService")
public class ShopAddressServiceImpl extends BaseServiceImpl implements ShopAddressService {
	private final static Logger log = Logger.getLogger(ShopAddressServiceImpl.class);

	@Autowired
	private ShopAddressMapper dao;

	@Override
	public ShopAddressMapper getDao() {
		return dao;
	}

	@Override
	public List<ShopAddress> getAreaProvinceList() {
		return dao.getAreaProvinceList();
	}

	@Override
	public List<ShopAddress> getAreaByPCode(String code) {
		return dao.getAreaByPCode(code);
	}

}
