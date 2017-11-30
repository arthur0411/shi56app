<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>心愿管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
			心愿管理
		</h3>
		<form action="/wishList/list.html" method="post" name="searchForm" id="searchForm">
		</form>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>心愿ID</th>
						<th>用户手机号</th>
						<th>商品名称</th>
						<th>商品图片</th>
						<th>提交时间</th>
					</tr>
					<c:choose>
						<c:when test="${not empty wishList}">
							<c:forEach items="${wishList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td>${list.id }</td>
									<td>${list.mobilePhone }</td>
									<td>${list.commodity_name }</td>
									<td><img width="50px;" height="50px;" src="${list.commodity_img }"></td>
									<td>${list.createTime }</td>
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
			activeMenu('心愿管理');
		});
	</script>
</body>
</html>
