<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改专题</title>
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
			<a href="/hajSpecialTopic/list.html" class="actionBtn">专题管理</a>修改专题
		</h3>
		<form method="post" name="editForm" id="editForm">
			<input type="hidden" name="id" value="${vo.id }" />
			<table class="tableBasic" style="width: 100%;">
			
				
				<tr>
					<td>专题分类：</td>
					<td>
						<label>
							<select name="type" id="type">
								<c:forEach items="${mapList}" var="list">
									<option value="${list.typeId }" <c:if test="${vo.type==list.typeId}">selected</c:if>>${list.typeValue }</option>
								</c:forEach>
							</select>
						</label>
					</td>
				</tr>
				
				<tr>
					<td>专题名：</td>
					<td><input class="inpMain" type="text" name="name" value="${vo.name }"/></td>
				</tr>
			
				<tr>
					<td>banner图片：</td>
					<td>
						<input class="inpMain" type="text" id="banner" name="banner" value="${vo.banner }"/>
						<c:set var="bannerUrl" value=""></c:set>
						<c:if test="${not empty vo.banner }">
							<c:set var="bannerUrl" value="${vo.banner }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="bannerImg" src="${bannerUrl }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="bannerFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
				<tr>
					<td>内容图片：</td>
					<td>
						<input class="inpMain" type="text" id="method" name="method" value="${vo.method }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.method }">
							<c:set var="methodUrl" value="${vo.method }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="methodImg" src="${methodUrl }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
				<tr>
					<td>排序：</td>
					<td><input class="inpMain short" type="number" id="sort" name="sort" value="${vo.sort }"/></td>
				</tr>
			
				<tr>
					<td>选择：</td>
					<td>
						<label>
							<input type="checkbox" id="invalid" name="invalid" value="1" <c:if test="${vo.invalid==1}">checked</c:if> />
							弃用
						</label>
					</td>
				</tr>
				
					<tr>
					<td>图片1：</td>
					<td>
						<input class="inpMain" type="text" id="img1" name="img1" value="${vo.img1 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img1 }">
							<c:set var="methodUrl" value="${vo.img1 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img1methodImg" src="${img1 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img1methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
				
					<tr>
					<td>图片2：</td>
					<td>
						<input class="inpMain" type="text" id="img2" name="img2" value="${vo.img2 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img2 }">
							<c:set var="methodUrl" value="${vo.img2 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img2methodImg" src="${img2 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img2methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
				
					<tr>
					<td>图片3：</td>
					<td>
						<input class="inpMain" type="text" id="img3" name="img3" value="${vo.img3 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img3 }">
							<c:set var="methodUrl" value="${vo.img3 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img3methodImg" src="${img3 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img3methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
					<tr>
					<td>图片4：</td>
					<td>
						<input class="inpMain" type="text" id="img4" name="img4" value="${vo.img4 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img4 }">
							<c:set var="methodUrl" value="${vo.img4 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img4methodImg" src="${img4 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img4methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
					<tr>
					<td>图片5：</td>
					<td>
						<input class="inpMain" type="text" id="img5" name="img5" value="${vo.img5 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img5 }">
							<c:set var="methodUrl" value="${vo.img5 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img5methodImg" src="${img5 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img5methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
					<tr>
					<td>图片6：</td>
					<td>
						<input class="inpMain" type="text" id="img6" name="img6" value="${vo.img6 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img6 }">
							<c:set var="methodUrl" value="${vo.img6 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img6methodImg" src="${img6 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img6methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
				</tr>
					<tr>
					<td>图片7：</td>
					<td>
						<input class="inpMain" type="text" id="img7" name="img7" value="${vo.img7 }"/>
						<c:set var="methodUrl" value=""></c:set>
						<c:if test="${not empty vo.img7 }">
							<c:set var="methodUrl" value="${vo.img7 }"></c:set>
						</c:if>
						<img alt="选择文件" title="选择文件" id="img7methodImg" src="${img7 }"
							 style="width: 90px; height: 30px;" />
						<input class="inpFlie" type="file" id="img7methodFile" accept=".jpg,.jpeg,.gif,.png" />
					</td>
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
				url : '/shopSpecialTopic/uploadFile.html',
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
					var url = '/shopSpecialTopic/save.html';
					$.post(url, $('#editForm').serialize(), function(data) {
						if (data == 'success') {
							alert('搞定啦！');
							window.location.href = '/shopSpecialTopic/list.html';
						} else {
							alert(UNKNOWN_ERROR);
						}
					});
				}
			});
			
			
			$('#bannerFile').change(function() {
				uploadImage($(this), $('#bannerImg'), $('#banner'));
			});
			
			$('#methodFile').change(function() {
				uploadImage($(this), $('#methodImg'), $('#method'));
			});

			
			
			$('#img1methodFile').change(function() {
				uploadImage($(this), $('#img1methodImg'), $('#img1'));
			});
			$('#img2methodFile').change(function() {
				uploadImage($(this), $('#img2methodImg'), $('#img2'));
			});
			$('#img3methodFile').change(function() {
				uploadImage($(this), $('#img3methodImg'), $('#img3'));
			});
			$('#img4methodFile').change(function() {
				uploadImage($(this), $('#img4methodImg'), $('#img4'));
			});
			$('#img5methodFile').change(function() {
				uploadImage($(this), $('#img5methodImg'), $('#img5'));
			});
			$('#img6methodFile').change(function() {
				uploadImage($(this), $('#img6methodImg'), $('#img6'));
			});
			$('#img7methodFile').change(function() {
				uploadImage($(this), $('#img7methodImg'), $('#img7'));
			});
			/* 弃用时将排序设为0，启用时再重新设置排序（为了避免弃用的专题排序出现混乱） */
			var sort_val = $('#sort').val();
			$('#sort').change(function () {
				sort_val = $(this).val();
			});

			$('#invalid').change(function () {
				if ($('#invalid').is(':checked')) {
					$('#sort').val(0);
				} else {
					$('#sort').val(sort_val);
				}
			});

			// 选中左侧相应的菜单
			activeMenu('专题管理');
		});
	</script>
</body>
</html>