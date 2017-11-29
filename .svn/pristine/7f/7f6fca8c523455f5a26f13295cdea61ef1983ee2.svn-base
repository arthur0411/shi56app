<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>轮播图管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
			轮播图管理
			<a href="/shopcarousel/toAdd.html" class="actionBtn add">添加轮播图</a>
		</h3>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>轮播小图</th>
						<th>排序</th>
						<th>创建时间</th>
						<th>轮播大图</th>
						<th>跳转链接</th>
						<th>高度</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty shopCarouselList}">
							<c:forEach items="${shopCarouselList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td><img width="50px;" height="50px;"  src="${list.carousel_img }"></td>
									<td>${list.rank }</td>
									<td>${list.create_time }</td>
									<td><img width="50px;" height="50px;"  src="${list.carousel_bigimg }"></td>
									<td>${list.url }</td>
									<td>${list.height }</td>
									<td>
										<a href="/shopcarousel/edit.html?id=${list.carousel_id }">编辑</a>&nbsp;|&nbsp;
										<a href="javascript:delshopcarousel(${list.carousel_id });">删除</a>
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
			activeMenu('轮播图管理');
		});
		
		function delshopcarousel(vid){
			if(confirm("确定要删除该记录？")){
				var url = "/shopcarousel/delete.html?id="+vid;
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
