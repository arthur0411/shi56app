<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加配置</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<style type="text/css">
#imageConPathImg {
	width: 100px;
	height: 100px;
	cursor: pointer;
	border: 1px dotted gray;
}
</style>
<body style="background-color: #FFFFFF;">
<div class="mainBox">
	<h3>
		<a href="/commodityTag/list.html" class="actionBtn">商品分类</a>
		<c:if test="${empty commodityTag.tagId }">
		添加分类
		</c:if>
		<c:if test="${not empty commodityTag.tagId }">
		修改分类
		</c:if>
	</h3>
	
	<form name="editForm" id="editForm" method="post" >
	<c:if test="${not empty commodityTag.tagId }">
	<input type="hidden" name="tagId" value="${commodityTag.tagId }" />
	</c:if>
	<table class="tableBasic" style="width: 100%;  ">
		<tr>
			<td width="150px">分类名称：</td>
			<td><input type="text" name="tagName" id="tagName" class="inpMain" value="${commodityTag.tagName }"/></td>
		</tr>
		<tr>
			<td>分类排序:</td>
			<td>
				<input type="text" name="tagOrder" id="tagOrder" class="inpMain" value="${commodityTag.tagOrder }"/>
			</td>
		</tr>
		<tr>
			<td>英文名字:</td>
			<td>
				<input type="text" name="tagEngilsh" id="tagEngilsh" class="inpMain" value="${commodityTag.tagEngilsh }"/>
			</td>
		</tr>
			<tr>
					<td><label>商品封面图片</label>：</td>
					<td>
						<div style="float: left;">
							<c:set var="bigImageUrl" value="#"></c:set>
							<c:if test="${not empty commodityTag.tagImg }">
								<c:set var="bigImageUrl" value="${commodityTag.tagImg }"></c:set>
							</c:if>
							<img alt="选择文件" title="选择文件" id="imageConPathImg" src="${commodityTag.tagImg  }"
								onclick="getElementById('imageConPathFile').click()">
							<input class="inpFlie" type="file" id="imageConPathFile" accept=".jpg,.jpeg,.gif,.png" />
							<input type="hidden" id="tagImg" name="tagImg" value="${commodityTag.tagImg }" />
						</div>
						
					</td>
				</tr>
				
		<tr>
			<td></td>
			<td><input id="btn_submit" class="btn" type="button" value="提交" /></td>
		</tr>
	</table>
	</form>	
</div>
<script type="text/javascript">
$(function(){
	$('#btn_submit').click(function(){
		var url = '/commodityTag/save.html';
		var tagName = $("#tagName").val();
		if(tagName=='')
		{
			alert('分类名称不能为空！');
			return;	
		}
		$.post(url, $('#editForm').serialize(), function(data){
			if (data == 'success') {
				window.location.href = document.referrer;
			} else {
				alert('未知异常，可能该分类名已存在');
				$('#name').focus();
			}
		});
	});
});

$('#imageConPathFile').change(function() {
	uploadImage($(this), $('#imageConPathImg'), $('#tagImg'));
});


/**
 * 上传图片时调用
 */
function uploadImage($fileId, $imageObj, $image) {
	//创建FormData对象
	var data = new FormData();

	//为FormData对象添加数据
	$.each($fileId[0].files, function(i, file) {
		data.append('fileName', file);
	});

	$.ajax({
		url : '/commodityTag/uploadFile.html',
		type : 'POST',
		data : data,
		cache : false,
		contentType : false, //不可缺
		processData : false, //不可缺
		success : function(result) {
			if (result.result = 'success') {
				$imageObj.attr('src', result.filePath);
				$image.val(result.filePath);
			} else {
				alert('我的天呐，上传失败了，赶紧问一下程序员GG');
			}
		},
		error : function() {
			alert('请先选择您要上传的图片');
		}
	});
}

// 选中左侧相应的菜单
activeMenu('添加配置');
</script>
</body>
</html>