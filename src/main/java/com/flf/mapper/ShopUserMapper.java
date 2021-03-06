package com.flf.mapper;

import java.util.List;
import java.util.Map;

import com.base.dao.BaseDao;
import com.flf.entity.ShopUser;
import com.flf.entity.ShopWithdrawals;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserDao<br>
 */
public interface ShopUserMapper extends BaseDao {

	void updateUserMoneyByRecharge(ShopUser user);

	List<Map<String, Object>> listPage(ShopUser vo);

	Map<String, Object> queryShopUserInfoById(Integer id);

	void updateUserStatus(Integer id);

	void updateUserMoney(Integer id);

	void updateUserDepositByRecharge(ShopUser user);

	List<Map<String, Object>> exportUsers();
}
