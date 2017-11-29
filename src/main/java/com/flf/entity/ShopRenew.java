package com.flf.entity;

public class ShopRenew {

	private Integer id;
	
	private Integer userId;
	
	private Integer vipId;
	
	private String createTime;
	
	private String endTime;
	
	private Integer rechargeId;
	
	private Page page;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getVipId() {
		return vipId;
	}

	public void setVipId(Integer vipId) {
		this.vipId = vipId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public Integer getRechargeId() {
		return rechargeId;
	}

	public void setRechargeId(Integer rechargeId) {
		this.rechargeId = rechargeId;
	}

	public Page getPage() {
		return page;
	}

	public void setPage(Page page) {
		this.page = page;
	}
	
}
