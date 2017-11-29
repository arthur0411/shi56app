package com.flf.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.service.BaseService;
import com.flf.entity.ShopRenew;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagService<br>
 */
public interface ShopRenewService extends BaseService {

	List<Map<String, Object>> listPage(ShopRenew vo);

	
	int queryByUserId(Integer id);


	XSSFWorkbook exportRecord();
}
