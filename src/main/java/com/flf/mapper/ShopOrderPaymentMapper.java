package com.flf.mapper;

import java.util.List;

import com.base.dao.BaseDao;
import com.flf.entity.ShopOrderPayment;
import com.flf.entity.ShopOrderPaymentVo;

/**
 * 
 * <br>
 * <b>功能：</b>ShopOrderPaymentDao<br>
 */
public interface ShopOrderPaymentMapper extends BaseDao {

	int getCount(ShopOrderPayment dto);

	List<ShopOrderPaymentVo> list(ShopOrderPaymentVo vo);

}
