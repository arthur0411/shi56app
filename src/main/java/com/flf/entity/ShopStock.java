package com.flf.entity;

import com.base.entity.BaseEntity;
/**
 * 
 * <br>
 * <b>功能：</b>ShopStockEntity<br>
 */
public class ShopStock extends BaseEntity {
	
		private java.lang.Integer stockId;//   	private java.lang.String stockName;//   库存名称	private java.lang.String stockNum;//   库存数量	private java.lang.String stockInventory;//   库存单位	private java.lang.Integer stockEarly;//   库存报警额度	private java.lang.Integer createTime;//   添加时间	private java.lang.Integer updateTime;//   补充时间	public java.lang.Integer getStockId() {	    return this.stockId;	}	public void setStockId(java.lang.Integer stockId) {	    this.stockId=stockId;	}	public java.lang.String getStockName() {	    return this.stockName;	}	public void setStockName(java.lang.String stockName) {	    this.stockName=stockName;	}	public java.lang.String getStockNum() {	    return this.stockNum;	}	public void setStockNum(java.lang.String stockNum) {	    this.stockNum=stockNum;	}	public java.lang.String getStockInventory() {	    return this.stockInventory;	}	public void setStockInventory(java.lang.String stockInventory) {	    this.stockInventory=stockInventory;	}	public java.lang.Integer getStockEarly() {	    return this.stockEarly;	}	public void setStockEarly(java.lang.Integer stockEarly) {	    this.stockEarly=stockEarly;	}	public java.lang.Integer getCreateTime() {	    return this.createTime;	}	public void setCreateTime(java.lang.Integer createTime) {	    this.createTime=createTime;	}	public java.lang.Integer getUpdateTime() {	    return this.updateTime;	}	public void setUpdateTime(java.lang.Integer updateTime) {	    this.updateTime=updateTime;	}
}

