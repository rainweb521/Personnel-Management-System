<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>请假修改页面</title>
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
		
		var msg = "";
		if (!/^[0-9]*$/.test($.trim(leavedays.val()))){
			msg = "上班天数只能填写数字！";
			leavedays.focus(); 
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
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/checkwork/adminleaveedit">
        <!-- id为员工信息登录名-->
        <input type="hidden" name="leave_id" id="leave_id" value="${leave.id}" >
        
        
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>请假天数
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="leavedays" name="leavedays" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${leave.leavedays}">
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>请假类型
              </label>
              <div class="layui-input-inline">
                  <select id="leavetype" name="leavetype" class="valid">
                    <c:forEach items="${requestScope.leavetype_list}" var="job">
						   		<c:choose>
			    					<c:when test="${leave.leavetype.id == job.id }">
			    						<option value="${job.id }" selected="selected">${job.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${job.id }">${job.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
                  </select>
              </div>
          </div>
		<div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>审批状态
              </label>
              <div class="layui-input-inline">
                  <select id="leavestatus" name="leavestatus" class="valid">
                    <c:forEach items="${requestScope.leavestatus_list}" var="job">
						   		<c:choose>
			    					<c:when test="${leave.leavestatus.id == job.id }">
			    						<option value="${job.id }" selected="selected">${job.name }</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${job.id }">${job.name}</option>
			    					</c:otherwise>
			    				</c:choose>
			    			</c:forEach>
                  </select>
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