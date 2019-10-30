<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<link rel="stylesheet" type="text/css" href="${ctx}/public/css/error_style.css"/>
<link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
<head>
    <title>错误页面</title>
</head>
<body>
<div class="img404">
	　</div>
<h2>抱歉，找不到您要的页面……</h2>
<h3 class="wearesorry">We&#39;re sorry but the page your are looking for is Not Found...</h3>
<div class="content">
	仔细找过啦，没有发现你要找的页面。最可能的原因是： 
	<ul>
		<li>在地址中可能存在键入错误。 </li>
		<li>当你点击某个链接时，它可能已过期。 </li>
	</ul>
	<div class="show14">
		<ul>
			<li><div>我将会在<font id="sp" style="color: red;"></font>秒之后跳转回系统首页，给你不好的体验，耽误你了！！！</div></li>
		</ul>
	</div>
</div>
	<script type="text/javascript">
		onload=function(){
			setInterval(go, 1000);
		};
		var x=6; //利用了全局变量来执行
		function go(){
		x--;
			if(x>0){
			document.getElementById("sp").innerHTML=x;  //每次设置的x的值都不一样了。
			}else{
			location.href='./loginForm.html';
			}
		}
	</script>
</body>
</html>