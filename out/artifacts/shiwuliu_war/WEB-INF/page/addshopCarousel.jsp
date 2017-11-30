<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加轮播图</title>
<jsp:include page="common/public_js_css.jsp" flush="true"/>
	<style>
		input, img {
			vertical-align: middle;
		}
	</style>
</head>
<body style="background-color: #FFFFFF;">
	<div class="mainBox">
		<h3>
			<a href="/shopcarousel/list.html" class="actionBtn">轮播图管理</a>添加轮播图
		</h3>
		<form method="post" name="editForm" id="editForm">
			<input type="hidden" name="carouselId" value="${vo.carouselId }" />
			<table class="tableBasic" style="width: 100%;">
			
				<tr>
					<td>轮播小图：</td>
					<td>
						<input class="inpMain" type="text" id="title" name="carouselImg" value="${vo.carouselImg }"/>
						<c:set var="titleUrl" value=""></c:set>
						<c:if test="${not empty vo.carouselImg }">
							<c:set var="titleUrl" value="${vo.carouselImg }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="titleImg" src="${titleUrl }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="titleFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
				<tr>
					<td>轮播大图：</td>
					<td>
						<input class="inpMain" type="text" id="carousel_bigimg" name="carousel_bigimg" value="${vo.carousel_bigimg }"/>
						<c:set var="bannerUrl" value=""></c:set>
						<c:if test="${not empty vo.carousel_bigimg }">
							<c:set var="bannerUrl" value="${vo.carousel_bigimg }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="bannerImg" src="${bannerUrl }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="bannerFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
				<tr>
					<td>跳转链接：</td>
						<td><input class="inpMain short" id="url" name="url" value="${vo.url }"/>
						</td>
				</tr>
				<tr>
					<td>排序：</td>
					<td><input class="inpMain short" type="number" id="rank" name="rank" value="${vo.rank }"/></td>
				</tr>
				<tr>
					<td>高度：</td>
					<td><input class="inpMain short" type="number" id="height" name="height" value="${vo.height }"/></td>
				</tr>
				
				<tr>
					<td style="width: 150px;"></td>
					<td>
						<input id="btn_submit" class="btn" type="button" value="提交" />
					</td>
				</tr>
			</table>
		</form>
	</div>

	<script type="text/javascript">
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
				url : '/shopcarousel/uploadFile.html',
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
		
		
		$(function() {
			$('#btn_submit').click(function() {
				if ($('#invalid').is(':checked')) {
					$('#sort').val(0);
				}
				if (confirm('确定不是手抖吗？')) {
					var url = '/shopcarousel/save.html';
					$.post(url, $('#editForm').serialize(), function(data) {
						if (data == 'success') {
							alert('搞定啦！');
							window.location.href = '/shopcarousel/list.html';
						} else {
							alert(UNKNOWN_ERROR);
						}
					});
				}
			});
			
			$('#titleFile').change(function() {
				uploadImage($(this), $('#titleImg'), $('#title'));
			});
			
			$('#bannerFile').change(function() {
				uploadImage($(this), $('#bannerImg'), $('#carousel_bigimg'));
			});
			
			


			// 选中左侧相应的菜单
			activeMenu('轮播图管理');
		});
	</script>
</body>
</html>