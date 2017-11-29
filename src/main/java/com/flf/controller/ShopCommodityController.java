package com.flf.controller;

import java.io.File;
import java.io.IOException;
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
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.flf.entity.Page;
import com.flf.entity.ShopCommodity;
import com.flf.entity.ShopCommodityBrand;
import com.flf.entity.ShopCommodityTag;
import com.flf.service.ShopCommodityBrandService;
import com.flf.service.ShopCommodityPicService;
import com.flf.service.ShopCommodityService;
import com.flf.service.ShopCommodityTagService;
import com.flf.service.ShopVipService;
import com.flf.util.DateUtil;
import com.flf.util.FileUpload;
import com.flf.util.JSONUtils;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityController<br>
 * <br>
 */
@Controller
@RequestMapping(value = "/commodity")
public class ShopCommodityController {

	private final static Logger log = Logger.getLogger(ShopCommodityController.class);
	@Autowired(required = false)
	private ShopCommodityService shopCommodityService;
	@Autowired(required = false)
	private ShopCommodityTagService shopCommodityTagService;
	@Autowired(required = false)
	private ShopCommodityPicService shopCommodityPicService;
	@Autowired(required = false)
	private ShopVipService shopVipService;

	@Autowired(required = false)
	private ShopCommodityBrandService shopCommodityBrandService;

