package com.flf.service.impl;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.base.service.BaseServiceImpl;
import com.flf.entity.ShopCommodityBrand;
import com.flf.entity.ShopRenew;
import com.flf.mapper.ShopCommodityBrandMapper;
import com.flf.mapper.ShopRenewMapper;
import com.flf.service.ShopCommodityBrandService;
import com.flf.service.ShopRenewService;
import com.flf.util.Tools;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityBrandService<br>
 */
@Service("shopRenewService")
public class ShopRenewServiceImpl extends BaseServiceImpl implements ShopRenewService {
	private final static Logger log = Logger.getLogger(ShopRenewServiceImpl.class);

	@Autowired
	private ShopRenewMapper dao;

	@Override
	public ShopRenewMapper getDao() {
		return dao;
	}

	@Override
	public List<Map<String, Object>> listPage(ShopRenew vo) {
		return dao.listPage(vo);
	}

	
	
	@Override
	public int queryByUserId(Integer id) {
		
		return dao.queryByUserId(id);
	}

	@Override
	public XSSFWorkbook exportRecord() {

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

		List<Map<String, Object>> communityPersions = dao.exportRecord();
		Map<String, Object> vo = null;
		String returnStatus = "";
		for (int i = 0, len = communityPersions.size(); i < len; i++) {
			vo = communityPersions.get(i);
			row = sheet.createRow(i + 1);

			row.createCell(0).setCellValue(getMapValue(vo.get("id").toString()));
			row.createCell(1).setCellValue(getMapValue(vo.get("name").toString()));
			row.createCell(2).setCellValue(getMapValue(getVipname(vo.get("vip_id").toString(), returnStatus)));
			row.createCell(3).setCellValue(getMapValue(vo.get("recharge_id")));
			row.createCell(4).setCellValue(getMapValue(vo.get("create_time")));
			row.createCell(5).setCellValue(getMapValue(vo.get("end_time")));
		}

		return wkb;

	}
	
	private String getVipname(String vip, String returnStatus) {
		switch (vip) {
		case "2":
			returnStatus = "体验周卡";
			break;
		case "3":
			returnStatus = "月卡";
			break;
		case "4":
			returnStatus = "季卡";
			break;
		case "5":
			returnStatus = "年卡";
			break;
		}
		return returnStatus;
	}
	
	
	
	
	private String getMapValue(Object obj) {
		return (obj == null) ? Tools.EMPTY : obj.toString();
	}
	
	private void setDefaultXSSFRow(XSSFRow row, XSSFSheet sheet) {
		// 初始化第一行标题
		row.createCell(0).setCellValue("编号");
		row.createCell(1).setCellValue("用户名");
		row.createCell(2).setCellValue("月卡类型");
		row.createCell(3).setCellValue("消费编号");
		row.createCell(4).setCellValue("购买时间");
		row.createCell(5).setCellValue("结束时间");
	}
}
