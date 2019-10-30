<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>  
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ page isELIgnored="false" %>
  

  
  
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>请假信息列表</title>
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
    	
    	if(${count}!=0){
    		$("#count1").hide();
    		$("#count2").show();
    	}
    })
  </script>
    
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>我的请假</cite></a>
      </span>
      <a  class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${ctx }/checkwork/leavelist?id=${sessionScope.user_session.id}" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox"  lay-skin="primary" ><i class="layui-icon">&#xe605;</i></div>
            </th>
              <th>开始日期</th>
			  <th>结束日期</th>
			  <th>请假天数</th>
			  <th>请假类型</th>
			  <th>请假内容</th>
			  <th>审批状态</th>
			  <th>请假时间</th>
			  <th>操作</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.leave_list}" var="leave" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox" lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
             <td>${leave.startdata}</td>
             <td>${leave.enddata}</td>
             <td>${leave.leavedays}</td>
			 <td>${leave.leavetype.name}</td>
			 <td>${leave.content}</td>
			 <td>
					 <c:choose>
					      <c:when test="${leave.leavestatus.id==1}">${leave.leavestatus.name}</c:when>
					      <c:otherwise><font><a style="color:red">${leave.leavestatus.name}</a></font></c:otherwise>
					 </c:choose>
					  
			</td>
			 <td>${leave.creatTimeStr}</td>
            <td class="td-manage">
             
              <a title="编辑"  href='${ctx}/checkwork/toleaveedit?id=${leave.id}'>
                <i class="layui-icon">&#xe642;</i>
              </a> 
              
              <a title="删除" onclick="member_del(this,'${leave.id}')" href="javascript:;">
                <i class="layui-icon">&#xe640;</i>
              </a>
            </td>
          </tr>
         </c:forEach>
        </tbody>
      
      </table>
         <!-- 分页标签 -->
     <div style="margin-left: 400px;" id="count1">
         <fkjava:pager
	  	        pageIndex="${requestScope.pageModel.pageIndex}" 
	  	        pageSize="${requestScope.pageModel.pageSize}" 
	  	        recordCount="${requestScope.pageModel.recordCount}" 
	  	        style="digg"
	  	        submitUrl="${ctx}/checkwork/list?pageIndex={0}"/>
     </div>
	  	 
	 <div style="margin-left: 500px; display: none;" id="count2">
                <p style="color: rgb(0,97,222)">共查询到<font color="red">${count}</font>条数据</p>
       </div>
    </div>
    <script>
      
      /*用户-删除*/
      function member_del(obj,id){
          layer.confirm('确认要删除吗？',function(index){
              //发异步删除数据
              //等以后再使用异步，这里先使用
              $.get("${ctx}/checkwork/leavedelete?id="+id);
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }
    </script>
    
  </body>

</html>