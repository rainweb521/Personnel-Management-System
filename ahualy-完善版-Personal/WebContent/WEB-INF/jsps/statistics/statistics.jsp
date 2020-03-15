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
		<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/echarts/dist/echarts.min.js"></script>
		<script src="https://cdn.jsdelivr.net/npm/v-charts/lib/index.min.js"></script>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/v-charts/lib/style.min.css">
		<script src="https://cdn.jsdelivr.net/npm/echarts-amap/dist/echarts-amap.min.js"></script> 
		<script src="https://cdn.jsdelivr.net/npm/echarts/dist/extension/bmap.min.js"></script>
        <style type="text/css">
	   p{
	      font-family: "宋体","仿宋",sans-serif;
	      text-align: left;
	      font-size: 14px;
	   }
	</style>
      <script type="text/javascript" src="http://echarts.baidu.com/gallery/vendors/echarts/echarts-all-3.js"></script>
       

<script type="text/javascript">
//使用ajax加载数据 
$.ajax({
    method:'post',
    url:'${ctx}/echartsData',
    dataType:'json',
    success:function(data){
        initChat(data);
    }
}); 
function initChat(data){
var myChart = echarts.init(document.getElementById('mainChart'));
    option = {
            title : {
                text: '员工男女比例统计',
                subtext: '内部数据',
                x:'center'
            },
            tooltip : {
                trigger: 'item',
                formatter: "{a} <br/>{b} : {c} ({d}%)"
            },
            legend: {
                orient: 'vertical',
                left: 'left',
                data: data
            },
            series : [
                {
                    name: '男女数量',
                    type: 'pie',
                    radius : '55%',
                    center: ['50%', '60%'],
                    data:data,
                    itemStyle: {
                        emphasis: {
                            shadowBlur: 10,
                            shadowOffsetX: 0,
                            shadowColor: 'rgba(0, 0, 0, 0.5)'
                        }
                    }       
                }
            ]
        },
                myChart.setOption(option);
};
</script> 

<script type="text/javascript">

//使用ajax加载数据 
$.ajax({
    method:'post',
    url:'${ctx}/echartsData1',
    dataType:'json',
    success:function(data){
        initChat1(data);
    }
}); 
function initChat1(data){
	var xA = [];
	var yA = [];
    //将获取到的json数据列表清洗数据后push到xA,yA两个坐标轴 数据列表中
    //注意，此处循环函数可以用于位置数量的数据，不必提前预知数据量大小
    for(var i=0;i<data.length;i++){
    	xA.push(data[i].name);
    	yA.push(data[i].value);
    }
	//通过 echarts.init 方法初始化一个 echarts 实例
var myChart = echarts.init(document.getElementById('mainChart1'));
	var option = {
			title: {
	            text: '各职位员工人数',
	            subtext: '内部数据',
	            x:'center'
	        },
	     /*    ['职员','java开发工程师','java高级开发工程师','架构师','经理','总经理','java中级开发工程师','系统管理员','主管'] */
	        tooltip: {},
	        legend: {
	        	orient: 'vertical',
	            data:data ,
	            left: 'left'
	        },
	       
	        itemStyle: {
	            normal: {
	                // 随机显示
	         color:function(d){return "#"+Math.floor(Math.random()*(256*256*256-1)).toString(16);
	         }
	         },
	        },
	        grid: {
	            y2: 70
	        },
	        xAxis: {
	            data: xA,
	            axisLabel: {
	                interval: 0,
	                rotate: -25
	            }
	        },
	        yAxis: {},
	        series: [{
	            name: '员工人数',
	            type: 'bar',
	            data: yA
	        }]
	};
	 myChart.setOption(option);
	};
</script> 
</head>
<body>

