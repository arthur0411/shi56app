package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopSpecialTopic;
import com.flf.mapper.ShopSpecialTopicMapper;
import com.flf.service.ShopSpecialTopicService;

/**
 * 
 * <br>
 * <b>功能：</b>ShopSpecialTopicService<br>
 */
@Service("shopSpecialTopicService")
public class ShopSpecialTopicServiceImpl extends BaseServiceImpl implements ShopSpecialTopicService {
	private final static Logger log = Logger.getLogger(ShopSpecialTopicServiceImpl.class);

	@Autowired
	private ShopSpecialTopicMapper dao;

	@Override
	public ShopSpecialTopicMapper getDao() {
		return dao;
	}

	@Override
	public List<ShopSpecialTopic> listPage(ShopSpecialTopic vo) {
		return dao.listPage(vo);
	}

	@Override
	public List<Map<String, Object>> getSpecialTopicList(int type) {
		return dao.getSpecialTopicList(type);
	}

}
