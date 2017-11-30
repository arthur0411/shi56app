<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加Sku</title>
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
		<a href="/commodityBrand/list.html" class="actionBtn">sku分类</a>
		<c:if test="${empty commoditySku.skuId }">
		添加分类
		</c:if>
		<c:if test="${not empty commoditySku.skuId }">
		修改分类
		</c:if>
	</h3>
	
	<form name="editForm" id="editForm" method="post" >
	<c:if test="${not empty commoditySku.skuId }">
	<input type="hidden" name="skuId" value="${commoditySku.skuId }" />
	</c:if>
	<table class="tableBasic" style="width: 100%;  ">
		<input type="hidden" name="commodityId" value="${commodityId }" />
		<tr>
			<td width="150px">颜色：</td>
			<td><input type="text" name="color" id="color" class="inpMain" value="${commoditySku.color }"/></td>
		</tr>
		<tr>
			<td>尺寸:</td>
			<td>
				<input type="text" name="commoditySize" id="commoditySize" class="inpMain" value="${commoditySku.commoditySize }"/>
			</td>
		</tr>
			<tr>
					<td><label>sku专图</label>：</td>
					<td>
						<div style="float: left;">
							<c:set var="bigImageUrl" value="#"></c:set>
							<c:if test="${not empty commoditySku.img }">
								<c:set var="bigImageUrl" value="${commoditySku.img }"></c:set>
							</c:if>
							<img alt="选择文件" title="选择文件" id="imageConPathImg" src="${commoditySku.img  }"
								onclick="getElementById('imageConPathFile').click()">
							<input class="inpFlie" type="file" id="imageConPathFile" accept=".jpg,.jpeg,.gif,.png" />
							<input type="hidden" id="BrandImg" name="BrandImg" value="${commoditySku.img }" />
						</div>
						
					</td>
				</tr>
		
		<tr>
			<td>售价:</td>
			<td>
				<input type="text" name="skuSale" id="skuSale" class="inpMain" value="${commoditySku.skuSale }"/>
			</td>
		</tr>
		
		<tr>
			<td>日租金:</td>
			<td>
				<input type="text" name="skuRent" id="skuRent" class="inpMain" value="${commoditySku.skuRent }"/>
			</td>
		</tr>
		
		<tr>
			<td>可售库存:</td>
			<td>
				<input type="text" name="saleStock" id="saleStock" class="inpMain" value="${commoditySku.saleStock }"/>
			</td>
		</tr>
		
		<tr>
			<td>可租库存:</td>
			<td>
				<input type="text" name="rentStock" id="rentStock" class="inpMain" value="${commoditySku.rentStock }"/>
			</td>
		</tr>
		
		<tr>
			<td>销量:</td>
			<td>
				<input type="text" name="commodityHot" id="commodityHot" class="inpMain" value="${commoditySku.commodityHot }"/>
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
		var url = '/commoditySku/save.html';
		
		$.post(url, $('#editForm').serialize(), function(data){
			if (data == 'success') {
				window.location.href = document.referrer;
			} else {
				alert('未知异常');
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
		url : '/commoditySku/uploadFile.html',
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
activeMenu('商品运营');
</script>
</body>
</html>