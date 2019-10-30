<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>公告编辑页面</title>
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
    		var content= $("#content");//内容
    		var msg = "";
    		if (!/^[\u4E00-\u9FA5_a-zA-Z0-9]{2,15}$/.test($.trim(title.val()))){
    			msg = "标题格式不正确,不能包含标点";
    			title.focus(); 
    	    }
    		else if (!/^[\u4E00-\u9FA5_a-zA-Z0-9\.，。! ！]{5,30}$/.test($.trim(content.val()))){
				msg = "内容格式不正确，不能除了，。！ !之外的标点";
				content.focus(); 
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
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/notice/add">
        <input type="hidden" name="id" id="id" value="${job.id }" >
        <input type="hidden" name="user_id" id="user_id" value="${sessionScope.user_session.id}" >
          <div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>标题
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="title" name="title" required="" lay-verify="required" placeholder="不少于2个汉字"
                 autocomplete="off" class="layui-input" value="${job.title }">
              </div>
             
          </div>
        
          <div class="layui-form-item layui-form-text">
                    <label for="desc" class="layui-form-label">
                     <span class="x-red">*</span> 内容
                    </label>
                    <div class="layui-input-block">
                        <textarea placeholder="请输入内容，不少于5个汉字" id="content" name="content" class="layui-textarea">${job.content }</textarea>
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
            layer.alert("修改成功", {icon: 6},function () {
            	document.getElementById('deptForm').submit();
                // 获得frame索引
                var index = parent.layer.getFrameIndex(window.name);
                //关闭当前frame
                parent.layer.close(index);
            });
            return false;
          });
        });
    </script>
    
  </body>

</html>