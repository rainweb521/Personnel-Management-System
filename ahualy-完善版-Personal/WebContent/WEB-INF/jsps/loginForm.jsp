<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%--   <%
        out.clear();
        out=pageContext.popBody();
    %> --%>
<!DOCTYPE html>

<html lang="zh">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>创新创业型小微企业的人力资源管理平台</title>
<meta content="" name="description" />
<meta content="" name="author" />
<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    
    <meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
<link href="${ctx}/js/metronic/css/style.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/base.css" rel="stylesheet" type="text/css" />
<link href="${ctx}/js/metronic/css/login.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" href="${ctx}/js/metronic/css/jquery.slider.css" type="text/css" />


<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
<script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/js/jquery.slider.js"></script>
<script type="text/javascript" src="${ctx}/ajax_js/check.js"></script>


<script type="text/javascript">
setTimeout(function(){document.getElementById("password_message").style.display="none";},5000);
setTimeout(function(){document.getElementById("new_message").style.display="none";},10000);

</script>

 <script type="text/javascript">
 //当项目第一次运行时，验证码类没有被请求，因此是这没有被生成验证码的
 var num=1;
$(function(){
       $('#verifyCodeImage').attr('src','${pageContext.request.contextPath }/checkCode?num='+num);
});	
</script> 
<script type="text/javascript">
function genTimestamp() {    
    var time = new Date();    
    return time.getTime();    
} 
function changeImage() {
     $('#verifyCodeImage').attr('src',
             '${pageContext.request.contextPath }/checkCode?timestamp=' + genTimestamp());
 }
</script> 
<style type="text/css">
body{
       overflow:scroll;
       overflow-x:hidden;
    }
</style>


</head>
<!-- oncontextmenu="self.event.returnValue=false"  屏蔽右键
 onselectstart="return false"   防止选择复制--> 
<body oncontextmenu="self.event.returnValue=false" onselectstart="return false" >
<div style="width: 1360px;height:48px; background-color:RGB(43,48,59) ">
      <ul style="margin-left: 40px;padding-top:6px">
        <li style="display: inline-block;"><h1><a href="loginForm.html" style="color:white;font-size:20px;">创新创业型小微企业的人力资源管理平台</a></h1></li>
        <li style="display: inline-block; margin-left: 120px;"><a  style="font-size:14px;color:rgb(130,130,130); " >用户登陆</a></li>
        <li style="display: inline-block; margin-left: 60px;"><a href="loginForm.html" style="font-size:14px;color:rgb(130,130,130); ">首页</a></li>
        <li style="display: inline-block; margin-left: 80px;"><a href="javascript:alert('本系统只提供管理员和公司管理人员登录系统！！！如若注册此系统，请联系部门负责人下发注册码 ！！！');"	  style="font-size:14px;color:rgb(130,130,130); ">帮助与文档</a></li>
        <li style="display: inline-block; margin-left: 64px;"><p  style="font-size:14px;color:rgb(130,130,130);">客服电话：4008864211</p></li>
      </ul>    
    </div> 
    <div id="main" style="width:1360px;height:610px;float:left;">
          
      <div class="container" style="background-color:rgb(73,74,95);width: 1360px;height:610px;float:left;"> 
        <!-- <div  style="background-color:green;width: 1360px;height:610px; float:left;"> -->
       <!-- 幻灯片 -->
       
       <div class="testSlider" style="margin-left:-15px;"></div>
         
		 <div id="register"  class="register" style="height:370px;margin-top:150px;margin-left:205px;opacity:0.9;">
            <div class="title">
                <span style="color: rgb(32,35,64);">系统登录</span>
                <div style="height: 10px;margin-top:15px;">
                 <input id="span_id" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red" size="45"/> 
                </div>
            </div>  <!--  -->
            <form  method="post" action="login" name="check-form" id="toLoginForm"> 
                
                <div class="default" style="margin-top:40px;">
                    <input 	data-form="uname" type="text" placeholder="请输入登录名"  onchange="tocheckLoginname()"  id="loginname" name="loginname" value="${user.loginname}"/>
                </div>
                 <div style="height: 10px;margin-top: -10px;">
                  <input id="test" disabled="disabled"  value=""style= "background-color:transparent;border:0;color: red"/> 
                 </div>
                <div class="default">
                    <input id="password" name="password"
									value="${user.password}" data-form="upwd" type="password" placeholder="请输入账号密码 " />
                </div>
                
              <div class="default" >
                    <input id="user_input_verifyCode" name="user_input_verifyCode" style="width:100px;"value="${user_input_verifyCode}"  type="text" placeholder="验证码 " />
                    <div class="vcode" id="vcodeDiv" style="float: right;">
			          		<img id="verifyCodeImage" title="验证码看不清？点击获取新验证码" src="" onclick="getValidateCode()"  
			          			style="width:86px;height:29px;cursor:hand;"/>
			          		<a id="changeVerifImageRegister" href="javascript:void(0);"  onclick="javascript:changeImage();" title="验证码看不清？点击获取新验证码">看不清图片？</a>
			          		<input name="code" type="hidden" id="code" value="dologin" />
			         	</div>
                </div> 
                <div class="submit" style="margin-top: 12px;"> 
                        
                        <a  href="javascript:;" onclick="location.href='./registCode';" style="">没有账号？立即注册</a>
                       
                        <span class="notice">
                        
                          <a href="javascript:;" onclick="location.href='./repassword';">忘记密码</a>
                        </span>
                    <button type="button" id="login-submit-btn" class="s_hover" onclick="checkLoginnameAndPassword()">安全登录</button>
                </div>
            </form> 
            
            
            <div id="password_message" style="color: red;margin-left:110px;">${message}</div>
            <div id="new_message" style="color: red;margin-left:40px;">${new_message}</div>
        </div>
       </div>
      </div>
       <!-- 底部开始 -->
     <div style="width: 1360px;height:48px; background-color:RGB(43,48,59) ;margin-top:610px;">
        <div align="center" style="padding-top:15px;color:RGB(153,153,153);">Copyright ©2019 </div>
    </div>
   		 <!-- 底部结束 -->
    
     <script>
window.onload = function(){
	var currentIndex = 0;
	$('.testSlider').slider({
		originType:'tuoyuan'
	});
}
</script>


</body>
</html>