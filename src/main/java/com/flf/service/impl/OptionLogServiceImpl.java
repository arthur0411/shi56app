package com.flf.service.impl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.base.service.BaseServiceImpl;
import com.flf.mapper.OptionLogMapper;
import com.flf.service.OptionLogService;

/**
 * 
 * <br>
 * <b>功能：</b>OptionLogService<br>
 */
@Service("optionLogService")
public class  OptionLogServiceImpl  extends BaseServiceImpl implements OptionLogService {
  private final static Logger log= Logger.getLogger(OptionLogServiceImpl.class);
	

	@Autowired
    private OptionLogMapper dao;

		
	public OptionLogMapper getDao() {
		return dao;
	}

	

}