<div style="width:100%;height:190%;margin-top:-30px; border:0;" class="main_tabbor">
 	<h1 align="center" style="color:  rgb(26,160,147); font-size:30px; margin-top:30px">数据统计报表</h1>
 	<hr>
 	
 	
 	<div   style="height:320px;width:320px;margin-top: 50px;margin-left:50px;">
    	 <div id="mainChart" style="border: 2px solid rosybrown; width:100%; height:100%;"></div>
	</div>

 	<div  style="height:320px;width:660px;margin-left:400px;margin-top:-320px;">
   		 <div id="mainChart1" style="border: 2px solid rosybrown; width:100%; height:100%;"></div> 
	</div>
	
	<div id="app1" style="width: 480px;height: 400px; border:2px solid rosybrown;margin-top:50px;margin-left:50px;">
     
     <ve-line :data="chartData" :settings="chartSettings"></ve-line>
   </div>
	<script>
	//使用ajax加载数据 
	$.ajax({
	    method:'post',
	    url:'${ctx}/echartsData2',
	    dataType:'json',
	    success:function(data){
	    	
	        initChat2(data);
	    }
	}); 
	function initChat2(data){
		var yA = [];
		var yB = [];
		var yC = [];
		var yD = [];
		var yE = [];
		
	    //将获取到的json数据列表清洗数据后push到xA,yA两个坐标轴 数据列表中
	    //注意，此处循环函数可以用于位置数量的数据，不必提前预知数据量大小
		 for(var i=0;i<data.length;i++){
	    	yA.push(data[i].searchengine);
	    	yB.push(data[i].allianceadvertising);
	    	yC.push(data[i].mailmarketing);
	    	yD.push(data[i].directaccess);
	    	yE.push(data[i].videoadvertising);
	     };
	    	new Vue({
	    		  el: '#app1',
	    		  data () {
	    		    this.chartSettings = {
	    		      title : '一周处理业务折线统计',  
	    		      metrics: ['邮件营销', '联盟广告', '视频广告','直接访问','搜索引擎'],
	    		      dimension: ['日期']
	    		    }
	    		    return {
	    		      chartData: {
	    		        columns: ['日期', '邮件营销', '联盟广告', '视频广告','直接访问','搜索引擎'],
	    		       
	    		        rows: [
	    		          { '日期': '星期一', '邮件营销': yA[0], '联盟广告': yB[0], '视频广告': yC[0],'直接访问':yD[0],'搜索引擎':yE[0]},
	    		          { '日期': '星期二', '邮件营销': yA[1], '联盟广告': yB[1], '视频广告': yC[1],'直接访问':yD[1],'搜索引擎':yE[1]},
	    		          { '日期': '星期三', '邮件营销': yA[2], '联盟广告': yB[2], '视频广告': yC[2],'直接访问':yD[2],'搜索引擎':yE[2]},
	    		          { '日期': '星期四', '邮件营销': yA[3], '联盟广告': yB[3], '视频广告': yC[3],'直接访问':yD[3],'搜索引擎':yE[3]},
	    		          { '日期': '星期五', '邮件营销': yA[4], '联盟广告': yB[4], '视频广告': yC[4],'直接访问':yD[4],'搜索引擎':yE[4]},
	    		          { '日期': '星期六', '邮件营销': yA[5], '联盟广告': yB[5], '视频广告': yC[5],'直接访问':yD[5],'搜索引擎':yE[5]},
	    		          { '日期': '星期日', '邮件营销': yA[6], '联盟广告': yB[6], '视频广告': yC[6],'直接访问':yD[6],'搜索引擎':yE[6]}
	    		        ]
	    		      }
	    		    }
	    		  }
	    		})
	    
	};
 
	</script>
	
	<div id="app" style="width: 500px;height: 400px; border:2px solid rosybrown;margin-top:-404px;margin-left:560px;margin-bottom: 15px;">
      
    <ve-map :data="chartData"></ve-map>
   </div>
<script>
//使用ajax加载数据 
$.ajax({
    method:'post',
    url:'${ctx}/echartsData3',
    dataType:'json',
    success:function(data){
        initChat3(data);
    }
}); 

