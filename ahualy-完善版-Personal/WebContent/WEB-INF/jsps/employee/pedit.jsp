<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>编辑员工</title>
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
    		var name = $("#name");//姓名
    		var card_id= $("#card_id");//身份证号码
    		var phone= $("#phone");	//手机
    		var address= $("#address");//联系地址
    		var msg = "";
    		if (!/^[\u4E00-\u9FA5]{2,4}$/.test($.trim(name.val()))){
    			msg = "姓名格式不正确！！！";
    			name.focus(); 
    	    }
    		else if (!/^[1-9]\d{5}[1-9]\d{3}((0\d)|(1[0-2]))(([0|1|2]\d)|3[0-1])\d{3}([0-9]|X)$/.test($.trim(card_id.val()))){
				msg = "身份证号码格式不正确！！！X需要大写";
				card_id.focus(); 
		    } 
    		else if (!/^1([38][0-9]|4[579]|5[0-3,5-9]|6[6]|7[0135678]|9[89])\d{8}$/.test($.trim(phone.val()))){
				msg = "手机格式不正确！！！";
				phone.focus(); 
		    } 
    		else if (!/^[\u4E00-\u9FA5]{6,15}$/.test($.trim(address.val()))){
				msg = "联系地址格式不正确！！！格式--XX省XX市";
				address.focus(); 
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
        <form class="layui-form" method="POST" id="deptForm"  action="${ctx}/employee/padd">
        <input type="hidden" name="id" id="id" value="${employee.id }" >
          <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>姓名
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="name" name="name" required="" lay-verify="required"   disabled="disabled"
                  autocomplete="off" class="layui-input" value="${employee.name }">
                  <p style="color: red">${param.message}</p>
                  <p style="color: red">姓名，身份证号修改请联系部门负责人</p>
              </div>
             
          </div>
          <div class="layui-form-item" >
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>身份证号码
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="card_id" name="card_id" required="" lay-verify="required"  disabled="disabled"
                  autocomplete="off" class="layui-input" value="${employee.card_id }">
              </div>
          </div>
          
          
            <div class="layui-form-item" >
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>性别
              </label>
              <div class="layui-input-inline">
               <select id="sex_id" name="sex_id" class="valid">
                  			<c:forEach items="${requestScope.sex_list}" var="job">
						   		<c:choose>
			    					<c:when test="${employee.sex.id== job.id }">
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
                  <span class="x-red">*</span>学历
              </label>
              <div class="layui-input-inline">
               <select id="education_id" name="education_id" class="valid">
                  			<c:forEach items="${requestScope.education_list}" var="job">
						   		<c:choose>
			    					<c:when test="${employee.education.id== job.id }">
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
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>手机
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="phone" name="phone" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${employee.phone }">
              </div>
          </div>
          <div class="layui-form-item">
              <label for="phone" class="layui-form-label">
                  <span class="x-red">*</span>联系地址
              </label>
              <div class="layui-input-inline">
                  <input type="text" id="address" name="address" required="" lay-verify="required"
                  autocomplete="off" class="layui-input" value="${employee.address }">
              </div>
          </div>
  		<div class="layui-form-item">
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>职位
              </label>
              <div class="layui-input-inline">
                  <select id="job_id" name="job_id" class="valid" >
                  <option value="0">--部门选择--</option>
						   <c:forEach items="${requestScope.job_list}" var="job">
						   		<c:choose>
			    					<c:when test="${employee.job.id == job.id }">
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
              <label for="username" class="layui-form-label">
                  <span class="x-red">*</span>部门
              </label>
              <div class="layui-input-inline">
                  <select  name="dept_id" class="valid">
                  <option value="0">--部门选择--</option>
						   <c:forEach items="${requestScope.dept_list}" var="dept">
						   		<c:choose>
			    					<c:when test="${employee.dept.id == dept.id }">
			    						<option value="${dept.id }" selected="selected">${dept.name}</option>
			    					</c:when>
			    					<c:otherwise>
			    						<option value="${dept.id }">${dept.name}</option>
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