<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta name="renderer" content="webkit" />
<meta http-equiv="X-UA-Compatible" content="chrome=1,IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>饰五六后台管理系统</title>
<link href="../../style/css/public.css?v=20161223" rel="stylesheet" type="text/css">
<script type="text/javascript" src="../../style/js/jquery.min.js"></script>
<script type="text/javascript" src="../../style/js/global.js"></script>
</head>
<body>
	<!--[if lt IE 9]><div class="browser-upgrade"><div class="wrapper">您的浏览器版本过低，为了保证更好的安全性和浏览体验，建议更换<a href="http://www.baidu.com/s?wd=谷歌浏览器">谷歌浏览器</a></div></div><style>body {padding-top: 40px;}.browser-upgrade {background-color: #DA7575;border-bottom: 1px solid #D65F39;color: #F0F0F0;padding: 10px 0;position: absolute;top: 0;left: 0;width: 100%;z-index: 10000;}.browser-upgrade .wrapper {width: 450px;margin: 0 auto;}.browser-upgrade a {color: yellow;margin-right: 10px;text-decoration: underline;}</style><![endif]-->
	<div id="dcWrap">
		<div id="dcHead">
			<div id="head">
				<div class="logo">
					<a href="index.html">
						<img height="25px;" width="115px;" src="../../style/images/logo.png" alt="logo">
					</a>
				</div>
				<div class="nav">
					<ul>
						<li>
							<a href="/test.html">test</a>
						</li>
						<li>
							<a href="javascript:;" onclick="toggleLeft(this);">隐藏菜单栏</a>
						</li>
						<li>
							<a href="javascript:;" onclick="goBack();">后退</a>
						</li>
						<li>
							<a href="javascript:;" onclick="goForward()">前进</a>
						</li>
						<li class="noRight">
							<a href="javascript:;" id="refreshBtn" onclick="refresh();">刷新</a>
						</li>
					</ul>
					<ul class="navRight">
						<li class="noLeft">
							<a href="javascript:;">您好，${user.username }</a>
						</li>
						<li class="noRight">
							<a href="javascript:;" onclick="logout()">退出</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<!-- dcHead 结束 -->
		<div id="dcLeft">
			<div id="menu">
				<ul style="padding-top: 4px;">
					<li id="首页" class="sub_menu">
						<a href="default.html" target="mainFrame">
							<i class="home"></i> <em>首页</em>
						</a>
					</li>
				</ul>
				<c:forEach items="${menuList}" var="menu" varStatus="vs">
					<c:if test="${menu.hasMenu}">
						<ul
							<c:choose>
							<c:when test="${(vs.index+1) == menuList.size() }">
								class="bot"
							</c:when>
							<c:otherwise>
							</c:otherwise>
							</c:choose>>
							<c:forEach items="${menu.subMenu}" var="sub">
								<c:if test="${sub.hasMenu}">
									<li id="${sub.menuName }" class="sub_menu">
										<a href="${sub.menuUrl }" target="mainFrame">
											<i class="${sub.icon }"></i> <em>${sub.menuName }</em>
										</a>
									</li>
								</c:if>
							</c:forEach>
						</ul>
					</c:if>
				</c:forEach>
			</div>
		</div>
		<div id="dcMain">
			<iframe name="mainFrame" id="mainFrame" src="default.html" onLoad="reinitIframe()"
				style="width: 100%; border: 0;"></iframe>
		</div>
		<div class="clear"></div>
		<div id="dcFooter">
			<div id="footer">
				<div class="line"></div>
				<div class="copyright">Copyright © 饰五六后台管理系统</div>
			</div>
		</div>
		<!-- dcFooter 结束 -->
		<div class="clear"></div>
	</div>

	<script type="text/javascript">
		var closeLeft = true;
		// 使iframe自适应大小显示
		function reinitIframe() {
			var iframe = document.getElementById("mainFrame");
			try {
				var bHeight = iframe.contentWindow.document.body.scrollHeight;
				var dHeight = iframe.contentWindow.document.documentElement.scrollHeight;
				var height = Math.max(bHeight, dHeight);
				iframe.height = height;
			} catch (ex) {
			}

			// 每次iframe加载完后让页面回到顶部
			$('html, body').animate({
				scrollTop : 0
			}, 0);
		}

		function logout() {
			if (confirm("确定要退出吗？")) {
				document.location = "/logout.html";
			}
		}

		//===================================
		// 左侧菜单栏开关
		//===================================
		function toggleLeft(obj) {
			if (closeLeft) {
				$("#dcLeft").css("display", "none");
				$("#dcMain").css("margin-left", "0px");
				$(obj).html("打开菜单栏");
				closeLeft = false;
			} else {
				$("#dcLeft").css("display", "block");
				$("#dcMain").css("margin-left", "179px");
				$(obj).html("隐藏菜单栏");
				closeLeft = true;
			}
		}

		$(function() {
			var locationHref = window.location.href;
			if ((locationHref.indexOf("115.29.170.224") > -1)) {
				document.title = "测试环境-后台";
			} else if ((locationHref.indexOf("localhost") > -1) || (locationHref.indexOf("192.168.") > -1)) {
				document.title = "localhost";
			}
		});
	</script>
</body>
</html>