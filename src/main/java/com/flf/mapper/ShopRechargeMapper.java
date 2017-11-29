package com.flf.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.base.dao.BaseDao;
import com.flf.entity.ShopRecharge;
import com.flf.entity.ShopRechargeVo;

/**
 * 
 * <br>
 * <b>功能：</b>ShopRechargeDao<br>
 */
public interface ShopRechargeMapper extends BaseDao {

	ShopRecharge getByOutTradeNo(String out_trade_no);

	int updateHajRecharge(@Param("prepayId") String prepay_id, @Param("alipayTradeNo") String alipayTradeNo);

	Map<String, Object> getHajRechargeByPrepay_id(@Param("prepay_id") String prepay_id);

	List<ShopRecharge> listPage(ShopRecharge vo);

	Map<String, Object> queryTotalRecharge(ShopRechargeVo vo);

	Map<String, Object> queryTotalRefund(ShopRechargeVo vo);

	List<ShopRecharge> queryRechargeListForRerund(ShopRechargeVo vo);
	
	ShopRecharge findById(Object id);

}
