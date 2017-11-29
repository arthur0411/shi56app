package com.flf.controller.app;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.base.criteria.Criteria;
import com.flf.entity.ShopUser;
import com.flf.service.RedisSpringProxy;
import com.flf.service.ShopUserService;
import com.flf.util.DateUtil;
import com.flf.util.FileUpload;
import com.flf.util.HttpClientUtil;
import com.flf.util.JSONUtils;
import com.flf.util.JuheResult;
import com.flf.util.JuheResultBean;
import com.flf.util.Tools;

/**
 * 
 * <br>
 * <b>功能：</b>ShopUserAppController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/shopUser")
public class ShopUserAppController {

	private final static Logger log = Logger.getLogger(ShopUserAppController.class);
	@Autowired(required = false)
	private ShopUserService shopUserService;
	@Autowired
	private RedisSpringProxy redisSpringProxy;

	/**
	 * 获取用户个人信息
	 */
	@RequestMapping(value = "/getHajFrontUser")
	public void getHajFrontUserApp(@RequestParam Integer userId, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			log.info("获取用户Id:" + userId + "个人信息");
			ShopUser user = shopUserService.queryById(userId);

			jsonMap.put("error", "0");
			jsonMap.put("msg", (user != null) ? "成功" : "为空");
			jsonMap.put("user", user);

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 用户登录/注册
	 *
	 * @param mobilePhone
	 * @param machineNumber
	 * @param code
	 *            验证码
	 * @param response
	 */
	@RequestMapping(value = "/shopUserlogin")
	public void shopUserlogin(@RequestParam String mobilePhone, String machineNumber, @RequestParam String code,
			String phoneModel, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		Map<String, Object> condition = new HashMap<String, Object>();
		condition.put("mobilePhone", mobilePhone);
		Criteria criteria = new Criteria();
		criteria.setCondition(condition);
		try {
			if (Tools.notEmpty(mobilePhone) && isMobileNO(mobilePhone)) {
				String login_verification_code = (String) redisSpringProxy.read("login_code_" + mobilePhone);

				// 判断是否禁用登录验证码，后台配置
				String disable_vcode = (String) redisSpringProxy.read("SystemConfiguration_disable_vcode");
				String appstore_checking = (String) redisSpringProxy.read("SystemConfiguration_appstore_checking");
				String appstore_test_account = (String) redisSpringProxy
						.read("SystemConfiguration_appstore_test_account");

				boolean disableVcode = Boolean.parseBoolean(disable_vcode);

				// appstore审核过程中，仅允许 appstore_test_account 可以绕过验证码进行登录
				if (Boolean.parseBoolean(appstore_checking) && appstore_test_account.equals(mobilePhone)) {
					disableVcode = true;
				}

				log.info(mobilePhone + "-->在redis中的验证码:" + login_verification_code);
				if (code.equals(login_verification_code) || disableVcode) {
					ShopUser user = (ShopUser) redisSpringProxy.read("shopfrontUser_" + mobilePhone);
					// 缓存中已找到该用户说明用户已登录过，无需重复登录
					if (null == user) {
						List<ShopUser> list = shopUserService.queryByList(criteria);
						if (list != null && list.size() > 0) {
							user = list.get(0);
							user.setMachineNumber(machineNumber);
							redisSpringProxy.save("shopfrontUser_" + mobilePhone, user);
						} else {
							user = shopUserService.save(mobilePhone, machineNumber, phoneModel);
							redisSpringProxy.save("shopfrontUser_" + mobilePhone, user);
						}
					} else {
						// 做登录剔除
						redisSpringProxy.delete("shopfrontUser_" + mobilePhone);
						// 新用户做剔除，删除前面的machineNumber唯一编号
						user.setMachineNumber(machineNumber);
						redisSpringProxy.save("shopfrontUser_" + mobilePhone, user);
					}

					jsonMap.put("error", "0");
					jsonMap.put("msg", "成功");
					jsonMap.put("result", user);

					// 登录成功后清除验证码
					redisSpringProxy.delete("login_code_" + mobilePhone);

					// 更新用户最后登录记录
					shopUserService.updateLoginInfo(user.getId(), machineNumber, phoneModel);
				} else {
					jsonMap.put("error", "1");
					jsonMap.put("msg", "验证码输入有误");
				}
			} else {
				jsonMap.put("error", "2");
				jsonMap.put("msg", "手机号码为空或者格式有误");
				jsonMap.put("result", "0");
				jsonMap.put("mobilePhone", mobilePhone);
			}

		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			jsonMap.put("result", "0");
			log.error(jsonMap.get("msg"), e);
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private static boolean isMobileNO(String mobiles) {
		Pattern p = Pattern.compile("^((13[0-9])|(15[^4])|(18[0-9])|(17[0-8])|(14[5,7,9]))\\d{8}$");
		Matcher m = p.matcher(mobiles);
		return m.matches();
	}

	/**
	 * APP上传头像接口返回头像在服务器上的http地址
	 *
	 * @param file
	 * @param sign
	 * @param request
	 * @param response
	 * @throws Exception
	 * @Author SevenWong
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("file") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		Map<String, Object> jsonMap = new HashMap<String, Object>();

		String subPath = "images" + File.separator + "userHead" + File.separator + DateUtil.date2Str(new Date(), false)
				+ File.separator;
		String filePath = FileUpload.uploadFile(file, subPath, request);

		if (!"".equals(filePath)) {
			jsonMap.put("error", "0");
			jsonMap.put("msg", "成功");
			jsonMap.put("result", filePath);
		} else {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			jsonMap.put("result", "0");
		}
		JSONUtils.printObject(jsonMap, response);
	}

	/**
	 * 发送登录短信验证码
	 * 
	 * @author SevenWong<br>
	 * @date 2016年6月16日上午10:38:49
	 * @param sign
	 * @param mobile
	 * @param response
	 */
	@RequestMapping(value = "/getLoginVerificationCode")
	public void getLoginVerificationCode(@RequestParam String mobile, HttpServletResponse response) {
		Map<String, String> jsonMap = new HashMap<String, String>();

		// 判断是否禁用登录验证码，后台配置
		String disable_vcode = (String) redisSpringProxy.read("SystemConfiguration_disable_vcode");
		boolean disableVcode = (Boolean.parseBoolean(disable_vcode));

		// 为true时，不发送短信，直接返回false
		String status = disableVcode ? "false" : shopUserService.getLoginVerificationCode(mobile);

		// status有4种状态 error: 未知异常; true: 发送成功; false: 发送失败; 其他: 发送异常描述
		jsonMap.put("status", status);
		try {
			JSONUtils.printObject(jsonMap, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 聚合认证
	 * 
	 * @param user_bname
	 *            李焱斌
	 * @param idcard
	 *            43022519890323601X
	 * @param bankcard
	 *            6214837800568242
	 * @param mobile
	 *            18576469329
	 */
	@RequestMapping(value = "/getHajFrontUserJuhe")
	public void getHajFrontUserJuhe(int userId, String user_bname, String idcard, String bankcard, String mobile,
			HttpServletResponse response) {
		String msg = "";
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		try {
			ShopUser user = shopUserService.queryById(userId);

			// 更新用户信息
			user.setUser_bname(user_bname);
			user.setMobile(mobile);
			user.setUser_creditnumber(bankcard);
			user.setUser_idcard(idcard);
			String disable_juhe = (String) redisSpringProxy.read("SystemConfiguration_disable_juhe");

			boolean disableJuhe = Boolean.parseBoolean(disable_juhe);

			if (disableJuhe) {
				if (user.getRenzhen_status() == 0) {
					log.info("用户：" + userId + ",聚合认证姓名:" + user_bname + "，身份证：" + idcard + ",银行卡号" + bankcard
							+ ",银行预留手机:" + mobile);
					String name = "";
					try {
						name = URLEncoder.encode(user_bname, "utf-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					}
					String params = "key=d794afab7b78c48ee705033fe4f2c7db&bankcard=" + bankcard + "&realname=" + name
							+ "&idcard=" + idcard + "&mobile=" + mobile;
					/*
					 * String returnStr = HttpClientUtil.sendPost(
					 * "http://v.juhe.cn/verifybankcard4/query.php?realname=" +
					 * user_bname + "&idcard=" + idcard + "&bankcard=" +
					 * bankcard + "&mobile=" + mobile +
					 * "&key=d794afab7b78c48ee705033fe4f2c7db", "",
					 * HttpClientUtil.CONTENT_TYPE_JSON, HttpClientUtil.UTF_8);
					 */

					String returnStr = HttpClientUtil.sendPost("http://v.juhe.cn/verifybankcard4/query.php?" + params,
							"", HttpClientUtil.CONTENT_TYPE_XML, HttpClientUtil.UTF_8);

					log.info("聚合认证返回字符串returnStr：" + returnStr);
					if (Tools.isNotEmpty(returnStr)) {
						JSONObject jsonObj = JSONObject.fromObject(returnStr);
						Map<String, Class> classMap = new HashMap<>();
						classMap.put("result", JuheResult.class);
						// 将JSON转换成Order
						JuheResultBean jhbean = (JuheResultBean) JSONObject.toBean(jsonObj, JuheResultBean.class,
								classMap);

						if (null != jhbean.getResult()) {
							JuheResult jhresult = jhbean.getResult();
							if (jhresult.getRes().equals("1")) {
								jsonMap.put("error", "0");
								msg = "认证成功";
								user.setRenzhen_status(1);
								user.setStatus(1);
								shopUserService.updateBySelective(user);
							} else {
								jsonMap.put("error", "3");
								msg = "认证失败！原因：" + jhresult.getMessage();
								user.setRenzhen_status(0);
								user.setStatus(2);
								shopUserService.updateBySelective(user);
							}
						} else {
							jsonMap.put("error", "2");
							msg = "认证失败！原因：" + jhbean.getReason();
						}
					} else {
						jsonMap.put("error", "1");
						msg = "认证失败！";
					}
				} else {
					jsonMap.put("error", "0");
					msg = "认证成功";
				}
			} else {
				jsonMap.put("error", "0");
				msg = "认证成功";
				user.setRenzhen_status(1);
				user.setStatus(1);
				shopUserService.updateBySelective(user);
			}
			jsonMap.put("msg", msg);
			jsonMap.put("user", shopUserService.queryById(userId));
		} catch (Exception e) {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				JSONUtils.printObject(jsonMap, response);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}

	/**
	 * 退出登录
	 */
	@RequestMapping(value = "/hajUserLogout")
	public void hajUserLogout(@RequestParam String mobilePhone, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (null != mobilePhone && !"".equals(mobilePhone)) {
			ShopUser user = (ShopUser) redisSpringProxy.read("shopfrontUser_" + mobilePhone);
			if (null != user) {
				// 退出登录 清空缓存
				jsonMap.put("error", "0");
				jsonMap.put("msg", "成功");
			} else {
				jsonMap.put("error", "1");
				jsonMap.put("msg", "失败");
			}
		} else {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "手机号码异常");
		}

		try {
			JSONUtils.printObject(jsonMap, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	/**
	 * 更新用户信息
	 */
	@RequestMapping(value = "/updateUserInfo")
	public void updateUserInfo(@ModelAttribute ShopUser user, HttpServletResponse response) {
		Map<String, Object> jsonMap = new HashMap<String, Object>();
		if (null != user && !"".equals(user)) {
			shopUserService.updateBySelective(user);

			ShopUser shopUser;
			try {
				shopUser = shopUserService.queryById(user.getId());
				jsonMap.put("user", shopUser);
			} catch (Exception e) {
				e.printStackTrace();
			}
			jsonMap.put("error", "0");
			jsonMap.put("msg", "成功");

		} else {
			jsonMap.put("error", "1");
			jsonMap.put("msg", "用户异常");
		}

		try {
			JSONUtils.printObject(jsonMap, response);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	public static void main(String[] args) {
		String bankcard = "6214837800568242";
		String user_bname = "李焱斌";
		String idcard = "43022519890323601X";
		String mobile = "18576469329";
		/*
		 * String params = "key=d794afab7b78c48ee705033fe4f2c7db&bankcard=" +
		 * bankcard + "&realname=" + user_bname + "&idcard=" + idcard +
		 * "&mobile=" + mobile;
		 */
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("key", "d794afab7b78c48ee705033fe4f2c7db");
		map.put("bankcard", bankcard);
		map.put("realname", user_bname);
		map.put("idcard", idcard);
		map.put("mobile", mobile);

		// http://v.juhe.cn/verifybankcard4/query.php?realname=%E6%9D%8E%E7%84%B1%E6%96%8C&idcard=43022519890323601X&bankcard=6214837800568242&mobile=18576469323&key=d794afab7b78c48ee705033fe4f2c7db

		String params = JSONUtils.toJSONString(map);
		// http://v.juhe.cn/verifybankcard4/query.php?realname=李焱斌&idcard=43022519890323601X&bankcard=6214837800568242&mobile=18576469329&key=d794afab7b78c48ee705033fe4f2c7db
		// http://v.juhe.cn/verifybankcard4/query.php?realname=李焱斌&idcard=43022519890323601X&bankcard=6214837800568242&mobile=18576469329&key=d794afab7b78c48ee705033fe4f2c7db
		String name = "";
		try {
			name = URLEncoder.encode("李焱斌", "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String returnStr = HttpClientUtil
				.sendPost(
						"http://v.juhe.cn/verifybankcard4/query.php?realname="
								+ name
								+ "&idcard=43022519890323601X&bankcard=6214837800568242&mobile=18576469329&key=d794afab7b78c48ee705033fe4f2c7db",
						"", HttpClientUtil.CONTENT_TYPE_JSON, HttpClientUtil.UTF_8);
		System.out.println(returnStr);
		if (Tools.isNotEmpty(returnStr)) {
			JSONObject jsonObj = JSONObject.fromObject(returnStr);
			Map<String, Class> classMap = new HashMap<>();
			classMap.put("result", JuheResult.class);
			// 将JSON转换成Order
			JuheResultBean jhbean = (JuheResultBean) JSONObject.toBean(jsonObj, JuheResultBean.class, classMap);

			if (null != jhbean.getResult()) {

			}
		}

	}
}
