<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加品牌</title>
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
		<a href="/commodityBrand/list.html" class="actionBtn">品牌分类</a>
		<c:if test="${empty commodityBrand.brandId }">
		添加分类
		</c:if>
		<c:if test="${not empty commodityBrand.brandId }">
		修改分类
		</c:if>
	</h3>
	
	<form name="editForm" id="editForm" method="post" >
	<c:if test="${not empty commodityBrand.brandId }">
	<input type="hidden" name="BrandId" value="${commodityBrand.brandId }" />
	</c:if>
	<table class="tableBasic" style="width: 100%;  ">
		<tr>
			<td width="150px">品牌名称：</td>
			<td><input type="text" name="BrandName" id="BrandName" class="inpMain" value="${commodityBrand.brandName }"/></td>
		</tr>
		<tr>
			<td>品牌描述:</td>
			<td>
				<input type="text" name="BrandInfo" id="BrandInfo" class="inpMain" value="${commodityBrand.brandInfo }"/>
			</td>
		</tr>
			<tr>
					<td><label>品牌图片</label>：</td>
					<td>
						<div style="float: left;">
							<c:set var="bigImageUrl" value="#"></c:set>
							<c:if test="${not empty commodityBrand.brandImg }">
								<c:set var="bigImageUrl" value="${commodityBrand.brandImg }"></c:set>
							</c:if>
							<img alt="选择文件" title="选择文件" id="imageConPathImg" src="${commodityBrand.brandImg  }"
								onclick="getElementById('imageConPathFile').click()">
							<input class="inpFlie" type="file" id="imageConPathFile" accept=".jpg,.jpeg,.gif,.png" />
							<input type="hidden" id="BrandImg" name="BrandImg" value="${commodityBrand.brandImg }" />
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
		var url = '/commodityBrand/save.html';
		var BrandName = $("#BrandName").val();
		if(BrandName=='')
		{
			alert('品牌名称不能为空！');
			return;	
		}
		$.post(url, $('#editForm').serialize(), function(data){
			if (data == 'success') {
				window.location.href = document.referrer;
			} else {
				alert('未知异常，可能该品牌名已存在');
				$('#name').focus();
			}
		});
	});
});

$('#imageConPathFile').change(function() {
	uploadImage($(this), $('#imageConPathImg'), $('#BrandImg'));
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
		url : '/commodityBrand/uploadFile.html',
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
activeMenu('品牌管理');
</script>
</body>
</html>