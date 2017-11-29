package com.flf.service;

import java.util.List;
import java.util.Map;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.base.service.BaseService;
import com.flf.entity.ShopUser;
import com.flf.entity.ShopWithdrawals;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserService<br>
 */
public interface ShopUserService extends BaseService {

	ShopUser save(String mobilePhone, String machineNumber, String phoneModel);

	void updateLoginInfo(Integer id, String machineNumber, String phoneModel);

	String getLoginVerificationCode(String mobile);

	List<Map<String, Object>> listPage(ShopUser vo);

	Map<String, Object> queryShopUserInfoById(Integer id);

	void updateUserStatus(Integer id);

	void updateUserMoney(Integer id);

	XSSFWorkbook exportUsers();
}
