package com.flf.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flf.entity.Info;
import com.flf.entity.Page;
import com.flf.mapper.InfoMapper;
import com.flf.service.InfoService;

@Service
public class InfoServiceImpl implements InfoService{
	@Autowired
	private InfoMapper infoMapper;
	
	public List<Info> listPageInfo(Page page) {
		// TODO Auto-generated method stub
		return infoMapper.listPageInfo(page);
	}
}
