<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>闲置订单</title>
<jsp:include page="common/public_js_css.jsp" flush="true"/>
<script type="text/javascript" src="../../style/js/echo-area-and-type.js"></script>
<script type="text/javascript" src="../../style/plugins/datePicker/WdatePicker.js"></script>
<style type="text/css">
input[type="file"] {
	opacity: 0;
	filter: alpha(opacity = 0);
	width: 0px;
	height: 0px;
}


</style>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
		<a href="/idle/list.html" class="actionBtn">闲置管理</a>
		</h3>
		<form method="post" name="editForm" id="editForm" method="">
			<%-- <c:if test="${not empty vo.commodityId }">
				<input type="hidden" name="commodityId" value="${vo.commodityId }" />
			</c:if>
			<input type="hidden" name="memberLever" value="2" /> --%>
			
			<table class="tableBasic" style="width: 100%;  ">
				<tr>
					<td>订单号：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="orderNum"  name="orderNum" value="${vo.orderNum }" />
					</td>
				</tr>
				<tr>
					<td>用户手机号：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="mobile"  name="mobile" value="${vo.mobile }" />
					</td>
				</tr>
				<tr>
					<td>品牌：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="brand"  name="brand" value="${vo.brand }" />
					</td>
				</tr>
				<tr>
					<td>材质：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="material"  name="material" value="${vo.material }" />
					</td>
				</tr>
				<tr>
					<td>类目：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="catagory"  name="catagory" value="${vo.catagory }" />
					</td>
				</tr>
				<tr>
					<td>存放天数：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="residueDay"  name="residueDay" value="${vo.residueDay }" />
					</td>
				</tr>
				<tr>
					<td>使用年限：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="useYears"  name="useYears" value="${vo.useYears }" />
					</td>
				</tr>
				<tr>
					<td>订单状态：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="status"  name="status" value="${vo.status }" />
					</td>
				</tr>
				<tr>
					<td>产品图：</td>
					<td>
						<a></a>	
					</td>
				</tr>
				<tr>
					<td>创建时间：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="createTime"  name="createTime" value="${vo.createTime }" />
					</td>
				</tr>
				<tr>
					<td>寄送快递：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="express"  name="express" value="${vo.express }" /><input readonly="readonly" class="inpMain required" id="expressCode"  name="expressCode" value="${vo.expressCode }" />
					</td>
				</tr>
				<tr>
					<td>返回快递：</td>
					<td>
						<input readonly="readonly" class="inpMain required" id="returnExpress"  name="returnExpress" value="${vo.returnExpress }" /><input readonly="readonly" class="inpMain required" id="returnCode"  name="returnCode" value="${vo.returnCode }" />
					</td>
				</tr>
			</table>
		</form>
	</div>
			
</body>


<script type="text/javascript">
	$(function() {}
	
		
	
	
</script>
</html>