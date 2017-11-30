<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
<style type="text/css">
	.edit_cover{ width:100%; height:100%; position:fixed; top:0; left:0; z-index:99999; background:rgba(0,0,0,0.3);}
	.edit_cover p{ width:50px; height:50px; margin:30%; margin-top:200px;}
	.edit_cover p img{ width:500px; height:250px;}
</style>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
		<a href="/commodity/add.html" class="actionBtn add">添加商品</a>商品管理
		
		</h3>
		<form action="/commodity/list.html" method="post" name="searchForm" id="searchForm">
			<div class="filter">

				<select name="brandId" id="brandId">
					<option value="">品牌分类</option>
					<c:forEach items="${brandList}" var="list">
						<option value="${list.brand_id}" <c:if test="${vo.brandId==list.brand_id}"> selected </c:if> >${list.brand_name}</option>
					</c:forEach>
				</select>

				<select name="commodityTagId" id="commodityTagId">
					<option value="">商品分类</option>
					<c:forEach items="${commodityTagList}" var="list">
						<option value="${list.tag_id }" <c:if test="${vo.commodityTagId==list.tag_id}"> selected </c:if> >${list.tag_name }</option>
					</c:forEach>
				</select>
		
				<select name="isUpjia" id="isUpjia" style="margin-right: 15px;">
					<option value="">是否上架</option>
					<option value="no" <c:if test="${vo.isUpjia=='no'}">selected</c:if>>否</option>
					<option value="yes" <c:if test="${vo.isUpjia=='yes'}">selected</c:if>>是</option>
				</select>

				<select name="isPresale" style="margin-right: 15px;">
					<option value="">是否预售</option>
					<option value="no" <c:if test="${vo.isPresale=='no'}">selected</c:if>>否</option>
					<option value="yes" <c:if test="${vo.isPresale=='yes'}">selected</c:if>>是</option>
				</select>
				
				<select name="isRecommend" style="margin-right: 15px;">
					<option value="">是否推荐</option>
					<option value="no" <c:if test="${vo.isRecommend=='no'}">selected</c:if>>否</option>
					<option value="yes" <c:if test="${vo.isRecommend=='yes'}">selected</c:if>>是</option>
				</select>
				
				
				<!--<select name="dealWay" style="margin-right: 15px;">
					<option value="">可交易方式</option>
					<option value="1" <c:if test="${vo.dealWay=='1'}">selected</c:if>>只可售</option>
					<option value="2" <c:if test="${vo.dealWay=='2'}">selected</c:if>>可租可售</option>
				</select>-->

			</div>
		
			<div class="filter">
				商品搜索：<input type="text" class="inpMain" placeholder="商品ID" name="commodityId" id="commodityId"
					value="${vo.commodityId }"  />
					名称搜索：<input type="text" class="inpMain" placeholder="支持模糊查询" name="commodityName" id="commodityName"
					value="${vo.commodityName }" />
				<input class="btn" type="submit" value="搜索" />
				<input class="btn" type="button" value="随机置顶" id="reloadAllBtn" />
			</div>

		</form>

		
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th><input type="checkbox" id="selectAll" name="selectAll" />全选  </th>
						<th>ID</th>
						<th>商品名称</th>
						<th>封面</th> 
						<th>价格</th>
						<th>分类</th>
						<th>预售</th>
						<th>上架</th>
						<th>推荐</th> 
						<th>创建时间</th>
						<th>最后操作时间</th>
						<th>库存</th>
						<th>销量</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty commodityList}">
							<c:forEach items="${commodityList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td><input type="checkbox" value="${list.commodity_id}" name="chckBox"/></td>  
									<td>${list.commodity_id }</td>
									<!--<td>
										<c:choose>
											<c:when test="${list.dealWay == '1' }">只可售</c:when>
											<c:when test="${list.dealWay == '2' }">可租可售</c:when>
										</c:choose>
									</td>-->
									<td>${list.commodity_name }</td>
									<td><img width="50px;" height="50px;" src="${list.icon }"></td>
									<td>${list.commodity_price }</td>
									<td>${list.tag_name }</td>
									<td>
										<c:choose>
											<c:when test="${list.is_presale == 'yes' }">是</c:when>
											<c:when test="${list.is_presale == 'no' }">否</c:when>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${list.is_upjia == 'yes' }">是</c:when>
											<c:when test="${list.is_upjia == 'no' }">否</c:when>
											<c:otherwise>未知</c:otherwise>
										</c:choose>
									</td>
									<td>
										<c:choose>
											<c:when test="${list.is_recommend == 'yes' }">是</c:when>
											<c:when test="${list.is_recommend == 'no' }">否</c:when>
											<c:otherwise>未知</c:otherwise>
										</c:choose>
									</td>
									<td>${list.create_time }</td>
									<td>${list.down_time }</td>
						
									<td>${list.sale_stock }</td>
									<td>${list.commodity_hot }</td>
								
									
									<td>
										<a href="javascript:reloadCommodity('${list.commodity_id}');">人工置顶</a>&nbsp;|&nbsp;
										<a href="/commodity/edit.html?id=${list.commodity_id }">编辑信息</a>&nbsp;|&nbsp;
										<!--<a href="/commoditySku/list.html?id=${list.commodity_id }">sku管理</a>&nbsp;|&nbsp; -->
										<a href="/commodity/editPic.html?id=${list.commodity_id }">修改图片</a>&nbsp;|&nbsp;
										<a href="javascript:deleteCommodity('${list.commodity_id }');">删除</a>&nbsp; 
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr class="main_info">
								<td colspan="5">暂无数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</form>
		</div>
				
		<div>&nbsp;&nbsp;<input class="btn" type="button" value="批量上架" id="upJiaBtn" />
			&nbsp;&nbsp;<input class="btn" type="button" value="批量下架" id="downJiaBtn" />
			&nbsp;&nbsp;<input class="btn" type="button" value="批量删除" id="deleteBtn" />
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type="text" style="width:100px" placeholder="商品价格" name="jiage" id="jiage"/>
			<input class="btn" type="button" value="一键挂灰" id="guahuiBtn" />
			</div>
		<div class="clear"></div>
		<div class="page_and_btn">${vo.page.pageStr }</div>
	</div>

	<script type="text/javascript">
		$(function() {
			// 选中左侧相应的菜单
			activeMenu('商品运营');
			
		});
		
		$("#reloadAllBtn").click( 
				  function(){ 
				   var url = "/commodity/reloadAll.html";
				   addCover();
				   $.get(url, function(data) {
						if (data == "0") {
							removeCover();
							document.location.reload();
						} else {
							document.location.reload();
						}
					});
				  } 
			);
		
		
		$("#guahuiBtn").click( 
				  function(){ 
				   var gPrice = $('#jiage').val();
				   var url = "/commodity/guahui.html?price=" + gPrice;
				   $.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				  } 
			);
		
			$("#selectAll").click( 
				  function(){ 
				    if(this.checked){ 
				        $("input[name='chckBox']").each(function() {  
			           			 $(this).prop("checked", true); 
			       			 }); 
				    }else{ 
				        $("input[name='chckBox']").each(function() {  
					            $(this).prop("checked", false); 
					        }); 
				    } 
				  } 
			);
		
		
			$("#upJiaBtn").click( 
				  function(){ 
				  	var arr1 = [];
				  	$("input[name='chckBox']").each(function() {  
			           	if(this.checked){
			           		arr1.push($(this).val());
			           	}
			       	});
			       	var url = "/commodity/upJia.html?arr1=" + arr1;
					$.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				  }
			);
			
			$("#downJiaBtn").click( 
				  function(){ 
				  	var arr1 = [];
				  	$("input[name='chckBox']").each(function() {  
			           	if(this.checked){
			           		arr1.push($(this).val());
			           	}
			       	});
			       	var url = "/commodity/downJia.html?arr1=" + arr1;
					$.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				  }
			);
			
			$("#deleteBtn").click( 
				  function(){ 
				  	if (confirm("确定要删除商品吗？")) {
					  	var arr1 = [];
					  	$("input[name='chckBox']").each(function() {  
				           	if(this.checked){
				           		arr1.push($(this).val());
				           	}
				       	});
				       	var url = "/commodity/deleteMany.html?arr1=" + arr1;
						$.get(url, function(data) {
							if (data == "1") {
								alert('成功');
								document.location.reload();
							} else {
								alert('失败');
							}
						});
					  }
					}  
			);
			
		//|&nbsp;<a href="javascript:delCommodityTag(${list.commodity_id });">删除</a> 
		function delCommodityTag(vid){
			if(confirm("确定要删除该记录？谨慎操作！")){
				var url = "/commodity/delete.html?id="+vid;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		
		function deleteCommodity(commodityId) {
			if (confirm("确定要删除商品吗？")) {
				var url = "/commodity/delete.html?id=" + commodityId;
				$.get(url, function(data) {
					if (data == "1") {
						alert('成功');
						document.location.reload();
					} else {
						alert('失败');
					}
				});
			}
		}
		
		function reloadCommodity(commodityId) {
			if (confirm("确定置顶该商品吗？")) {
				var url = "/commodity/reload.html?id=" + commodityId;
				$.get(url, function(data) {
					if (data == "0") {
						alert('成功');
						document.location.reload();
					} else {
						alert('失败');
					}
				});
			}
		}
		
		/**
		 * 添加遮罩
		 */
		function addCover(){
		   var o = document.body;
		   var div = document.createElement("div");
		   var p = document.createElement("p");
		   var img = document.createElement("img");
		   div.setAttribute("class","edit_cover");
		   div.setAttribute("id","edit_cover");
		   img.setAttribute("src","../../style/images/loading.gif");
		   p.appendChild(img);
		   div.appendChild(p);
		   o.appendChild(div);
		}
		
		/**
		 * 隐藏遮罩
		 */
		function removeCover(){
		  var o = document.body;
		  var div = document.getElementById("edit_cover");
		  o.removeChild(div);
		}
		
	</script>
</body>
</html>
