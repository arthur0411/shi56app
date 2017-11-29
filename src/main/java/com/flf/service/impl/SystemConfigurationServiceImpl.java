package com.flf.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.SystemConfiguration;
import com.flf.mapper.SystemConfigurationMapper;
import com.flf.service.SystemConfigurationService;

/**
 * 
 * <br>
 * <b>功能：</b>SystemConfigurationService<br>
 */
@Service("systemConfigurationService")
public class SystemConfigurationServiceImpl extends BaseServiceImpl implements SystemConfigurationService {

	@Autowired
	private SystemConfigurationMapper dao;

	@Override
	public SystemConfigurationMapper getDao() {
		return dao;
	}

	public List<SystemConfiguration> listPageConfig(SystemConfiguration configuration) {
		return dao.listPageConfig(configuration);
	}

	public String getValueByName(String name) {
		return dao.getValueByName(name);
	}

	public SystemConfiguration getConfigurationByName(String name) {
		return dao.getConfigurationByName(name);
	}

}
