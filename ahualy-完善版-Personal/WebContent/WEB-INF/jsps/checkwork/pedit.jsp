<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>考勤编辑页面</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css">
    <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    
    <script type="text/javascript">
$(function(){
	$("#deptForm").submit(function(){
		var employee_name = $("#employee_name");
		var workingdays = $("#workingdays");
		var daysleave = $("#daysleave");
		var daysout = $("#daysout");
		var msg = "";
		if (!/^[\u4E00-\u9FA5]{2,4}$/.test($.trim(employee_name.val()))){
			msg = "姓名格式不正确！！！";
			employee_name.focus(); 
	    }
		else if (!/^[0-9]*$/.test($.trim(workingdays.val()))){
			msg = "上班天数只能填写数字！";
			workingdays.focus(); 
		}
		 else if (!/^[0-9]*$/.test($.trim(daysleave.val()))){
				msg = "请假天数只能填写数字！";
				daysleave.focus(); 
			}
		 else if (!/^[0-9]*$/.test($.trim(daysout.val()))){
				msg = "迟到天数只能填写数字！";
				daysout.focus(); 
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
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/checkwork/add">
        <input type="hidden" name="id" id="id" value="${checkwork.id }" >
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="employee_name" name="employee_name" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${checkwork.employee.name}">
                  <p class="x-red">请联系管理员修改姓名等信息</p>
              </div>
             
          </div>
          
          
            <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>职位
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="employee_name" name="employee_name" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${checkwork.job.name}">
                  
              </div>
             
          </div>
          
          
            <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>部门
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="employee_name" name="employee_name" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${checkwork.dept.name}">
                  
              </div>
             
          </div>
           <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>上班天数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="workingdays" name="workingdays" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${checkwork.workingdays}">
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>请假天数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="daysleave" name="daysleave" required="" lay-verify="required"   disabled="disabled"
                  autocomplete="off" class="layui-input" value="${checkwork.daysleave}">
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>迟到天数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="daysout" name="daysout" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${checkwork.daysout}">
              </div>
             
          </div>
      </form>
    </div>
   
    
  </body>
</html>