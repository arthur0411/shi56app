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
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<script type="text/javascript">
var string_url = location.href.split('#')[0];
function fnGetQueryString(key) { //正则获取url后面的参数值，如?env=dev&exp=123中可以通过fnGetQueryString('exp')=>得到123
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var result = window.location.search.substr(1).match(reg);
    return result ? decodeURIComponent(result[2]) : false;
};    
function funcUrlDel(name) {//删除url指定参数名并返回新的url
    var loca = window.location;
    var baseUrl = loca.origin + loca.pathname + "?";
    var query = loca.search.substr(1);
    if (query.indexOf(name) > -1) {
        var obj = {};
        var arr = query.split("&");
        for (var i = 0; i < arr.length; i++) {
            arr[i] = arr[i].split("=");
            obj[arr[i][0]] = arr[i][1];
        };
        delete obj[name];
        var url = baseUrl + JSON.stringify(obj).replace(/[\"\{\}]/g, "").replace(/\:/g, "=").replace(/\,/g, "&");
        return url;
    };
};

function getlinkSearch(key, reqStr) {//这个作用同fnGetQueryString(key)函数差不多，不过它将reqStr参数替代了window.searsh获取的东西,因为上个函数在删除了url的某一参数后会返回一个新的带?参数查询的链接
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var result = reqStr.substr(1).match(reg);
    return result ? decodeURIComponent(result[2]) : false;
}; 

function refreshUrl() {//强制刷新到不带二次分享参数页面
    var url = "";
    var reqStr = "";
    if (fnGetQueryString('from')) {//from为微信二次分享自带参数
        url = funcUrlDel('from');
        var reqIndex = url.indexOf('?');
        reqStr = url.substr(reqIndex);//截取去除from参数后的地址
        if (getlinkSearch('isappinstalled', reqStr)) {//isappinstalled为微信二次分享自带参数
            url = url.substr(0, url.indexOf('?'));//截取去除isappinstalled参数后的地址
            window.location.href = url;
        } else {
            window.location.href = url;
        }
    }
};



$(function(){
	
	refreshUrl();
	$.ajax({
		  type: 'POST',
		  url: "/getAccess.html",
		  data: {"url":string_url},
		  async: false, 
		  success: function(result) {
				wx.config({
					debug:true, 
				    appId:'wx6bffcec4ff6f54f5', // 必填，公众号的唯一标识
				    timestamp:result.timestamp, // 必填，生成签名的时间戳
				    nonceStr:result.nonceStr, // 必填，生成签名的随机串
				    signature:result.signature,// 必填，签名，见附录1
				    jsApiList:['onMenuShareTimeline','onMenuShareAppMessage'] // 必填，需要使用的JS接口列表，所有JS接口列表见附录2
				});
		  }
		});
	
	wx.ready(function () {  
		wx.onMenuShareTimeline({
		    title: '499元即可享受全年饰品无限换戴——饰五六', // 分享标题
		    link:string_url, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: 'http://120.77.81.97:88/special_topic/20171109/117b8062-37d3-4d86-8336-4e465410e204.jpg', // 分享图标
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
		wx.onMenuShareAppMessage({
		    title: '499元即可享受全年饰品无限换戴——饰五六', // 分享标题
		    desc: '缴纳499元成为平台年度会员，可全年无限体验到更多样，更高品质的海内外品牌轻奢饰品。', // 分享描述
		    link:string_url, // 分享链接，该链接域名或路径必须与当前页面对应的公众号JS安全域名一致
		    imgUrl: 'http://120.77.81.97:88/special_topic/20171109/117b8062-37d3-4d86-8336-4e465410e204.jpg', // 分享图标
		    type: '', // 分享类型,music、video或link，不填默认为link
		    dataUrl: '', // 如果type是music或video，则要提供数据链接，默认为空
		    success: function () { 
		        // 用户确认分享后执行的回调函数
		    },
		    cancel: function () { 
		        // 用户取消分享后执行的回调函数
		    }
		});
	
	})
	

});
	
	
</script>
</head>
	<input id="ret" type="hidden"  value=${ret}/>
	<table id="dg"></table> 
</body>  
</html>