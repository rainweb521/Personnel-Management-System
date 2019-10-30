<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <title>培训资料页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
   
  <!-- 注意：如果你直接复制所有代码到本地，上述css路径需要改成你本地的 -->
</head>
<body>
          
<!-- <blockquote class="layui-elem-quote">为节省服务器开销，以下示例均未配置真实上传接口，所以每次上传都会报提示：请求上传接口出现异常，这属于正常现象。</blockquote>    -->
          

 
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 80px;">
  <legend >选择视频文件进行上传</legend>
</fieldset>
 
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

</body>
</html>