<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>闲置管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
			闲置管理
		</h3>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>ID</th>
						<th>品牌</th>
						<th>新旧程度</th>
						<th>材质</th>
						<th>提交时间</th>
						<th>用户手机号码</th>
						<th>审核状态</th>
						<th>评估价值</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty shopIdleList}">
							<c:forEach items="${shopIdleList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td>${list.id }</td>
									<td>${list.brand }</td>
									<td>${list.newInfo }</td>
									<td>${list.material }</td>
									<td>${list.createTime }</td>
									<td>${list.mobilePhone }</td>
									<td>
										<c:choose>
											<c:when test="${list.auditStatus == 1 }">
												<span class="text_red">未审核</span>
											</c:when>
											<c:when test="${list.auditStatus == 2 }">
												<span>审核成功</span>
											</c:when>
											<c:when test="${list.auditStatus == 3 }">
												<span>审核失败</span>
											</c:when>
											<c:otherwise>未知</c:otherwise>
										</c:choose>
									</td>
									<td>${list.evaluationValue }</td>
									<td>
										<a href="/idle/auditStatusSuccess.html?id=${list.id }&status=2">审核成功</a>&nbsp;|&nbsp;
										<a href="/idle/auditStatusSuccess.html?id=${list.id }&status=3">审核失败</a>&nbsp;|&nbsp;
										<a href="#">估值</a>
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
			activeMenu('闲置管理');
		});
	</script>
</body>
</html>
