package com.flf.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopUser;
import com.flf.entity.ShopWithdrawals;
import com.flf.mapper.ShopUserMapper;
import com.flf.service.RedisSpringProxy;
import com.flf.service.ShopUserService;
import com.flf.util.Config;
import com.flf.util.SMSUtil;
import com.flf.util.Tools;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserService<br>
 */
@Service("shopUserService")
public class ShopUserServiceImpl extends BaseServiceImpl implements ShopUserService {
	private final static Logger log = Logger.getLogger(ShopUserServiceImpl.class);

	@Autowired
	private ShopUserMapper dao;
	@Autowired
	private RedisSpringProxy redisSpringProxy;

	public static final String SMS_URL = Config.getSmsProperties("sms.url");
	public static final String SMS_APPKEY = Config.getSmsProperties("sms.appkey");
	public static final String SMS_SECRET = Config.getSmsProperties("sms.secret");
	public static final String SMS_FREE_SIGN_NAME = Config.getSmsProperties("sms.free.sign.name");

	/** 登录短信验证码模版ID */
	public static final String SMS_TEMPLATE_LOGIN_CODE = Config.getSmsProperties("sms.template.login.code");

	@Override
	public ShopUserMapper getDao() {
		return dao;
	}

	@Override
	public ShopUser save(String mobilePhone, String machineNumber, String phoneModel) {
		ShopUser user = new ShopUser();
		user.setName(mobilePhone);
		user.setType(1);
		user.setStatus(0);
		user.setRegisterTime(new Date());
		user.setMachineNumber(machineNumber);
		user.setPhoneModel(phoneModel);
		user.setLastLoginTime(new Date());
		user.setMobilePhone(mobilePhone);
		user.setBalance(BigDecimal.ZERO);
		user.setDeposit(BigDecimal.ZERO);
		user.setRenzhen_status(0);
		user.setUserVip(0);

		dao.add(user);
		return user;
	}

	@Override
	public void updateLoginInfo(Integer id, String machineNumber, String phoneModel) {
		if (id != null && id > 0) {
			ShopUser user = new ShopUser();
			user.setId(id);
			user.setMachineNumber(machineNumber);
			user.setPhoneModel(phoneModel);
			user.setLastLoginTime(new Date());
			dao.updateBySelective(user);
		}
	}

	@Override
	public String getLoginVerificationCode(String mobile) {
		String code = RandomStringUtils.randomNumeric(4);
		log.info("给手机号" + mobile + "生成的验证码:" + code);
		redisSpringProxy.saveEx("login_code_" + mobile, 5 * 60L, code);
		String smsParamString = "{\"code\":\"" + code + "\",\"product\":\"" + SMS_FREE_SIGN_NAME + "\"}";
		return SMSUtil.sendSms(SMS_FREE_SIGN_NAME, smsParamString, SMS_TEMPLATE_LOGIN_CODE, mobile);
	}

	@Override
	public List<Map<String, Object>> listPage(ShopUser vo) {
		return dao.listPage(vo);
	}

	@Override
	public Map<String, Object> queryShopUserInfoById(Integer id) {
		return dao.queryShopUserInfoById(id);
	}

	@Override
	public void updateUserStatus(Integer id) {
		dao.updateUserStatus(id);
	}

	@Override
	public void updateUserMoney(Integer id) {
		dao.updateUserMoney(id);
	}
	
	@Override
	public XSSFWorkbook exportUsers() {

		// 创建HSSFWorkbook对象(excel的文档对象)
		XSSFWorkbook wkb = new XSSFWorkbook();

		// 建立新的sheet对象（excel的表单）
		XSSFSheet sheet = wkb.createSheet();
		sheet.setDefaultColumnWidth(12);
		sheet.setDefaultRowHeightInPoints(20);

		// 在sheet里创建第1行
		XSSFRow row = sheet.createRow(0);

		// 创建单元格并设置单元格内容
		this.setDefaultXSSFRow(row, sheet);

		// ============================================================================
		// 开始写入上报小区数据
		// ============================================================================

		List<Map<String, Object>> communityPersions = dao.exportUsers();
		Map<String, Object> vo = null;
		String returnStatus = "";
		String tkStatus = "";
		for (int i = 0, len = communityPersions.size(); i < len; i++) {
			vo = communityPersions.get(i);
			row = sheet.createRow(i + 1);

			row.createCell(0).setCellValue(getMapValue(vo.get("id").toString()));
			row.createCell(1).setCellValue(getMapValue(vo.get("name").toString()));
			row.createCell(2).setCellValue(getMapValue(vo.get("mobilePhone")));
			row.createCell(3).setCellValue(getMapValue(vo.get("register_time")));
			row.createCell(4).setCellValue(getMapValue(vo.get("deposit")));
			row.createCell(5).setCellValue(getMapValue(vo.get("reductionDays")));
			row.createCell(6).setCellValue(getMapValue(vo.get("balance")));
			row.createCell(7).setCellValue(getMapValue(vo.get("machineNumber")));
			row.createCell(8).setCellValue(getMapValue(vo.get("phoneModel")));
			row.createCell(9).setCellValue(getMapValue(vo.get("last_login_time")));
			row.createCell(10).setCellValue(getMapValue(getStatus(vo.get("status").toString(), returnStatus)));
			row.createCell(11).setCellValue(getMapValue(getvipName(vo.get("userVip").toString(), returnStatus)));
			
		}

		return wkb;

	}
	
	private String getStatus(String status, String returnStatus) {
		switch (status) {
		case "0":
			returnStatus = "正常";
			break;
		case "1":
			returnStatus = "删除";
			break;
		}
		return returnStatus;
	}
	
	private String getvipName(String userVip, String returnStatus) {
		switch (userVip) {
		case "0":
			returnStatus = "非会员";
			break;
		case "2":
			returnStatus = "随心会员";
			break;
		}
		return returnStatus;
	}
	
	private String getMapValue(Object obj) {
		return (obj == null) ? Tools.EMPTY : obj.toString();
	}
	
	private void setDefaultXSSFRow(XSSFRow row, XSSFSheet sheet) {
		// 初始化第一行标题
		row.createCell(0).setCellValue("id");
		row.createCell(1).setCellValue("用户名");
		row.createCell(2).setCellValue("手机号码");
		row.createCell(3).setCellValue("创建时间");
		row.createCell(4).setCellValue("用户押金");
		row.createCell(5).setCellValue("免费期限");
		row.createCell(6).setCellValue("用户余额");
		row.createCell(7).setCellValue("用户手机设备号");
		row.createCell(8).setCellValue("用户机型");
		row.createCell(9).setCellValue("上次登陆时间");
		row.createCell(10).setCellValue("状态");
		row.createCell(11).setCellValue("会员等级");
	}

}
