<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>商品分类管理</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
		<a href="/commodityTag/add.html" class="actionBtn add">添加商品分类</a>商品分类管理
		
		</h3>
		<div id="list">
			<form name="deleteForm" id="deleteForm">
				<table style="width: 100%;" class="tableBasic list">
					<tr>
						<th>ID</th>
						<th>分类名称</th>
					 	<th>分类图片</th> 
						<th>创建时间</th>
						<th>修改时间</th>
						<th>分类英文名</th>
						<th>分类排序</th>
						<th>操作</th>
					</tr>
					<c:choose>
						<c:when test="${not empty commodityTagList}">
							<c:forEach items="${commodityTagList}" var="list" varStatus="vs">
								<tr class="main_info">
									<td>${list.tag_id }</td>
									<td>${list.tag_name }</td>
									<td><img width="50px;" height="50px;" src="${list.tag_img }"></td> 
									<td>${list.create_time }</td>
									<td>${list.update_time }</td>
									<td>${list.tag_engilsh }</td>
									<td>${list.tag_order }</td>
									<td>
										<a href="/commodityTag/edit.html?id=${list.tag_id }">编辑</a>&nbsp;|&nbsp;
										<a href="javascript:delCommodityTag(${list.tag_id });">删除</a>
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
			activeMenu('商品分类');
		});
		
		
		
		function delCommodityTag(vid){
			if(confirm("确定要删除该记录？谨慎操作！")){
				var url = "/commodityTag/delete.html?id="+vid;
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
