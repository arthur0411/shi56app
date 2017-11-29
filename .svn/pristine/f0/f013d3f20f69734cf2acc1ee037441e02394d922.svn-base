<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>角色管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
<div class="mainBox">
	<h3>
		<a href="/role.html" class="actionBtn">角色管理</a>
		<c:if test="${empty role.roleId }">
		添加角色
		</c:if>
		<c:if test="${not empty role.roleId }">
		修改角色
		</c:if>
	</h3>
	
	<form name="editForm" id="editForm" method="post" >
	<c:if test="${not empty role.roleId }">
	<input type="hidden" name="roleId" value="${role.roleId }" />
	</c:if>
	<table class="tableBasic list" style="width: 100%;  ">
		<tr>
			<td width="150px">角色名称：</td>
			<td><input type="text" name="roleName" id="roleName" class="inpMain" value="${role.roleName }"/></td>
		</tr>
	</table>
	</form>	
</div>
<script type="text/javascript">

// 选中左侧相应的菜单
activeMenu('角色管理');
</script>
</body>
</html>