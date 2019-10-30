<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>创新创业型小微企业的人力资源管理平台</title>
	<meta name="renderer" content="webkit|ie-comp|ie-stand">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
  
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
    <link rel="stylesheet" href="${ctx}/public/css/font.css"> 
	<link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
    <script type="text/javascript" src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="${ctx}/public/lib/layui/layui.js" charset="utf-8"></script>
    <script type="text/javascript" src="${ctx}/public/js/xadmin.js"></script>
    <!-- 导入jquery插件 -->
	<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
	<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
	<script type="text/javascript" src="${ctx }/js/fkjava_timer.js"></script>
    
    	<script type="text/javascript">
	//这个事件不能一直点击
		  $(function() { // 页面装载执行
				$("#bt").click(function(){ // 为ID为bt的标签绑定click事件
					$.ajax({
					    method:'get',
					    url:'${ctx}/toCreateCode',
					    dataType:'json',
					    success:function(data){
					       alert("您只有三次获取注册码的机会,请务必复制此注册码   "+data.code);
					    }
					}); 
				});
			});
	
		
    </script> 
	
    <script type="text/javascript">
    /** 文档加载完成后立即执行的方法 */
    // var weeks = new Array();
    
    $(function(){
    	$("#nowTime").runTimer();
    	var calendar = showCal(); 
    	$("#currentDate").text("农历" + calendar);
    	var n1 ="admin";
    	var n2 = "manager";
    	var n = "${sessionScope.user_session.loginname}";
    	if(n1==n||n2==n){
    		$("#uservisit").css("display", "block"); 
    		$("#train").css("display", "none"); 
    		$("#admintrain").css("display", "block");
    		$("#personal").css("display", "none");
    		$("#pcheckwork").css("display", "none");
    		$("#psalary").css("display", "none");
    		$("#pcontract").css("display", "none");
    		$("#checkwork").css("display", "block");
    		$("#salary").css("display", "block");
    		$("#contract").css("display", "block");
    		$("#dept").css("display", "block"); 
    		$("#job").css("display", "block"); 
    		$("#employee").css("display", "block"); 
    		$("#peremployee").css("display", "none"); 
    		$("#notice").css("display", "block"); 
    		$("#document").css("display", "block"); 
    		$("#bt").css("display", "block"); 
    		
    	}else{
    		$("#train").css("display", "block"); 
    		$("#admintrain").css("display", "none");
    		$("#uservisit").css("display", "none"); 
    		$("#personal").css("display", "block"); 
    		$("#pcheckwork").css("display", "block");
    		$("#psalary").css("display", "block");
    		$("#pcontract").css("display", "block");
    		$("#checkwork").css("display", "none");
    		$("#salary").css("display", "none");
    		$("#contract").css("display", "none");
    		$("#dept").css("display", "none"); 
    		$("#job").css("display", "none"); 
    		$("#employee").css("display", "none"); 
    		$("#peremployee").css("display", "block"); 
    		/* if("${sessionScope.employee}"=="null"){
    			//没有查到用户，就说明，还么有关联，因此展示添加列表
    			$("#peremployee.pemployee").css("display", "block"); 
    		}else{
    			//查到用户，就说明，已经关联，因此隐藏添加列表
    			$("##peremployee.pemployee").css("display", "none"); 
    		} */
    		$("#notice").css("display", "none"); 
    		$("#document").css("display", "none");
    		$("#bt").css("display", "none");
    	}; 
    	 
    	
    	$("#exit").click(function(){
    		/** parent从主界面进行退出,避免局部刷新*/
         layer.confirm("确定要退出当前用户吗？",function(index){
        	 parent.location = "${ctx}/exit"; 
         });
    	});
    	
    	$("#main").click(function(){
    		/** parent从主界面进行退出,避免局部刷新*/
    		parent.location = "${ctx}/login?loginname=${sessionScope.user_session.loginname}&password=${sessionScope.user_session.password}";
    	})
	});
    
</script>



