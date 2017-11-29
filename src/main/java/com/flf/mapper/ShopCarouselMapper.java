package com.flf.mapper;

import java.util.List;

import com.base.dao.BaseDao;
import com.flf.entity.ShopCarousel;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCarouselDao<br>
 */
public interface ShopCarouselMapper extends BaseDao {

	List<ShopCarousel> listPage(ShopCarousel vo);

}
