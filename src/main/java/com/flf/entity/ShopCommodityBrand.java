package com.flf.entity;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopCommodityTagEntity<br>
 */
public class ShopCommodityBrand extends BaseEntity {

	private java.lang.Integer brandId;// 分类ID
	private java.lang.String brandName;// 分类名称
	private java.lang.String brandImg;// 分类图片
	private java.lang.String createTime;// 创建时间
	private java.lang.String updateTime;// 修改时间
	private java.lang.String brandInfo;// 分类英文名

	private Page page;

	public java.lang.Integer getBrandId() {
		return brandId;
	}

	public void setBrandId(java.lang.Integer brandId) {
		this.brandId = brandId;
	}

	public java.lang.String getBrandName() {
		return brandName;
	}

	public void setBrandName(java.lang.String brandName) {
		this.brandName = brandName;
	}

	public java.lang.String getBrandImg() {
		return brandImg;
	}

	public void setBrandImg(java.lang.String brandImg) {
		this.brandImg = brandImg;
	}

	public java.lang.String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(java.lang.String createTime) {
		this.createTime = createTime;
	}

	public java.lang.String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(java.lang.String updateTime) {
		this.updateTime = updateTime;
	}

	public java.lang.String getBrandInfo() {
		return brandInfo;
	}

	public void setBrandInfo(java.lang.String brandInfo) {
		this.brandInfo = brandInfo;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	} // 分页

}