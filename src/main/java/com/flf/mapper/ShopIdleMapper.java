package com.flf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.flf.entity.ShopIdle;

/**
 * 
 * <br>
 * <b>功能：</b>ShopIdleDao<br>
 */
public interface ShopIdleMapper extends BaseDao {

	List<Map<String,String>> listPage(ShopIdle vo);
	
	int saveShopIdle(ShopIdle shopIdle);
	
	void changeStatus(@Param("id")Integer id , @Param("status")String status);
	
	List<Map<String, Object>> getOrderTimeByUserId(Integer userId);
	
	int getIdleCount(Integer userId);
}
