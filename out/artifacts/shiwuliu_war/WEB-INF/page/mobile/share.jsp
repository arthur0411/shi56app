<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" 
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,minimum-scale=1.0,user-scalable=no" />
<meta name="format-detection" content="telephone=no" />
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="apple-mobile-web-app-status-bar-style" content="black" />
<meta http-equiv="pragma" content="no-cache" />
<script type="text/javascript" src="https://res.wx.qq.com/open/js/jweixin-1.2.0.js"></script>
<title>饰五六</title>
<style type="text/css">
html, body{
	padding: 0;
	margin: 0;
}

#haj_ad {
	position: absolute;
	display: none;
}

#haj_ad img {
    display:block;
	width: 100%;
	/* height: 100%; */
}


#btn_join_us {
	font-size: 10.7vw;
	position: absolute;
	bottom: 5%;
	z-index: 3;
	font-weight: bolder;
	color: black;
	text-align: center;
	width: 50%;
	left: 25%;
}

/* 微信遮罩层 */
#weixin-tip {
	display: none;
	position: fixed;
	left: 0;
	top: 0;
	background: rgba(0, 0, 0, 0.7);
	filter: alpha(opacity = 80);
	width: 100%;
	height: 100%;
	z-index: 100;
}

#weixin-tip p {
	text-align: center;
	margin-top: 10%;
	padding: 0 5%;
	position: relative;
}

#weixin-tip p img {
	max-width: 100%;
	height: auto;
}

#weixin-tip .close {
	color: #fff;
	padding: 5px;
	font: bold 20px/24px simsun;
	text-shadow: 0 1px 0 #ddd;
	position: absolute;
	top: 0;
	left: 5%;
}

</style>
</head>
<body>
	<div id="weixin-tip">
		<p><img src="/style/images/wechat/live_weixin.png" alt="微信打开" /></p>
	</div>
		
	<div hidden="true">
		<img src="/style/images/wechat/share_logo.png">
	</div>
	
	
	<div id="haj_ad">
		
		<img alt="饰五六" src="/style/images/wechat/main_pic.png">
		
		
		
		<span id="btn_join_us"><img alt="饰五六" src="/style/images/wechat/btn.png"></span>
	</div>
<input id="ret" type="hidden"  value=${ret}/>
<script type="text/javascript" src="/style/js/jquery.min.js"></script>
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
					//debug:true, 
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
		    imgUrl: 'http://120.77.81.97:88/666.jpg', // 分享图标
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
		    imgUrl: 'http://120.77.81.97:88/666.jpg', // 分享图标
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

	var userAgent = navigator.userAgent;
	var isIOS = userAgent.match(/\(i[^;]+;( U;)? CPU.+Mac OS X/); //ios终端
	var $winHeight = $(window).height(); // 当前屏幕高度
	var $btn = $('#btn_join_us'); // 立即体验按钮
	var $tip = $('#weixin-tip'); // 提示遮罩层
	
	// 使文字样式自适应屏幕
	function autoCommunityStyle() {
		var len = community.length;
		var fontSize = '5vw';
		if (len > 9) {
			fontSize = '5vw';
		} if (len == 9) {
			fontSize = '6vw';
		} else if (len == 8) {
			fontSize = '6.5vw';
		} else if (len == 7) {
			fontSize = '7.8vw';
		} else if (len == 6) {
			fontSize = '9vw';
		} else if (len == 5) {
			fontSize = '10.7vw';
		} else if (len == 4) {
			fontSize = '13vw';
		} else if (len < 4) {
			fontSize = '15vw';
		}
	}
	
	// 判断是否使用微信中的浏览器
	function isWeixin() {
		var ua = navigator.userAgent.toLowerCase();
		if (ua.match(/MicroMessenger/i) == 'micromessenger') {
			return true;
		} else {
			return false;
		}
	}
	
	// 判断是否在移动端打开
	function isMobile() {
		var agents = new Array("Android", "iPhone", "iPad", "iPod");
		var flag = false;
		for (var v = 0; v < agents.length; v++) {
			if (userAgent.indexOf(agents[v]) > 0) { flag = true; break; }
		}
		return flag;
	}
	
	// 应用宝下载APP
	function yingyongbao() {
		window.location.href="#";
	}
	
	function toAppStore() {
		window.location.href="http://itunes.apple.com/cn/app/id1251293537?mt=8";
	}

	// 直接下载官网apk
	function apkdownload() {
		window.location.href="www.shi56.com.cn";
	}
	
	if (isMobile()) {
	//	autoCommunityStyle();
		// 加载完字体再显示
		$('#haj_ad').css('display', 'block');
		
		if (isIOS) { // IOS移动端
			$btn.click(function() {
				if (isWeixin()) {
					// IOS使用应用宝中转然后自动跳转至app store
					//toAppStore();
					toAppStore();
				} else {
					// IOS中用浏览器打开此页面直接跳转至app store
					toAppStore();
				}
			});
		} else { // android移动端
			$btn.click(function() {
				if (isWeixin()) { // 微信内打开
					// android弹出使用浏览器打开的提示
					$tip.css('height', $winHeight);
					$tip.show();
					
					$tip.click(function() {
						$tip.hide();
					});
				} else {
					// android浏览器打开自动从官网下载apk
					apkdownload();
				}
			});
		}
	} else {
		// 非移动端打开直接跳转应用宝，避免出现样式问题
		apkdownload();
		//yingyongbao();
	}

});
	

</script>
</body>
</html>