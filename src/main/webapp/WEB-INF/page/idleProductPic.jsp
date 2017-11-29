<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>产品图</title>
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
	</div>
		<input class="btn" type="button" value="主观图" id="picBtn1" />&nbsp;&nbsp;
		<input class="btn" type="button" value="局部图" id="picBtn2" />&nbsp;&nbsp;
		<input class="btn" type="button" value="凭证图" id="picBtn3" />&nbsp;&nbsp;
	
	<div>
		<img id="productPic" src="" />
	</div>	
	<div hidden="true">
		<input readonly="readonly" class="inpMain required" id="pic1"  name="panoramicPic" value="${vo.panoramicPic }" />
		<input readonly="readonly" class="inpMain required" id="pic2"  name="partPic" value="${vo.partPic }" />
		<input readonly="readonly" class="inpMain required" id="pic3"  name="voucherPic" value="${vo.voucherPic }" />
	</div>	
</body>


<script type="text/javascript">
	$(function() {
		
	})
	$("#picBtn1").click(function(){ 
		var url = $('#pic1').val();
		$('#productPic').attr('src',url);
	});
	
	
</script>
</html>