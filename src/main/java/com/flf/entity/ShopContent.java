package com.flf.entity;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>ShopContentEntity<br>
 */
public class ShopContent extends BaseEntity {
	
		private java.lang.Integer contentId;//   	private java.lang.String content;//   商品评价	private java.lang.Integer userId;//   发表评价的用户ID	private java.lang.Integer commodityId;//   商品ID	private java.lang.Integer createTime;//   	private java.lang.String contentImg;//   商品图片	public java.lang.Integer getContentId() {	    return this.contentId;	}	public void setContentId(java.lang.Integer contentId) {	    this.contentId=contentId;	}	public java.lang.String getContent() {	    return this.content;	}	public void setContent(java.lang.String content) {	    this.content=content;	}	public java.lang.Integer getUserId() {	    return this.userId;	}	public void setUserId(java.lang.Integer userId) {	    this.userId=userId;	}	public java.lang.Integer getCommodityId() {	    return this.commodityId;	}	public void setCommodityId(java.lang.Integer commodityId) {	    this.commodityId=commodityId;	}	public java.lang.Integer getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Integer createTime) {	    this.createTime=createTime;	}	public java.lang.String getContentImg() {	    return this.contentImg;	}	public void setContentImg(java.lang.String contentImg) {	    this.contentImg=contentImg;	}
}

