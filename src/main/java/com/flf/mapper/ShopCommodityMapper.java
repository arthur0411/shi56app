package com.flf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.criteria.Criteria;
import com.base.dao.BaseDao;
import com.flf.entity.ShopCommodity;
import com.flf.entity.ShopOrderDetails;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityDao<br>
 */
public interface ShopCommodityMapper extends BaseDao {

	List<Map<String, Object>> getCommodityIndex(Criteria criteria);

	List<Map<String, Object>> getRecommendCommodity(Criteria criteria);

	List<Map<String, Object>> getCommodityByTagId(Criteria criteria);

	List<Map<String, Object>> listPage(ShopCommodity vo);
	
	List<ShopCommodity> listcart();

	ShopCommodity getCommodityById(Integer commodityId);

	List<Map<String, Object>> getSearchCommodity(Criteria criteria);

	int updateInventoryReduce(ShopOrderDetails details);
	
	int updateRentInventoryReduce(ShopOrderDetails details);

	void updateSoldOut(Integer commodityId);
	
	void updateStock(@Param("commodityId")Integer commodityId,@Param("num") Integer num);

	List<Map<String, Object>> getBrandTopicCommodity(Criteria criteria);

	List<Map<String, Object>> getSaleStockById(@Param("commodityId") String[] commodityId);
	
	List<Map<String, Object>> getRentStockById(@Param("commodityId") String[] commodityId);
	
	String getRentStock(@Param("commodityId")Object commodityId);
	
	void deletePic(String picName);
	
	void changeBrand(@Param("brandId")String brandId,@Param("brandName")String brandName);

	void guaHui(Integer price);
	
}
