<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>会员管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
			客户列表
		</h3>
		<form action="/shopUser/list.html" method="post" name="searchForm" id="searchForm">
			<div class="filter">
				<select name="userVip" id="userVip">
					<option value="">会员等级</option>
					<option value="0" <c:if test="${vo.userVip=='0'}">selected</c:if>>非会员</option>
					<option value="1" <c:if test="${vo.userVip=='1'}">selected</c:if>>普通会员</option>
					<option value="2" <c:if test="${vo.userVip=='2'}">selected</c:if>>体验会员</option>
					<option value="3" <c:if test="${vo.userVip=='3'}">selected</c:if>>月度会员</option>
					<option value="4" <c:if test="${vo.userVip=='4'}">selected</c:if>>季度会员</option>
					<option value="5" <c:if test="${vo.userVip=='5'}">selected</c:if>>年度会员</option>
				</select>
				
				用户名：<input type="text" class="inpMain" placeholder="支持模糊查询" name="name" id="name"
					value="${vo.name }" />
				<input class="btn" type="submit" value="搜索" />
			</div>
			
		</form>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>ID</th>
						<th>用户名</th>
						<th>手机号码</th>
						<th>创建时间</th>
						<th>上次登录时间</th>
						<th>用户押金</th>
						<th>用户余额</th>
						<th>免费期限</th>
						<th>月卡截止时间</th>
						<th>状态</th>
						<th>会员等级</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty shopUserList}">
							<c:forEach items="${shopUserList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td>${list.id }</td>
									<td>${list.name }</td>
									<td>${list.mobilePhone }</td>
									<td><fmt:formatDate value="${list.register_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td><fmt:formatDate value="${list.last_login_time }" pattern="yyyy-MM-dd HH:mm:ss"/></td>
									<td>${list.deposit }</td>
									
									<td>${list.balance }</td>
									<td>${list.reductionDays }</td>
									<td>${list.time_over }</td>
									<td>
										<c:if test="${list.status=='0'}">正常</c:if>
										<c:if test="${list.status=='1'}">删除 </c:if>
									</td>
									<td>
										<c:choose>
											<c:when test="${list.vipName == null }">非会员</c:when>
											<c:otherwise>${list.vipName}</c:otherwise>
										</c:choose>
									</td>
									<td>
										<a href="/shopUser/selectInfo.html?id=${list.id }">查看详情</a>&nbsp;|&nbsp;
										<a href="/shopTradingHistory/list.html?userId=${list.id}&remark3=${list.name}">交易记录</a>&nbsp;|&nbsp;
										<a href="javascript:delShopVip(${list.id });">删除</a>
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
		<div class="page_and_btn">
			<div>
				<a href="javascript:exportwithdrawals();" class="btn">
					<em>导出至excel</em>
				</a>
			</div>
		${vo.page.pageStr }</div>
	</div>

<script type="text/javascript">


	function delShopVip(vid){
			if(confirm("确定要删除该记录？")){
				var url = "updateUserStatus.html?id="+vid;
				$.get(url,function(data){
					if(data=="success"){
						document.location.reload();
					}
				});
			}
		}
		
		activeMenu('客户列表');
		
		
		function exportwithdrawals() {
			//var status = $("#status").val();
			//var mobilePhone = $("input[name='mobilePhone']").val();
			document.location = "/shopUser/excel.html" //?status=" + status + "&mobilePhone=" + mobilePhone ;
		}
	</script>
</body>
</html>
