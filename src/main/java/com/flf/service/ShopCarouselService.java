package com.flf.service;

import java.util.List;

import com.base.service.BaseService;
import com.flf.entity.ShopCarousel;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCarouselService<br>
 */
public interface ShopCarouselService extends BaseService {

	List<ShopCarousel> listPage(ShopCarousel vo);

}
