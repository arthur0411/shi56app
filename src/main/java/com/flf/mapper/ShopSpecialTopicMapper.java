package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopSpecialTopic;

/**
 * 
 * <br>
 * <b>功能：</b>ShopSpecialTopicDao<br>
 */
public interface ShopSpecialTopicMapper extends BaseDao {

	List<ShopSpecialTopic> listPage(ShopSpecialTopic vo);

	List<Map<String, Object>> getSpecialTopicList(int type);

}
