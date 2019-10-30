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
        <meta name="viewport" content="width=device-width,user-scalable=yes, minimum-scale=0.4, initial-scale=0.8,target-densitydpi=low-dpi" />
        <link rel="shortcut icon" href="${ctx}/public/logo.ico" type="image/x-icon" />
         <link rel="stylesheet" href="${ctx}/public/css/font.css"> 
        <link rel="stylesheet" href="${ctx}/public/css/xadmin.css">
        <!-- 导入jquery插件 -->
		<script type="text/javascript" src="${ctx }/js/jquery-1.11.0.js"></script>
		<script type="text/javascript" src="${ctx }/js/jquery-migrate-1.2.1.js"></script>
		<script type="text/javascript" src="${ctx }/js/fkjava_timer.js"></script>
        <script type="text/javascript">
		function fontZoomA(){
	     document.getElementById('fontzoom').style.fontSize='12px';
	     document.getElementById('fontzoom').style.lineHeight='26px';
	     allChanges("12px","26px");
		}
		function fontZoomB(){
	     document.getElementById('fontzoom').style.fontSize='14px';
	     document.getElementById('fontzoom').style.lineHeight='28px';
	     allChanges("16px","28px");
		}
		function fontZoomC(){
	     document.getElementById('fontzoom').style.fontSize='16px';
	     document.getElementById('fontzoom').style.lineHeight='30px';
	     allChanges("20px","30px");
		}
		function allChanges(_fontSize,_lineHeight){
		 var allSpan=document.getElementById('fontzoom').getElementsByTagName('span');
		 var allDiv=document.getElementById('fontzoom').getElementsByTagName('div');
		 for(var i=0;i<allSpan.length;i++){
			 allSpan.item(i).style.fontSize=_fontSize;
			 allSpan.item(i).style.lineHeight=_lineHeight;
		 }
		 for(var i=0;i<allDiv.length;i++){
			 allDiv.item(i).style.fontSize=_fontSize;
			 allDiv.item(i).style.lineHeight=_lineHeight;
		 }
	 }
	
