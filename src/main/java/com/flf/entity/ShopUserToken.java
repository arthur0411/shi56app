package com.flf.entity;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>ShopUserTokenEntity<br>
 */
public class ShopUserToken extends BaseEntity {
	
		private java.lang.Integer sessionId;//   	private java.lang.Integer userId;//   	private java.lang.String userToken;//   用户登录token	private java.lang.Integer createTime;//   登录时间	public java.lang.Integer getSessionId() {	    return this.sessionId;	}	public void setSessionId(java.lang.Integer sessionId) {	    this.sessionId=sessionId;	}	public java.lang.Integer getUserId() {	    return this.userId;	}	public void setUserId(java.lang.Integer userId) {	    this.userId=userId;	}	public java.lang.String getUserToken() {	    return this.userToken;	}	public void setUserToken(java.lang.String userToken) {	    this.userToken=userToken;	}	public java.lang.Integer getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Integer createTime) {	    this.createTime=createTime;	}
}

