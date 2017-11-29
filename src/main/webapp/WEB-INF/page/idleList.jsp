<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>闲置管理</title>
<jsp:include page="common/public_js_css.jsp" flush="true"/>
<script type="text/javascript" src="../../style/js/echo-area-and-type.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>闲置订单</h3>
		<form action="/idle/list.html" method="post" name="userForm" id="userForm">
			
			<div class="filter" >
				
				订单状态：
				<select name="status" id="status" style="vertical-align: middle;">
					<option value="" >不限</option>
					<option value="1" <c:if test="${vo.status==1}">selected</c:if>>审核中</option>
					<option value="2" <c:if test="${vo.status==2}">selected</c:if>>审核失败</option>
					<option value="3" <c:if test="${vo.status==3}">selected</c:if>>审核通过</option>
					<option value="4" <c:if test="${vo.status==4}">selected</c:if>>寄送中</option>
					<option value="5" <c:if test="${vo.status==5}">selected</c:if>>验收失败</option>
					<option value="6" <c:if test="${vo.status==6}">selected</c:if>>验收完成</option>
					<option value="7" <c:if test="${vo.status==7}">selected</c:if>>返还中</option>
					<option value="8" <c:if test="${vo.status==8}">selected</c:if>>已归还</option>
				</select>
				
				<input type="text" class="inpMain short" placeholder="用户手机" name="mobile" value="${vo.mobile }" />

				<input type="text" class="inpMain short" placeholder="订单号 " name="orderNum" value="${vo.orderNum}" />
				<input class="btn" type="submit" value="搜索" />
			</div>

		</form>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;  " class="tableBasic list">
					<tr>
						<th>
							<input type="checkbox" name="sltAll" id="sltAll" onclick="sltAllUser()" />
						</th>
						<th>订单号</th>
						<th>用户手机号</th>
						<th>创建时间</th>
						<th>品牌</th>
						<th>材质</th>
						<th>类目</th>
						<th>存放天数</th>
						<th>使用年限</th>
						<th>产品图</th>
						<th>订单状态</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty idleList}">
							<c:forEach items="${idleList}" var="order" varStatus="vs">
								<tr class="main_info">
									<td>
										<input type="checkbox" name="orderIds" id="orderIds${order.id}" value="${order.id}" />
									</td>
									<td>${order.order_num}</td>
									<td>${order.mobile}</td>
									<td>${order.create_time}</td>
									<td>${order.brand}</td>
									<td>${order.material}</td>
									<td>${order.catagory}</td>
									<td>${order.residueDay}</td>
									<td>${order.use_years}</td>
									<td><a href="/idle/productPic.html?id=${order.id}">产品图</a></td>
									<td>
									<c:choose>
											<c:when test="${order.status == '1' }">审核中</c:when>
											<c:when test="${order.status == '2' }">审核失败</c:when>
											<c:when test="${order.status == '3' }">审核通过</c:when>
											<c:when test="${order.status == '4' }">寄送中</c:when>
											<c:when test="${order.status == '5' }">验收失败</c:when>
											<c:when test="${order.status == '6' }">验收完成</c:when>
											<c:when test="${order.status == '7' }">返还中</c:when>
											<c:when test="${order.status == '8' }">已返还</c:when>
										</c:choose>
									</td>
									<td>
										<a href="/idle/detail.html?id=${order.id}">更多信息</a>
										&nbsp;|&nbsp;<a href="javascript:checkFail('${order.id}','${order.status}');">审核失败</a>
										&nbsp;|&nbsp;<a href="javascript:checkPass('${order.id}','${order.status}');">审核通过</a>
										&nbsp;|&nbsp;<a href="javascript:acceptFail('${order.id}','${order.status}');">验收失败</a>
										&nbsp;|&nbsp;<a href="javascript:acceptPass('${order.id}','${order.status}');">验收完成</a>
										&nbsp;|&nbsp;<a href="javascript:returnOrder('${order.id}');">返还填写</a>
									</td>
								</tr>
							</c:forEach>
						</c:when>
						<c:otherwise>
							<tr class="main_info">
								<td colspan="23">没有相关数据</td>
							</tr>
						</c:otherwise>
					</c:choose>
				</table>
			</form>
		</div>
		<div class="clear"></div>
		<div class="page_and_btn">
			<div>
				<!-- <a href="javascript:exportwithdrawals();" class="btn">
					<em>导出至excel</em>
				</a>
				<input type="button" id="btn_excel" class="btn" value="批量导出" title="批量导出" />
				<input type="button" id="btn_hebingexcel" class="btn" value="合并导出" title="合并导出" />
				<input type="button" id="btn_report" class="btn" value="批量报表" title="批量报表" /> -->
			</div>
			${order.page.pageStr }
		</div>
	</div>

	<script type="text/javascript" src="../../style/plugins/datePicker/WdatePicker.js"></script>
	<script type="text/javascript">
		function sltAllUser() {
			if ($("#sltAll").prop("checked")) {
				$("input[name='orderIds']").prop("checked", true);
			} else {
				$("input[name='orderIds']").prop("checked", false);
			}
		}
		
		function returnOrder(){}
		
		function checkFail(id,status) {
			if(status=='1')
			{
				if (confirm("确定审核失败吗？")) {
					var url = "/idle/checkFail.html?id=" + id;
					$.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				}
			}else
			{
				alert('只能操未审核的订单！');
			}
		}
		
		function checkPass(id,status) {
			if(status=='1')
			{
				if (confirm("确定审核成功吗?")) {
					var url = "/idle/checkPass.html?id=" + id;
					$.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				}
			}else
			{
				alert('只能操作未审核的订单！');
			}
		}
		
		function acceptFail(id,status) {
			if(status=='4')
			{
				if (confirm("确定验收失败吗?")) {
					var url = "/idle/acceptFail.html?id=" + id;
					$.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				}
			}else
			{
				alert('只能操作寄送中的订单！');
			}
		}
		
		function acceptPass(id,status) {
			if(status=='4')
			{
				if (confirm("确定验收成功吗?")) {
					var url = "/idle/acceptPass.html?id=" + id;
					$.get(url, function(data) {
						if (data == "1") {
							alert('成功');
							document.location.reload();
						} else {
							alert('失败');
						}
					});
				}
			}else
			{
				alert('只能操作寄送中的订单！');
			}
		}


		

		$(function(){
		
			$('#btn_today').click(function(){
				var today = date_format(new Date(), '-');
				$('#beginTime').val(today + ' 00:00:00');
				$('#endTime').val(today + ' 23:59:59');
			});
		});
			
		// 选中左侧相应的菜单
		activeMenu('闲置管理');
		
		function exportwithdrawals() {
			//var status = $("#status").val();
			//var mobilePhone = $("input[name='mobilePhone']").val();
			document.location = "/shopIdle/excel.html" //?status=" + status + "&mobilePhone=" + mobilePhone ;
		}
		
	</script>
</body>
</html>