function initChat3(data){
	var y2 = 0;
	var y3 = 0;
	var y4 = 0;
	var y5 = 0;
	var y6 = 0;
	var y7 = 0;
	var y8 = 0;
	var y9 = 0;
	var y10 = 0;
	var y11 = 0;
	var y12 = 0;
	var y13 = 0;
	var y14 = 0;
	var y15 = 0;
	var y16 = 0;
	var y17 = 0;
	var y18 = 0;
	var y19 = 0;
	var y20 = 0;
	var y21 = 0;
	var y22 = 0;
	var y23 = 0;
	var y24 = 0;
	var y25 = 0;
	var y26 = 0;
	var y27 = 0;
	var y28 = 0;
	var y29 = 0;
	var y30 = 0;
	var y31 = 0;
	var y32 = 0;
	var y33 = 0;
	var y34 = 0;
	var y35 = 0;
	if(data['北京']!=null){
		var y2 = data['北京'];
	}
	if(data['天津']!=null){
		var y3 = data['天津'];
	}
	if(data['上海']!=null){
		var y4 = data['上海'];
	}
	if(data['重庆']!=null){
		var y5 = data['重庆'];
	}
	if(data['河北']!=null){
		var y6 = data['河北'];
	}
	if(data['山西']!=null){
		var y7= data['山西'];
	}
	if(data['辽宁']!=null){
		var y8 = data['辽宁'];
	}
	if(data['吉林']!=null){
		var y9 = data['吉林'];
	}
	if(data['黑龙']!=null){
		var y10= data['黑龙'];
	}
	if(data['江苏']!=null){
		var y11 = data['江苏'];
	}
	if(data['浙江']!=null){
		var y12= data['浙江'];
	}
	if(data['安徽']!=null){
		var y13 = data['安徽'];
	}
	if(data['福建']!=null){
		var y14 = data['福建'];
	}
	if(data['江西']!=null){
		var y15 = data['江西'];
	}
	if(data['山东']!=null){
		var y16 = data['山东'];
	}
	if(data['河南']!=null){
		var y17 = data['河南'];
	}
	if(data['湖北']!=null){
		var y18 = data['湖北'];
	}
	if(data['湖南']!=null){
		var y19 = data['湖南'];
	}
	if(data['广东']!=null){
		var y20 = data['广东'];
	}
	if(data['海南']!=null){
		var y21 = data['海南'];
	}
	if(data['四川']!=null){
		var y22 = data['四川'];
	}
	if(data['贵州']!=null){
		var y23 = data['贵州'];
	}
	if(data['云南']!=null){
		var y24 = data['云南'];
	}
	if(data['陕西']!=null){
		var y25 = data['陕西'];
	}
	if(data['甘肃']!=null){
		var y26 = data['甘肃'];
	}
	if(data['青海']!=null){
		var y27 = data['青海'];
	}
	if(data['台湾']!=null){
		var y28 = data['台湾'];
	}
	if(data['内蒙']!=null){
		var y29 = data['内蒙'];
	}
	if(data['广西']!=null){
		var y30 = data['广西'];
	}
	if(data['西藏']!=null){
		var y31= data['西藏'];
	}
	if(data['宁夏']!=null){
		var y32 = data['宁夏'];
	}
	if(data['新疆']!=null){
		var y33= data['新疆'];
	}
	if(data['香港']!=null){
		var y34 = data['香港'];
	}
	if(data['澳门']!=null){
		var y35 = data['澳门'];
	}
	new Vue({
		  el:'#app',
		  data() {
		    return {
		      chartData: {
		        columns: ['位置', '人数'],
		        rows: [
		          { '位置': '北京', '人数': y2},
		          { '位置': '天津', '人数': y3},
		          { '位置': '上海', '人数': y4},
		          { '位置': '重庆', '人数': y5},
				  { '位置': '河北', '人数': y6},
				  { '位置': '山西', '人数': y7},
				  { '位置': '辽宁', '人数': y8},
				  { '位置': '吉林', '人数': y9},
				  { '位置': '黑龙江', '人数': y10},
				  { '位置': '江苏', '人数': y11},
				  { '位置': '浙江', '人数': y12},
				  { '位置': '安徽', '人数': y13},
				  { '位置': '福建', '人数': y14},
				  { '位置': '江西', '人数': y15},
				  { '位置': '山东', '人数': y16},
				  { '位置': '河南', '人数': y17},
				  { '位置': '湖北', '人数': y18},
				  { '位置': '湖南', '人数': y19},
				  { '位置': '广东', '人数': y20}, 
				  { '位置': '海南', '人数': y21},
				  { '位置': '四川', '人数': y22},
				  { '位置': '贵州', '人数': y23},
				  { '位置': '云南', '人数': y24},
				  { '位置': '陕西', '人数': y25},
				  { '位置': '甘肃', '人数': y26},
				  { '位置': '青海', '人数': y27},
			      { '位置': '台湾', '人数': y28},
				  { '位置': '内蒙古', '人数': y29},
				  { '位置': '广西', '人数': y30},
				  { '位置': '西藏', '人数': y31},
				  { '位置': '宁夏', '人数': y32},
				  { '位置': '新疆', '人数': y33},
				  { '位置': '香港', '人数': y34},
				  { '位置': '澳门', '人数': y35}
							
		        ]
		      }
		    }
		  }
		})
	
};

	
</script>
</div>	
</body> 

</html>