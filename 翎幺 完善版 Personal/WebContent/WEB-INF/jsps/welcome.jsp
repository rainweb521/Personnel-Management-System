<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!doctype html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>欢迎页面</title>
        <meta name="renderer" content="webkit">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
         <link rel="stylesheet" href="${ctx}/public/css/font.css"> 
         <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
        <!-- 导入jquery插件 -->
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript" src="${ctx }/js/fkjava_timer.js"></script>
		<script type="text/javascript" src="https://cdn-hangzhou.goeasy.io/goeasy.js"></script>

        <script type="text/javascript">
         $(function(){
    	    $("#nowTime").runTimer();
        })
    </script>
    <!--方式一   ajax实现轮询  -->
<!-- <script type="text/javascript">
//使用ajax加载数据 
setInterval("test()",3000);
function test(){
	$.ajax({
	    method:'post',
	    url:'${ctx}/notice/newMessage',
	    dataType:"json",
	    success:function(data){
	    	if(data!=null&&"${sessionScope.user_session.loginname}"!="admin"
	    		&&"${sessionScope.user_session.loginname}"!="manager"){
	    		alert("有新的公告发布，请转到公告页查看！！！");
	    	} 
	    }
	}); 
}
</script> -->

<!-- 方式二GoEasy实现消息推送  这个页面直接进行接收消息就行了 -->
<script type="text/javascript">
var goEasy = new GoEasy({
appkey: 'BC-0706aa93d6614a2db50b660458b42ff5'
});
//接收消息	
goEasy.subscribe({
         channel:"myChannel",
         onMessage:function(message){
        	 if("${sessionScope.user_session.loginname}"!="admin"
 	    		&&"${sessionScope.user_session.loginname}"!="manager"){
                 alert(message.content);
        	 }
         }
 });
</script>
<!-- 异地登录消息提示 -->
<script type="text/javascript">
var goEasy = new GoEasy({
appkey: 'BC-0706aa93d6614a2db50b660458b42ff5'
});
//接收消息	
goEasy.subscribe({
         channel:"myChannel2",
         onMessage:function(message){
                 alert(message.content);
         }
 });
</script>

<!--员工信息完善消息提示 -->
<script type="text/javascript">

</script>

    </head>
    <body oncontextmenu="self.event.returnValue=false" onselectstart="return false">
    <div class="x-body layui-anim layui-anim-up">
        <blockquote class="layui-elem-quote">欢迎
            <span class="x-red">${sessionScope.user_session.username}</span>登录系统！当前时间:<span id="nowTime"></span></blockquote>
     
        <fieldset class="layui-elem-field">
            <legend>系统通知</legend>
            <div class="layui-field-box">
                <table class="layui-table" lay-skin="line">
                    <tbody>
                        <tr>
                            <td >
                                <a class="x-a" href="./index?token=${sessionScope.token}&lang=zh_CN" target="_blank">创新创业型小微企业的人力资源管理平台上线了!!!</a>
                            </td>
                        </tr>
                        
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>系统信息</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        
                        <tr>
                            <th>系统版本</th>
                            <td>2019-03.0.1</td></tr>
                        <tr>
                            <th>服务器地址</th>
                            <td>本机服务</td></tr>
                        <tr>
                            <th>操作系统</th>
                            <td>WINDOWS10</td></tr>
                        <tr>
                            <th>运行环境</th>
                            <td>Apache/Tomcat v8.5 (Win64) JDK1.8.0_131</td></tr>
                       
                        <tr>
                            <th>MYSQL版本</th>
                            <td>5.7.21</td></tr>
                      
                        <tr>
                            <th>上传附件限制</th>
                            <td>2M</td></tr>
                        <tr>
                            <th>执行时间限制</th>
                            <td>30s</td></tr>
                        <tr>
                            <th>剩余空间</th>
                            <td>86015.2M</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <fieldset class="layui-elem-field">
            <legend>开发团队</legend>
            <div class="layui-field-box">
                <table class="layui-table">
                    <tbody>
                        <tr>
                            <th>版权所有</th>
                            <td>东北电力大学计算机学院</td>
                        </tr>
                        <tr>
                            <th>开发者</th>
                            <td>安斌杰</td></tr>
                    </tbody>
                </table>
            </div>
        </fieldset>
        <blockquote class="layui-elem-quote layui-quote-nm">感谢倪老师的指导！</blockquote>
    </div>
        <script>
        var _hmt = _hmt || [];
        (function() {
          var hm = document.createElement("script");
          hm.src = "https://hm.baidu.com/hm.js?b393d153aeb26b46e9431fabaf0f6190";
          var s = document.getElementsByTagName("script")[0]; 
          s.parentNode.insertBefore(hm, s);
        })();
        </script>
    </body>
</html>