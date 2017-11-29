package com.flf.entity;

import com.base.entity.BaseEntity;

/**
 * 
 * <br>
 * <b>功能：</b>ShopSpecialTopicEntity<br>
 */
public class ShopSpecialTopic extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private java.lang.Integer id;//
	private java.lang.String banner;// 横幅图片
	private java.lang.String method;// 内容图片
	private java.lang.Integer sort;// 排序
	private java.lang.Integer invalid;// 失效(0,否;1,是)
	private java.lang.String name;// 专题名
	private Integer type;// 专题分类(1新人必读2饰五六推荐品牌3其它）
	private Integer height;

	private String img1;
	private String img2;
	private String img3;
	private String img4;
	private String img5;
	private String img6;
	private String img7;

	private Page page;

	public java.lang.Integer getId() {
		return this.id;
	}

	public void setId(java.lang.Integer id) {
		this.id = id;
	}

	public java.lang.String getBanner() {
		return this.banner;
	}

	public void setBanner(java.lang.String banner) {
		this.banner = banner;
	}

	public java.lang.String getMethod() {
		return this.method;
	}

	public void setMethod(java.lang.String method) {
		this.method = method;
	}

	public java.lang.Integer getSort() {
		return this.sort;
	}

	public void setSort(java.lang.Integer sort) {
		this.sort = sort;
	}

	public java.lang.Integer getInvalid() {
		return this.invalid;
	}

	public void setInvalid(java.lang.Integer invalid) {
		this.invalid = invalid;
	}

	public java.lang.String getName() {
		return this.name;
	}

	public void setName(java.lang.String name) {
		this.name = name;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public String getImg1() {
		return img1;
	}

	public void setImg1(String img1) {
		this.img1 = img1;
	}

	public String getImg2() {
		return img2;
	}

	public void setImg2(String img2) {
		this.img2 = img2;
	}

	public String getImg3() {
		return img3;
	}

	public void setImg3(String img3) {
		this.img3 = img3;
	}

	public String getImg4() {
		return img4;
	}

	public void setImg4(String img4) {
		this.img4 = img4;
	}

	public String getImg5() {
		return img5;
	}

	public void setImg5(String img5) {
		this.img5 = img5;
	}

	public String getImg6() {
		return img6;
	}

	public void setImg6(String img6) {
		this.img6 = img6;
	}

	public String getImg7() {
		return img7;
	}

	public void setImg7(String img7) {
		this.img7 = img7;
	}

}
