<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>部门添加页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
   
   <script type="text/javascript">
    $(function(){
		$("#deptForm").submit(function(){
		
			var title = $("#title");//标题
    		var remark= $("#remark");//描述
	        var fileInput = $('#file').get(0).files[0];
	        var msg = "";
	    if(!/^[\u4E00-\u9FA5]{3,15}$/.test($.trim(title.val()))){
	    	msg = "标题格式不正确！！！";
			title.focus(); 
	    }
	    else if (!/^[\u4E00-\u9FA5]{5,30}$/.test($.trim(remark.val()))){
			msg = "内容格式不正确！！！";
			remark.focus(); 
	    } 
	    else if(!fileInput){
	    	msg = "请选择上传文件！文件不能为空";
	    }
	    if (msg != ""){
			 alert(msg);
			return false;
		}else{
			return true;
			$("#deptForm").submit();
		}
     });
   });
   </script> 
  </head>
  
  <body>
    <div class="x-body">
        <form class="layui-form" method="POST" id="deptForm" enctype="multipart/form-data" action="${ctx}/document/add">
        <input type="hidden" name="id" id="id" value="${job.id }" >
        <input type="hidden" name="user_id" id="user_id" value="${sessionScope.user_session.id}" >
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="title" name="title" required="" lay-verify="required"
               placeholder="不少于3个汉字"   autocomplete="off" class="layui-input" value="${job.title }">
              </div>
             
          </div>
        <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>描述
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="remark" name="remark" required="" lay-verify="required"
                placeholder="不少于5个汉字"   autocomplete="off" class="layui-input" value="${job.remark }">
              </div>
             
          </div>
         <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>上传文件
              </label>
              <div class="layui-input-inline">
                  <input type="file" id="file" name="file"  >
                  <p class="x-red">必须选择上传文件</p>
                  <p class="x-red">${param.message}</p>
              </div>
             
          </div>
          <div class="layui-form-item">
              <label for="L_repass" class="layui-form-label">
              </label>
              <input type="submit" value=" 提交" class="layui-btn" lay-filter="add" lay-submit=""/>
                 
          </div>
      </form>
    </div>
    <script>
          //监听提交
          form.on('submit(add)', function(data){
        	  
            console.log(data);
            //发异步，把数据提交给php
            layer.alert("增加成功", {icon: 6},function () {
            	document.getElementById('deptForm').submit();
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
               
            });
            return false;
          });
       
    </script>
    
  </body>

</html>