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

import com.flf.entity.ShopCommodityBrand;
import com.flf.entity.ShopCommoditySku;
import com.flf.service.ShopCommodityBrandService;
import com.flf.service.ShopCommoditySkuService;
import com.flf.util.DateUtil;
import com.flf.util.FileUpload;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityBrandController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/commoditySku")
public class ShopCommoditySkuController {

	private final static Logger log = Logger.getLogger(ShopCommoditySkuController.class);
	
	@Autowired(required = false)
	private ShopCommodityBrandService shopCommodityBrandService;
	
	@Autowired(required = false)
	private ShopCommoditySkuService shopCommoditySkuService;

	@RequestMapping(value = "/list")
	public ModelAndView list(Integer id, HttpSession session) {
		ModelAndView mv = new ModelAndView();

		List<ShopCommoditySku> list = shopCommoditySkuService.list(id);

		mv.addObject("commodityId", id);
		mv.addObject("commoditySkuList", list);
		mv.setViewName("commoditySkuList");

		return mv;
	}

	@RequestMapping(value = "/add")
	public ModelAndView toAdd(Integer commodityId,HttpSession session) {
		ModelAndView mv = new ModelAndView();
		
		mv.addObject("commodityId", commodityId);
		mv.setViewName("commoditySku_add");

		return mv;
	}

	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam Integer id,Integer commodityId) throws Exception {
		ModelAndView mv = new ModelAndView();
		ShopCommoditySku vo = shopCommoditySkuService.queryById(id);
		
		mv.addObject("commodityId", commodityId);
		mv.addObject("commoditySku", vo);
		mv.setViewName("commoditySku_add");
		return mv;
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(ShopCommoditySku vo, PrintWriter out) throws Exception {
		String result = "success";
		if (vo != null) {
			if (vo.getSkuId() == null) {
				shopCommoditySkuService.add(vo);
			} else {
				shopCommoditySkuService.updateBySelective(vo);
			}
		} else {
			result = "error";
		}

		out.print(result);
		out.close();
	}

	@RequestMapping(value = "/delete")
	public void delete(Integer[] id, PrintWriter out) throws Exception {
		shopCommoditySkuService.delete((Object[]) id);
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
		String subPath = "commoditySku_images" + File.separator;
		String filePath = FileUpload.uploadFile(file, subPath, request);
		Map<String, Object> result = new HashMap<>();
		if (!"".equals(filePath)) {
			filePath = filePath + "?v=" + new Random().nextInt(10000);
			result.put("filePath", filePath);
			result.put("result", "success");
		} else {
			result.put("result", "error");
		}
		JSONUtils.printObject(result, response);
	}

}
