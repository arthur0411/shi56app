package com.flf.controller;

import java.io.File;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.ShopCommodityTag;
import com.flf.service.ShopCommodityTagService;
import com.flf.util.DateUtil;
import com.flf.util.FileUpload;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/commodityTag")
public class ShopCommodityTagController {

	private final static Logger log = Logger.getLogger(ShopCommodityTagController.class);
	@Autowired(required = false)
	private ShopCommodityTagService shopCommodityTagService;

	@RequestMapping(value = "/list")
	public ModelAndView list(ShopCommodityTag vo, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<Map<String, Object>> commodityTagList = shopCommodityTagService.listPage(vo);

		mv.addObject("vo", vo);
		mv.addObject("commodityTagList", commodityTagList);
		mv.setViewName("commodityTagList");

		return mv;
	}

	@RequestMapping(value = "/add")
	public String toAdd(Model model) {
		return "commodityTag_add";
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam Integer id) throws Exception {
		ModelAndView mv = new ModelAndView();
		ShopCommodityTag vo = shopCommodityTagService.queryById(id);
		mv.addObject("commodityTag", vo);
		mv.setViewName("commodityTag_add");
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(ShopCommodityTag vo, PrintWriter out) throws Exception {
		String result = "success";
		if (vo != null) {
			if (vo.getTagId() == null) {
				boolean istrue = shopCommodityTagService.getShopCommodityTagByName(vo.getTagName());
				if (istrue) {
					result = "error";
				} else {
					vo.setCreateTime(DateUtil.datetime2Str(new Date()));
					shopCommodityTagService.add(vo);
				}
			} else {
				shopCommodityTagService.updateBySelective(vo);
			}
		} else {
			result = "error";
		}

		out.print(result);
		out.close();
	}

	@RequestMapping(value = "/delete")
	public void delete(Integer[] id, PrintWriter out) throws Exception {
		shopCommodityTagService.delete((Object[]) id);
		out.print("success");
		out.close();
	}

	/**
	 * 文件上传（上传商品图片）
	 *
	 * @param file
	 * @param request
	 * @throws Exception
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	public void uploadFile(@RequestParam("fileName") MultipartFile file, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		response.setContentType("application/json;charset=UTF-8");

		// 手动上传图片默认保存在同一目录下，然后更新文件后缀版本号
		String subPath = "commodityTag_images" + File.separator;
		String filePath = FileUpload.uploadFile(file, subPath, request);
		Map<String, Object> result = new HashMap<>();
		if (!"".equals(filePath)) {
			filePath = filePath + "?v=" + new Random().nextInt(1000);
			result.put("filePath", filePath);
			result.put("result", "success");
		} else {
			result.put("result", "error");
		}
		JSONUtils.printObject(result, response);
	}

}