</head>
<body oncontextmenu="self.event.returnValue=false" onselectstart="return false">
    <!-- 顶部开始 -->
    <div class="container">
        <div class="logo" ><a href="./index?token=${sessionScope.token}&lang=zh_CN"  style="width:300px;" >创新创业型小微企业的人力资源管理平台</a></div>
        
        <div class="left_open">
            <i title="展开左侧栏" class="iconfont">&#xe699;</i>
        </div>
        
         <div class="left_open" style="margin-top:15px;">
            <a href="./index?token=${sessionScope.token}&lang=zh_CN" style="color:white;margin-left: 60px;">系统首页</a>
         </div>
         
         <div class="left_open" style="margin-top:15px;">
            <p  style="color:white;margin-left:100px;">当前时间 ：<span id="nowTime"></span></p>
         </div>
          <div class="left_open" style="margin-top:15px;">
            <p  style="color:white;margin-left:14px;"><span id="currentDate"></span></p>
         </div>
         
         <div class="left_open" style="margin-top:15px;">
            <a id="bt" href="#" style="color:white;margin-left: 60px;">获取注册码</a>
            
         </div>
         
        <ul class="layui-nav right">
          <li class="layui-nav-item">
           <a href="javascript:;">${sessionScope.user_session.username}</a>
            <dl class="layui-nav-child"> <!-- 二级菜单 -->
              <dd><a href="#" id="exit"  >注销退出</a></dd>
            </dl>
          </li>
        </ul>
        
    </div>
    <!-- 顶部结束 -->
    <!-- 中部开始 -->
     <!-- 左侧菜单开始 -->
    <div class="left-nav">
      <div id="side-nav">
        <ul id="nav">
            <li id="uservisit">
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>用户管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                
                   <li >
                        <a _href="${ctx }/uservisit/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户访问信息</cite>
                            
                        </a>
                    </li >
                    
                    
                    <li>
                        <a _href="${ctx }/user/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>用户列表</cite>
                            
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx }/user/toadd">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加用户</cite>
                            
                        </a>
                    </li>
                   
                </ul>
            </li>
            
            <!--登录用户信息管理 查看修改 查看按id查找-->
            <li id="personal">
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b8;</i>
                    <cite>个人管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx}/user/pedit?id=${sessionScope.user_session.id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>个人信息</cite>
                        </a>
                    </li >
                   
                </ul>
            </li>
            
            <li >
            
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>部门管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/dept/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>部门列表</cite>
                        </a>
                    </li >
                    <li id="dept">
                        <a _href="${ctx }/dept/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加部门</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            <li >
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>职位管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/job/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>职位列表</cite>
                        </a>
                    </li >
                     <li id="job">
                        <a _href="${ctx }/job/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加职位</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            <!-- 管理员所看员工管理界面 -->
           
            <li id="employee">
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>员工管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/employee/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>员工列表</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx }/employee/association">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加员工</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            
            <!-- 我的员工信息 -->
            <li id="peremployee">
                <a href="javascript:;">
                    <i class="iconfont">&#xe726;</i>
                    <cite>员工信息</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <!--需要和登录信息表关联，获取到员工id 第一次注册打开之后，这里是空的，需要用户去完善个人信息 -->
                    <!-- 这里展示员工信息界面这个界面不允许修改 -->
                    <li >
                        <!-- 这里首先需要默认一个员工的id，因为第一次注册登录，没有员工信息，所以这个id如何设置 -->
                        <a _href="${ctx}/employee/plist?id=${sessionScope.user_session.emp_id}"><!--session中获取员工信息的id  -->
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>个人信息</cite>
                        </a>
                    </li >
                    <!--这个是用来添加的 后面不需要参数，直接跳转添加页面-->
                    <li>
                        <!-- 这里首先需要默认一个员工的id，因为第一次注册登录，没有员工信息，所以这个id如何设置 -->
                        <a _href="${ctx}/employee/padd?id=${sessionScope.user_session.emp_id}"><!--session中获取员工信息的id  -->
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>完善个人信息</cite>
                        </a>
                    </li >
                    <!--这个是用来修改的，需要参数，跳转修改界面-->
                    <li >
                        <!-- 这里首先需要默认一个员工的id，因为第一次注册登录，没有员工信息，所以这个id如何设置 -->
                        <a _href="${ctx}/employee/pedit?id=${sessionScope.user_session.emp_id}"><!--session中获取员工信息的id  -->
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>修改个人信息</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            
            
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6ce;</i>
                    <cite>公告管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/notice/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>公告查询</cite>
                        </a>
                    </li >
                    <li id="notice">
                        <a _href="${ctx }/notice/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加公告</cite>
                        </a>
                    </li >
                   
                   
                </ul>
            </li>
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe6b4;</i>
                    <cite>下载中心</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/document/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>文档查询</cite>
                        </a>
                    </li>
                    <li id="document">
                        <a _href="${ctx }/document/add">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>上传文档</cite>
                        </a>
                    </li>
                </ul>
            </li>
            
            <!--**************************************************  -->
            <li id="checkwork">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>考勤管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/checkwork/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>考勤查询</cite>
                        </a>
                    </li >
                </ul>
 <!--************************************************************************************* -->   
 <!--新增用户请假功能  部门经理进行审批-->            
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/checkwork/adminleavelist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>请假申请</cite>
                        </a>
                    </li >
                </ul>
 <!--************************************************************************************* -->                  
            </li>
            <!--个人考勤查看  -->
            <li id="pcheckwork">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>个人考勤</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/checkwork/pedit?id=${sessionScope.user_session.che_id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看考勤</cite>
                        </a>
                    </li >
                </ul>
<!--************************************************************************************* -->
<!--新增用户请假功能  填写请假信息，做提交给数据库，为请假详情列表，-->
                 <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/checkwork/leaveadd">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>我要请假</cite>
                        </a>
                    </li >
                </ul>

<!--我的请假直接通过id去查询请假详情列表，简单的查询功能，用户可以删除自己的请假信息   -->                
                  <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/checkwork/leavelist?id=${sessionScope.user_session.id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>我的请假</cite>
                        </a>
                    </li >
                </ul>
            </li>
<!--************************************************************************************* -->            
            
            <li id="salary">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>薪酬管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/salary/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>薪酬管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            <!-- 个人薪酬管理，不提供薪酬修改 -->
            <li id="psalary">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>个人薪酬</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/salary/pedit?id=${sessionScope.user_session.sal_id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看薪酬</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            <li id="contract">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>合同管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/contract/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>合同管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            <!-- 个人合同信息查看，不提供修改 -->
            <li id="pcontract">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>个人合同</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/contract/pedit?id=${sessionScope.user_session.con_id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>查看合同</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            
            
            <li id="train">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>培训管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/train">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训计划</cite>
                        </a>
                    </li >
                    <!-- 新增培训评价及详细信息 -->
                    <li>
                        <a _href="${ctx }/train/trainlist?id=${sessionScope.user_session.id}">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>我的培训</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx }/traindata/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训资料</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            <!-- 管理员培训管理显示内容 -->
            
            <li id ="admintrain">
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>培训管理</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/train">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训计划</cite>
                        </a>
                    </li >
                    <!-- 新增详细信息，管理员，操作管理员工培训详细信息 -->
                    <li>
                        <a _href="${ctx }/train/admintrainlist">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训详情</cite>
                        </a>
                    </li >
                    <li>
                        <a _href="${ctx }/train/trainadd">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>添加培训信息</cite>
                        </a>
                    </li >
                     <li>
                        <a _href="${ctx }/traindata/list">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>培训资料管理</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            
            
            
            
            
            <li>
                <a href="javascript:;">
                    <i class="iconfont">&#xe723;</i>
                    <cite>数据可视化</cite>
                    <i class="iconfont nav_right">&#xe697;</i>
                </a>
                <ul class="sub-menu">
                    <li>
                        <a _href="${ctx }/statistics">
                            <i class="iconfont">&#xe6a7;</i>
                            <cite>数据报表</cite>
                        </a>
                    </li >
                </ul>
            </li>
            
            
            
        </ul>
      </div>
    </div>
    <!-- <div class="x-slide_left"></div> -->
    <!-- 左侧菜单结束 -->
    <!-- 右侧主体开始 -->
    <div class="page-content">
        <div class="layui-tab tab" lay-filter="xbs_tab" lay-allowclose="false">
          <ul class="layui-tab-title">
            <li class="home"><i class="layui-icon">&#xe68e;</i>我的桌面</li>
          </ul>
          <div class="layui-tab-content">
            <div class="layui-tab-item layui-show">
                <iframe src='${ctx}/welcome' frameborder="0" scrolling="yes" class="x-iframe"></iframe>
            </div>
          </div>
        </div>
    </div>
    <div class="page-content-bg"></div>
    <!-- 右侧主体结束 -->
    <!-- 中部结束 -->
    <!-- 底部开始 -->
    <div class="footer" style="background-color:RGB(43,48,59)">
        <div class="copyright" align="center"style="background-color:RGB(43,48,59)">Copyright ©2019 东北电力大学计算机学院</div>  
    </div>
    <!-- 底部结束 -->
 
</body>
</html>