<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>饰五六商城</title>
<script src="../../../style/js/jquery.min.js" type="text/javascript" ></script>
<script src="../../../style/js/jquery.easyui.min.js" type="text/javascript" ></script>
<script src="../../../style/js/easyui-lang-zh_CN.js" type="text/javascript" ></script>
<link rel="stylesheet" href="../../../style/css/easyui.css" type="text/css"></link>
<link rel="stylesheet" href="../../../style/css/icon.css" type="text/css"></link>
<script type="text/javascript">
	/* $(function(){
		
		$('#dg').datagrid({    
		    url:'listcart.action',    
		    columns:[[   
		    	{field:'icon',title:'pic',width:100,
		    		formatter:function(value,row){
		    	    	var str = "";
		    	    	if(value!="" || value!=null){
		    	    	str = "<img style=\"height: 50px;width: 50px;\" src=\""+value+"\"/>";
		    	        	return str;
		    	    	}	
		    		}
		    	},
		        {field:'commodityId',title:'Id',width:100},    
		        {field:'commodityName',title:'Name',width:100},    
		        {field:'commodityPrice',title:'Price',width:100,align:'right'}    
		    ]]    
		});  


		
	}) */

	for(var key in ret){  
	   if(key == 'timestamp'){
		   var tt = ret[key];
	   }
	   if(key == 'nonceStr'){
		   var ns = ret[key];
	   } 
	   if(key == 'signature'){
		   var st = ret[key];
	   }
	   if(key == 'jsapi_ticket'){
		   var jt = ret[key];
	   }
	} 
	alert(tt);
</script>
</head>
<body>   
	<table id="dg"></table> 
</body>  
</html>