	@RequestMapping(value = "/listBrand")
	public void listBrand(ShopCommodityBrand vo, HttpServletResponse httpServletResponse) {
		
		StringBuffer result = new StringBuffer("[");
	
		List<Map<String, Object>> commodityBrandList = shopCommodityBrandService.listAll();
		
		for(Map map : commodityBrandList) {
			result.append("{\"id\":"+map.get("brand_id")+",\"name\":\""+map.get("brand_name")+"\"},");
		}
		
		String rs = result.substring(0, result.length()-1)+"]";
		
		try {
			JSONUtils.printStr(rs, httpServletResponse);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "/addBrand")
	public String toAddBrand(Model model) {
		return "commodityBrand_add";
	}
	
	/**
	 * 查询所有商品列表
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/list")
	public ModelAndView list(ShopCommodity vo, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();

		// 查找商品分类
		ShopCommodityTag commodityTag = new ShopCommodityTag();
		List<Map<String, Object>> commodityTagList = shopCommodityTagService.listPage(commodityTag);
		mv.addObject("commodityTagList", commodityTagList);

		List<Map<String, Object>> commodityList = shopCommodityService.listPage(vo);
		mv.addObject("vo", vo);
		mv.addObject("commodityList", commodityList);
		mv.setViewName("commodityList");
		return mv;
	}
	
	
	@RequestMapping(value = "/delete")
	public void delete(@RequestParam Integer id, HttpServletResponse httpServletResponse) throws Exception {
		shopCommodityService.delete(id);
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	@RequestMapping(value = "/deleteMany")
	public void deleteMany(@RequestParam Integer[] arr1, HttpServletResponse httpServletResponse) throws Exception {
		for(int i=0;i<arr1.length;i++) {
			shopCommodityService.delete(arr1[i]);
		}
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	@RequestMapping(value = "/upJia")
	public void upJia(@RequestParam Integer[] arr1, HttpServletResponse httpServletResponse) throws Exception {
		ShopCommodity sc =new ShopCommodity();
		sc.setIsUpjia("yes");
		for(int i=0;i<arr1.length;i++) {
			sc.setCommodityId(arr1[i]);
			shopCommodityService.updateBySelective(sc);
		}
		JSONUtils.printStr("1", httpServletResponse);
	}

	@RequestMapping(value = "/downJia")
	public void downJia(@RequestParam Integer[] arr1, HttpServletResponse httpServletResponse) throws Exception {
		ShopCommodity sc =new ShopCommodity();
		sc.setIsUpjia("no");
		for(int i=0;i<arr1.length;i++) {
			sc.setCommodityId(arr1[i]);
			shopCommodityService.updateBySelective(sc);
		}
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	
	@RequestMapping(value = "/guahui")
	public void guaHui(@RequestParam Integer price, HttpServletResponse httpServletResponse) throws Exception {
		shopCommodityService.guaHui(price);
		JSONUtils.printStr("1", httpServletResponse);
	}
	
	/**
	 * 根据商品id查询对应的商品，返回给前端修改页面填充数据
	 *
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/edit")
	public ModelAndView edit(@RequestParam Integer id, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView(); // vipList
		List<Map<String, Object>> vipList = shopVipService.getShopVipList();
		mv.addObject("vipList", vipList);
		List<Map<String, Object>> commodityTagList = shopCommodityTagService.getCommodityTagList();
		mv.addObject("commodityTagList", commodityTagList);
		ShopCommodity vo = shopCommodityService.getCommodityById(id);
		mv.addObject("vo", vo);
		mv.setViewName("commodityDetail");
		return mv;
	}
	
	@RequestMapping(value = "/editPic")
	public ModelAndView editPic(@RequestParam Integer id, HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView(); // vipList
		List<Map<String, Object>> vipList = shopVipService.getShopVipList();
		mv.addObject("vipList", vipList);
		List<Map<String, Object>> commodityTagList = shopCommodityTagService.getCommodityTagList();
		mv.addObject("commodityTagList", commodityTagList);
		ShopCommodity vo = shopCommodityService.getCommodityById(id);
		mv.addObject("vo", vo);
		mv.setViewName("commodityPic");
		return mv;
	}

	/**
	 * 跳转到商品添加页面
	 *
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/add")
	public ModelAndView add(HttpSession session) throws Exception {
		ModelAndView mv = new ModelAndView();
		List<Map<String, Object>> vipList = shopVipService.getShopVipList();
		mv.addObject("vipList", vipList);
		List<Map<String, Object>> commodityTagList = shopCommodityTagService.getCommodityTagList();
		mv.addObject("commodityTagList", commodityTagList);
		ShopCommodity vo = new ShopCommodity();
		mv.addObject("vo", vo);
		mv.setViewName("commodityEdit");
		return mv;
	}

	/**
	 * 保存添加或修改的商品<br>
	 * 前台ajax 调用
	 *
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public void save(ShopCommodity commodity, HttpSession session, HttpServletResponse response) {
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("error", "0");
		returnMap.put("msg", "success");
		try {
			if (commodity != null) {
				ShopCommodityBrand brand = shopCommodityBrandService.queryById(commodity.getBrandId());
				commodity.setBrand(brand.getBrandName());
				if (commodity.getCommodityId() != null && commodity.getCommodityId() > 0) {
					commodity.setCommodityName(commodity.getCommodityName().trim());
					if(commodity.getCommodityPrice() != null && commodity.getCommodityPrice().indexOf("￥") == -1) {
						commodity.setCommodityPrice("￥" +  commodity.getCommodityPrice());
					}
					if(commodity.getActualPrice() != null && commodity.getActualPrice().indexOf("￥") == -1) {
						commodity.setActualPrice("￥" +  commodity.getActualPrice());
					}
					commodity.setDownTime(DateUtil.datetime2Str(new Date()));
					shopCommodityService.updateBySelective(commodity);
				} else {
					// 商品上下架在商品供应管理中统一管理，添加时默认下架。修改商品不做处理
					//commodity.setIsUpjia("no");
					//commodity.setIsRecommend("no");
					commodity.setCommodityName(commodity.getCommodityName().trim());
					if(commodity.getCommodityPrice() != null && commodity.getCommodityPrice().indexOf("￥") == -1) {
						commodity.setCommodityPrice("￥" +  commodity.getCommodityPrice());
					}
					if(commodity.getActualPrice() != null && commodity.getActualPrice().indexOf("￥") == -1) {
						commodity.setActualPrice("￥" +  commodity.getActualPrice());
					}
					commodity.setBrandTopic(0);
					commodity.setCreateTime(DateUtil.datetime2Str(new Date()));
					commodity.setSortId(DateUtil.getInt());
					shopCommodityService.add(commodity);
				}

			} else {
				returnMap.put("error", "3");
				returnMap.put("msg", "数据为空");
			}
		} catch (DuplicateKeyException e) {
			returnMap.put("error", "4");
			returnMap.put("msg", "此商品已存在");
			log.error(e.getCause());
		} catch (Exception e) {
			returnMap.put("error", "1");
			returnMap.put("msg", "未知异常");
			e.printStackTrace();
		} finally {
			try {
				JSONUtils.printObject(returnMap, response);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	
	@RequestMapping(value = "/reload")
	public void reload(@RequestParam Integer id,  HttpServletResponse httpServletResponse) {
		try {
			ShopCommodity commodity = shopCommodityService.queryById(id);
			commodity.setSortId(DateUtil.getInt());
			shopCommodityService.updateBySelective(commodity);
			JSONUtils.printStr("0", httpServletResponse);
		} catch (Exception e) {
			try {
				JSONUtils.printStr("1", httpServletResponse);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
	
	@RequestMapping(value = "/reloadAll")
	public void reloadAll(HttpServletResponse httpServletResponse) {
		try {
			List<ShopCommodity> list = shopCommodityService.listcart();
			int newSort = DateUtil.getInt();
			for(ShopCommodity sc : list) {
				if(sc.getSaleStock().equals("0")) {
					sc.setSortId(newSort - new Random().nextInt(10000) -10000);
				}else {
					sc.setSortId(newSort - new Random().nextInt(10000));
				}
				shopCommodityService.updateBySelective(sc);
			}
			JSONUtils.printStr("0", httpServletResponse);
		} catch (Exception e) {
			try {
				JSONUtils.printStr("1", httpServletResponse);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
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
		String subPath = "commodity_images" + File.separator;
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
