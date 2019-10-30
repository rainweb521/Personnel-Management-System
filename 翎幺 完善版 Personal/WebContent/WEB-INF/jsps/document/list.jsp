<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
  
  <head>
    <meta charset="UTF-8">
    <title>文档信息</title>
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
    	/** 下载文档功能 */
    	$("a[id^='down_']").click(function(){
    		/** 得到需要下载的文档的id */
    		var id = this.id.replace("down_","");
    		/** 下载该文档 */
    		window.location = "${ctx}/document/downLoad?id="+id;
    	})
    })
    </script>
    
    <script type="text/javascript">
    $(function(){
    	if(${count}!=0){
    		$("#count1").hide();
    		$("#count2").show();
    	}
    	var username = "${sessionScope.user_session.loginname}";
    	if(username=="admin"||username=="manager"){
    		$("#aaa").show(); 
    		$("#bbb").show(); 
    		$("#do").css("display", "block"); 
    		$("#ID").css("display", "block"); 
    		$('tr').find('td:eq(0)').show();
    		$('tr').find('td:eq(8)').show();
    	}else{
    		$("#aaa").hide();
    		$("#bbb").hide(); 
    		$("#do").css("display", "none"); 
    		$("#ID").css("display", "none");
    		$('tr').find('td:eq(8)').hide();
    	};
    }) 
  </script>
  </head>
  
  <body>
    <div class="x-nav">
      <span class="layui-breadcrumb">
        <a href="">首页</a>
        <a>
          <cite>文档信息</cite></a>
      </span>
            <button id="aaa" type="button" onclick="location.href='${ctx}/document/add'" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:innert;margin-left:75%;;"  lay-submit="" lay-filter="sreach"><i class="layui-icon"></i>增加</button>
      
      <a id="bbb" class="layui-btn layui-btn-small" style="line-height:1.6em;margin-top:3px;float:right" href="${ctx }/document/list" title="刷新">
        <i class="layui-icon" style="line-height:30px">ဂ</i></a>
    </div>
    <div class="x-body">
      <div class="layui-row" style="" align="center">
        <form class="layui-form layui-col-md12 x-so" method="get" action="${ctx }/document/list">
          <input type="text" name="content" style="width:50%;"  placeholder="请输入查找内容" autocomplete="off" class="layui-input">
          <button class="layui-btn"  lay-submit="" lay-filter="sreach"><i class="layui-icon">&#xe615;</i></button>
        </form>
      </div>
      
     
      
      <table class="layui-table">
        <thead>
          <tr>
            <th>
              <div class="layui-unselect header layui-form-checkbox" lay-skin="primary"><i class="layui-icon">&#xe605;</i></div>
            </th>
            <th>ID</th>
            <th>标题</th>
            <th>描述</th>
            <th>文件名</th>
            <th>发布日期</th>
            <th>发布用户</th>
            <th>下载</th>
            <th id="do">操作</th>
        </thead>
        <tbody>
        <c:forEach items="${requestScope.list}" var="dept" varStatus="stat">
     <tr>
            <td>
              <div class="layui-unselect layui-form-checkbox"  lay-skin="primary" data-id='2'><i class="layui-icon">&#xe605;</i></div>
            </td>
            
            <td>${dept.id}</td>
            <td>${dept.title }</td>
            <td>${dept.remark }</td>
            <td>${dept.filename }</td>
            <td>${dept.creatTimeStr}</td>
            <td>${dept.user.username }</td>
            <td align="center"  width="40px;"><a href="#" id="down_${dept.id }">
							<img width="20" height="20" title="下载" src="${ctx }/public/images/downLoad.png"/></a>
					  </td>
            <td class="td-manage">
              
              <a title="编辑"  href='${ctx}/document/edit?id=${dept.id}'>
                <i class="layui-icon">&#xe642;</i>
              </a>
              <a title="删除" onclick="member_del(this,'${dept.id }')" href="javascript:;">
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
	  	        submitUrl="${ctx}/document/list?pageIndex={0}"/>
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
              $.get("${ctx}/document/delete?id="+id);
              $(obj).parents("tr").remove();
              layer.msg('已删除!',{icon:1,time:1000});
          });
      }

      function delAll (argument) {
        var data = tableCheck.getData();
        layer.confirm('确认要删除吗？'+data,function(index){
            //捉到所有被选中的，发异步进行删除
            layer.msg('删除成功', {icon: 1});
            $(".layui-form-checked").not('.header').parents('tr').remove();
        });
      }
    </script>
  </body>

</html>