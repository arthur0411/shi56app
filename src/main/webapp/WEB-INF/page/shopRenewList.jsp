<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>年卡记录</title>
	<jsp:include page="common/public_js_css.jsp" flush="true"/>
	<script type="text/javascript" src="../../style/plugins/datePicker/WdatePicker.js"></script>
</head>
<body style="background-color: #FFFFFF;">
<div class="mainBox">
	<h3>年卡记录</h3>
	<form action="/renew/list.html" method="post" name="searchForm" id="searchForm">
		<div class="filter">
			
			用户ID：
			<input type="text" class="inpMain short" placeholder="" name="userId" id="userId"
				   value="${vo.userId }"/>&nbsp;&nbsp;
		<input class="btn" type="submit" value="搜索" />
		</div>
		
	</form>
	<div id="list">
		<form name="deleteForm" id="deleteForm">
			<table style="width: 100%;" class="tableBasic list">
				<tr>
					<th>编号</th>
					<th>用户</th>
					<th>交易编号</th>
					<th>月卡类型</th>
					<th>购买时间</th>
					<th>到期时间</th>
				</tr>
				<c:choose>
					<c:when test="${not empty renewList}">
						<c:forEach items="${renewList}" var="list" varStatus="vs">
							<tr class="main_info">
								<td>${list.id}</td>
								<td>${list.user_id }</td>
								<td>${list.recharge_id }</td>
								<td>
									<c:if test="${list.vip_id=='2'}">体验卡</c:if>
									<c:if test="${list.vip_id=='3'}">月卡</c:if>
									<c:if test="${list.vip_id=='4'}">季卡</c:if>
									<c:if test="${list.vip_id=='5'}">年卡</c:if>
								</td>
								<td>${list.create_time}</td>
								<td>${list.end_time}</td>
							</tr>
						</c:forEach>
					</c:when>
					<c:otherwise>
						<tr class="main_info">
							<td colspan="10">暂无数据o(&gt;﹏&lt;)o</td>
						</tr>
					</c:otherwise>
				</c:choose>
			</table>
		</form>
	</div>
	<div class="clear"></div>
	<div class="page_and_btn">
		<div class="action">
			<input type="button" id="btn_excel" class="btn" value="导出列表" title="导出列表" />
		</div>
		${vo.page.pageStr }
	</div>
</div>

<script type="text/javascript">
	$(function () {
		// 选中左侧相应的菜单
		activeMenu('月卡购买查询');
	});


	$(function () {
		
		$('#btn_excel').click(function () {
			btn_disable($(this));
			document.location = "/renew/excel.html?" + $('#searchForm').serialize();
			setTimeout('btn_enable($("#btn_excel"))', 10000);
		}); 

		$('#btn_today').click(function () {
			var today = date_format(new Date(), '-');
			$('#dateStart').val(today + ' 00:00:00');
			$('#dateEnd').val(today + ' 23:59:59');
		})
	});

</script>
</body>
</html>