</script>
        
        <script type="text/javascript">
         $(function(){
    	    $("#nowTime").runTimer();
        })
    </script>
    
    
        
    </head>
    <body>
    <div class="x-body layui-anim layui-anim-up" style="font-family'宋体'">
       
        <h1 style="font-size: 36px;color: rgb(26,160,147);margin-left: 450px;">培训计划</h1>
	     <p style="margin-left:80px">2019-2-14   &nbsp;&nbsp;&nbsp;&nbsp;来源：创新创业型小微企业   </p>    
         <div style="margin-left:800px;margin-top:-15px;">【字体：<a href="javascript:fontZoomC();">大</a> <a href="javascript:fontZoomB();">中</a> <a  href="javascript:fontZoomA();">小</a>】 </div>
	     <hr>
	    
	    <div id="fontzoom">
		<p><font size="5px">一、培训的目的</font></p>
		<p>1.提升员工的专业知识和岗位技能,提高工作质量和绩效。</p>
		<p>2.提升公司全员的素质,适应公司不断向前发展的要求。</p>
		<p>3.构建符合公司策略和发展的培训体系，形成“学习型组织”的氛围，提升公司整体的绩效及竞争力。</p>
		<p><font size="5px">二、培训的对象、师资</font></p>
		<p>培训的对象为公司全体员工,包括中高层管理人员和基层员工。
		<p>培训师资有公司内部和公司外部两类。
		<p>公司内部培训师资,指公司内部在某些方面有专长、具备一定讲解能力的员工。
		<p>公司外部培训师资,指公司聘请的外部专业讲师。
		<p><font size="5px">三、培训的分类及内容</font></p>
		<p>(一)新员工培训</p>
		<p>新员工培训是指对新入职员工进行的培训, 主要内容包括公司企业文化、公司组织结构、相关人事制度、基本工作知识以及职业发展教育等方面。</p>
		<p>(二)部门内部培训</p>
		<p>部门内部培训是指各部门根据实际工作需要,利用内部培训师资对员工开展的有关业务知识、岗位技能等方面的交流、经验分享或培训。</p>
		<p>(三)部门交叉培训</p>
		<p>部门交叉培训是指利用公司内部培训师资,在相关业务部门之间开展的与工作内容相关的知识、技能的交流培训。</p>
		<p>(四)通用类外部培训</p>
		<p>通用类外部培训是指利用外部培训师资组织开展的全员适用的通用类知识、技能和态度培训,如电脑使用、时间管理、沟通技巧、团队建设等。 </p>
		<p>(五)专业类外部培训</p>
		<p>专业类外部培训是指利用外部培训师资开展的与业务、技术相关的知识和技能培训,包括企业管理、项目管理、专业知识等。 </p>
		<p>(六)其他类培训</p>
		<p>外派学习：1.参加专业教育、培训机构在国内举办的培训班、进修班；</p>
		<p>2.实地参观、考察：主要是同行业“标杆”企业的学习交流。</p>
		<p>个人业余进修：员工个人参加各专业指定的从业资格、执业资格及职称考试培训，以及有益于工作的读书观影等活动。</p>
		<p><font size="5px">四、培训职责</font></p>
		<p>（一） 培训管理统筹部门（通常是人力资源与行政部），负责公司培训体系的建立、推广、评估及优化，包括以下职责:</p>
		<p>1.建立并完善公司的培训体系； </p>
		<p>2.制定及完善培训管理制度、工作流程； </p>
		<p>3.执行年度培训需求调研；</p>
		<p>4.根据培训需求及公司发展策略，制定年度培训计划；</p>
		<p>5.根据年度培训计划，制定年度培训预算方案；</p>
		<p>6.组织、培养区域公司内部培训师队伍。</p>
		<p>（二）各业务部门、项目部职责：</p>
		<p>在培训统筹部门的组织和指导下，实施本部门内部培训： </p>
		<p>1.配合培训统筹部门，开发和建立部门内部的课程体系； </p>
		<p>2.配合培训统筹部门，选拔符合标准的内部人才担任培训导师，并参加内部授课；</p>
		<p>3.在培训统筹部门的指导下，制定部门内部的年度培训计划，并推动对本部门内部培训计划的落地实施。</p>
		<p><font size="5px">五、培训管理实施</font></p>
		<p>公司围绕培训需求、培训计划、培训预算、培训实施、培训评估等模块，进行培训体系的闭环管理。</p>
		<p>(一)、培训需求调查</p>
		<p>根据公司战略计划，培训统筹部门开展培训需求调查及分析，为制定年度培训计划做好前期准备。</p>
		<p>1.培训需求调研方法</p>
		<p>培训统筹部门根据公司战略目标和年度经营计划，制定培训需求调研问卷，对各职能部门及项目部进行需求调研，并根据调研结果，结合公司发展需求及员工能力与岗位匹配差距，汇总人员发展培训需求。
		<p>2.培训需求调研分工</p>
		<p>在培训统筹部门的指导下，各部门配合完成。</p>
		<p>(二)、年度培训计划制定</p>
		<p>根据培训需求调研，匹配人才发展规划，培训统筹部门结合各部门内部的年度培训计划及培训需求，制定总的年度培训计划并公布。</p>
		<p>备注：年度培训计划由培训统筹部门制定，并报人力资源与行政部审批后执行。</p>
		<p>(三)、培训预算与费用管理 </p>
		<p>依据年度培训计划，培训统筹部门对预算进行分解制定，确定各项培训费用预算，并报区域总裁、集团培训统筹部门审批后执行。</p>
		<p>培训预算专款专用，主要用于各项培养项目，包括日常通用素质培训、专题培训、讲师课酬、课程开发、培训咨询/顾问,以及培训场地费、资料费、因培训发生的交通、住宿、餐费、考试等费用。  
		<p>(四)、培训实施 </p>
		<p>根据年度培训计划，组织推动各培训项目及专题培训项目落地实施。</p>
		<p>主责部门：培训统筹部门</p>
		<p>支撑部门：各职能部门及项目部</p>
		<p>(五)、培训效果评估 </p>
		<p>1.培训考试：以线上或线下形式，组织培训学员进行培训考试，检测培训实时效果；</p>
		<p>2.培训满意度：以问卷调查形式，组织学员填写《培训满意度评估表》，对培训组织和培训内容进行满意度调查，检测培训组织效果；</p>
		<p>3.培训回访：以培训抽查形式，多维度多角度进行培训回访，检测培训实施效果；</p>
		<p>4.培训总结：培训统筹部门根据培训反馈效果，形成培训效果总结报告，指导下阶段培训工作。</p>
		</div>
      </div>
    </body>
</html>