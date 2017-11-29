<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>订单查看</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
			订单查看
		
		</h3>
		<form action="" method="post" name="searchForm" id="searchForm">
		</form>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>订单号</th>
						<th>订单类别</th>
					 	<th>订单状态</th> 
						<th>商品名称</th>
						<th>品牌名称</th>
						<th>下单时间</th>
						<th>归还时间</th>
					</tr>
					<c:choose>
						<c:when test="${not empty supplierList}">
							<c:forEach items="${supplierList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td>${list.order_ }</td>
									<td>${list.brand_name }</td>
									<td>${list.create_time }</td>
									<td>${list.update_time }</td>
									<td>${list.brand_info }</td>
									
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
			activeMenu('供应商');
		});
		
		
		
		
	</script>
</body>
</html>
