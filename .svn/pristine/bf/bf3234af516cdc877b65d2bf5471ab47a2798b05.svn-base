package com.flf.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.flf.entity.Menu;
import com.flf.entity.Role;
import com.flf.entity.User;
import com.flf.service.MenuService;
import com.flf.service.UserService;
import com.flf.util.Const;
import com.flf.util.JSONUtils;
import com.flf.util.MD5;
import com.flf.util.RightsHelper;
import com.flf.util.Tools;

@Controller
public class LoginController {

	private static final Logger log = Logger.getLogger(LoginController.class);
	@Autowired
	private UserService userService;
	@Autowired
	private MenuService menuService;

	/*
	 * @Autowired private TestFreemarkerService testFreemarkerService;
	 */

	/**
	 * 访问登录页
	 * 
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginGet() {
		return "login";
	}

	/**
	 * 请求登录，验证用户
	 * 
	 * @param session
	 * @param loginname
	 * @param password
	 * @param code
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public void loginPost(HttpSession session, @RequestParam String loginname, @RequestParam String password,
			HttpServletResponse response) {
		Map<String, String> jsonMap = new HashMap<String, String>();
		log.info("后台登陆进行中");
		String errInfo = "success";
		password = MD5.MD5Encode(password);
		User user = userService.getUserByNameAndPwd(loginname, password);
		if (user != null) {
			user.setLastLogin(new Date());
			userService.updateLastLogin(user);
			session.setAttribute(Const.SESSION_USER, user);
		} else {
			errInfo = "用户名或密码有误！";
		}
		jsonMap.put("msg", errInfo);

		try {
			JSONUtils.printObject(jsonMap, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 访问系统首页
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public String index(HttpSession session, Model model) {
		User user = (User) session.getAttribute(Const.SESSION_USER);
		if (null != user) {
			user = userService.getUserAndRoleById(user.getUserId());
			Role role = user.getRole();
			String roleRights = role != null ? role.getRights() : "";
			String userRights = user.getRights();
			// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
			session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session
			session.setAttribute(Const.SESSION_USER_RIGHTS, userRights); // 将用户权限存入session

			List<Menu> menuList = menuService.listAllMenu();
			if (Tools.notEmpty(userRights) || Tools.notEmpty(roleRights)) {
				for (Menu menu : menuList) {
					menu.setHasMenu(RightsHelper.testRights(userRights, menu.getMenuId())
							|| RightsHelper.testRights(roleRights, menu.getMenuId()));
					if (menu.isHasMenu()) {
						List<Menu> subMenuList = menu.getSubMenu();
						for (Menu sub : subMenuList) {
							sub.setHasMenu(RightsHelper.testRights(userRights, sub.getMenuId())
									|| RightsHelper.testRights(roleRights, sub.getMenuId()));
						}
					}
				}
			}
			model.addAttribute("user", user);
			model.addAttribute("menuList", menuList);
			return "index";
		} else {
			return "login";
		}
	}

	/**
	 * debug时使用此方法访问后台首页，重启tomcat无需重复登录，<br>
	 * 如需更换身份，则改变userService.getUserAndRoleById(1)的用户Id
	 * 
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index_debug")
	public String index_debug(HttpSession session, Model model) {
		User user = userService.getUserAndRoleById(1);
		Role role = user.getRole();
		String roleRights = role != null ? role.getRights() : "";
		String userRights = user.getRights();
		// 避免每次拦截用户操作时查询数据库，以下将用户所属角色权限、用户权限限都存入session
		session.setAttribute(Const.SESSION_ROLE_RIGHTS, roleRights); // 将角色权限存入session
		session.setAttribute(Const.SESSION_USER_RIGHTS, userRights); // 将用户权限存入session

		List<Menu> menuList = menuService.listAllMenu();
		if (Tools.notEmpty(userRights) || Tools.notEmpty(roleRights)) {
			for (Menu menu : menuList) {
				menu.setHasMenu(RightsHelper.testRights(userRights, menu.getMenuId())
						|| RightsHelper.testRights(roleRights, menu.getMenuId()));
				if (menu.isHasMenu()) {
					List<Menu> subMenuList = menu.getSubMenu();
					for (Menu sub : subMenuList) {
						sub.setHasMenu(RightsHelper.testRights(userRights, sub.getMenuId())
								|| RightsHelper.testRights(roleRights, sub.getMenuId()));
					}
				}
			}
		}
		model.addAttribute("user", user);
		model.addAttribute("menuList", menuList);
		return "index";
	}

	/**
	 * 进入首页后的默认页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/default")
	public String defaultPage() {
		return "default";
	}

	/**
	 * 用户注销
	 * 
	 * @param session
	 * @return
	 */
	@RequestMapping(value = "/logout")
	public String logout(HttpSession session) {
		session.removeAttribute(Const.SESSION_USER);
		session.removeAttribute(Const.SESSION_ROLE_RIGHTS);
		session.removeAttribute(Const.SESSION_USER_RIGHTS);
		return "login";
	}
}
