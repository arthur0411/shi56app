<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品sku管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<input type="hidden" name="commodityId" value="${commodityId }" />
		<h3>
		<a href="/commoditySku/add.html?commodityId=${commodityId}" class="actionBtn add">添加sku</a>商品sku管理
		
		</h3>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>SkuId</th>
						<th>sku专图</th>
					 	<th>尺寸</th> 
						<th>颜色</th>
						<th>售价</th>
						<th>日租金</th>
						<th>可售库存</th>
						<th>可租库存</th>
						<th>销量</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty commoditySkuList}">
							<c:forEach items="${commoditySkuList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td>${list.skuId }</td>
									<td><img width="50px;" height="50px;" src="${list.img }"></td> 
									<td>${list.commoditySize }</td>
									<td>${list.color }</td>
									<td>${list.skuSale }</td>
									<td>${list.skuRent }</td>
									<td>${list.saleStock }</td>
									<td>${list.rentStock }</td>
									<td>${list.commodityHot }</td>
									<td>
										<a href="/commoditySku/edit.html?id=${list.skuId }&commodityId=${commodityId}">编辑</a>&nbsp;|&nbsp;
										<a href="javascript:delCommodityTag(${list.skuId });">删除</a>
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
		<div class="clear"></div>
		<div class="page_and_btn">${vo.page.pageStr }</div>
	</div>

	<script type="text/javascript">
		$(function() {
			// 选中左侧相应的菜单
			activeMenu('商品运营');
		});
		
		
		
		function delCommodityTag(vid){
			if(confirm("确定要删除该记录？谨慎操作！")){
				var url = "/commoditySku/delete.html?id="+vid;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
	</script>
</body>
</html>
