<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>编辑商品</title>
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
		<a href="/commodity/list.html" class="actionBtn">商品运营</a>
		<c:if test="${not empty vo.commodityId }">
		修改商品
		</c:if>
		</h3>
		<form method="post" name="editForm" id="editForm" method="">
			<c:if test="${not empty vo.commodityId }">
				<input type="hidden" name="commodityId" value="${vo.commodityId }" />
			</c:if>
			<input type="hidden" name="memberLever" value="2" />
			
			<table class="tableBasic" style="width: 100%;  ">
				<tr>
					<td>商品名称：</td>
					<td>
						<input class="inpMain required" id="commodityName"  name="commodityName" value="${vo.commodityName }" />
					</td>
				</tr>
				
				<tr>
					<td><label>商品分类</label>：</td>
					<td>
						<select name="commodityTagId" id="commodityTagId" style="margin-right: 15px;">
							<option value="0">请选择</option>
							<c:forEach items="${commodityTagList}" var="list">
								<option value="${list.tagId }" <c:if test="${vo.commodityTagId==list.tagId}">selected</c:if>>${list.tagName }</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				
				
				<tr>
					<td><label>商品价格</label>：</td>
					<td>
						<input class="inpMain required" type="text" name="commodityPrice" value="${vo.commodityPrice }" />
					</td>
				</tr> 
				
				<tr>
					<td><label>划线价格</label>：</td>
					<td>
						<input class="inpMain required" type="text" name="actualPrice" value="${vo.actualPrice }" />
					</td>
				</tr>
				
				<tr>
					<td><label>是否预售</label>：</td>
					<td>
						<select class="required" name="isPresale" id="isPresale">
							<option value="no" <c:if test="${vo.isPresale=='no'}">selected</c:if>>否</option>
							<option value="yes" <c:if test="${vo.isPresale=='yes'}">selected</c:if>>是</option>
							
						</select>
					</td>
				</tr>
				<tr>
					<td><label>是否上架</label>：</td>
					<td>
						<select class="required" name="isUpjia" id="isUpjia">
							<option value="yes" <c:if test="${vo.isUpjia=='yes'}">selected</c:if>>是</option>
							<option value="no" <c:if test="${vo.isUpjia=='no'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><label>是否推荐</label>：</td>
					<td>
						<select class="required" name="isRecommend" id="isRecommend">
							<option value="yes" <c:if test="${vo.isRecommend=='yes'}">selected</c:if>>是</option>
							<option value="no" <c:if test="${vo.isRecommend=='no'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				<!-- <tr>
					<td><label>可售库存</label>：</td>
					<td>
						<input class="inpMain required" name="rentStock" value="${vo.rentStock }" />
					</td>
				</tr> -->
				<tr>
					<td><label>库存</label>：</td>
					<td>
						<input class="inpMain required" name="saleStock" value="${vo.saleStock }" />
					</td>
				</tr>
				
				<tr>
					<td><label>是否单品推荐</label>：</td>
					<td>
						<select class="required" name="brandTopic" id="brandTopic">
							<option value="1" <c:if test="${vo.brandTopic=='1'}">selected</c:if>>是</option>
							<option value="0" <c:if test="${vo.brandTopic=='0'}">selected</c:if>>否</option>
						</select>
					</td>
				</tr>
				
				
				<tr>
					<td><label>品牌</label>：</td>
					<td>
						<input type="hidden" id="commodityBrand" value="${vo.brandId }" />
						<select class="required" name="brandId" id="brandId">
						</select>
					</td>
				</tr>
				<tr>
					<td><label>材质</label>：</td>
					<td>
						<input class="inpMain required" name="material" value="${vo.material }" />
					</td>
				</tr>
				
					
				
				<tr>
					<td><label>可交易方式</label>：</td>
					<td>
						<select class="required" name="dealWay" id="dealWay">
							<option value="2" <c:if test="${vo.dealWay=='2'}">selected</c:if>>可租可售</option>
							<option value="1" <c:if test="${vo.dealWay=='1'}">selected</c:if>>只可售</option>
						</select>
					</td>
				</tr>
				<tr>
					<td><label>产地</label>：</td>
					<td>
						<input class="inpMain required" name="producer" value="${vo.producer }" />
					</td>
				</tr>
				
				<tr>
					<td><label>尺寸</label>：</td>
					<td>
						<input class="inpMain required" id="commoditySize" name="commoditySize" value="${vo.commoditySize }" />
					</td>
				</tr>
				
				<tr>
					<td><label>描述</label>：</td>
					<td>
						<input class="inpMain required" name="commodityDescribe" value="${vo.commodityDescribe }" />
					</td>
				</tr>
			
				<tr>
					<td><label>买手昵称</label>：</td>
					<td>
						<input class="inpMain required" name="buyingNickname" id="buyingNickname" value="${vo.buyingNickname }" />
					</td>
				</tr>
			
				<tr>
					<td></td>
					<td>
						<input id="btn_submit" class="btn" type="button" value="提交" />
					</td>
				</tr>
			</table>
		</form>
	</div>
			
</body>


<script type="text/javascript">
	$(function() {
	
		//var csize = $('#commoditySize').val();
		var cbuyingName = $('#buyingNickname').val();
		//if(csize == ''){
			//$('#commoditySize').val('均码');
		//}
		if(cbuyingName == ''){
			$('#buyingNickname').val('饰五六');
		}
	
		$.post("listBrand.html",
			  function(data){
   		 			for(var i=0;i<data.length;i++){
   		 				$("#brandId").append("<option value='"+data[i].id+"'>"+data[i].name+"</option>");
   		 			}
   		 			var brandId = $('#commodityBrand').val();
   		 			 $("#brandId").val(brandId);  
 			 }
 			 );
		
		
		$('#btn_submit').click(function() {
				if (confirm('确定不是手抖吗？')) {
				
					var commodityName = $("#commodityName").val();
					if(commodityName=='')
					{
						alert('商品名称不能为空！');
						return;	
					}
					
					var commodityTagId = $("#commodityTagId").val();
					if(commodityTagId=='0')
					{
						alert('商品分类不能不选！');
						return;	
					}
					
					$(this).val('正在提交...');
					$(this).prop('disabled', 'disabled');
					
					var url = '/commodity/save.html';
					$.post(url, $('#editForm').serialize(), function(data) {
						if (data.error == '0') {
							alert('好了！');
							self.location=document.referrer;
							//window.location.href = '/commodity/list.html?page.currentPage='+vo.page.currentPage;
						} else {
							alert(data.msg);
							$('#btn_submit').val('提交');
							$('#btn_submit').removeProp('disabled');
						}
					});
				}
		});
			});
	
	
	
</script>
</html>