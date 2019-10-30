var icon_perf = '/metronic/assets/img/eap/ActiveView.gif';
var icon_server = '/metronic/assets/img/eap/base_disabled.gif';
var icon_cluster = '/metronic/assets/img/eap/workflow_16.gif';
var icon_war = '/metronic/assets/img/eap/war.gif';
var icon_service = '/metronic/assets/img/eap/sca_spring.gif';
var icon_workflow = '/metronic/assets/img/eap/workflow_16.gif';

var indexdata = 
	   [{ text: 'openEAP', isexpand:true, children: [
	       	{ text: '服务器', url:'servers.html', isexpand:false, children: [
	   			{url:'server.html',text:'车管所项目', icon: icon_server},
	   			{url:'server.html',text:'实战平台项目', icon: icon_server}
	   		]},
	   		{ text: '集群', url:''},
	   		{ text: '部署', isexpand:false, children: [
	   			{url:'/demo/page/cmdb/org/roles.htm',text:'应用', icon: icon_war},
	   			{url:'/demo/page/cmdb/org/roles.htm',text:'启动 & 停止', icon: icon_war}
	   		]},
	   		{ text: '服务', isexpand:false, children: [
	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'应用', icon: icon_service},
	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'启动 & 停止'}
	   		]},
	   		{ text: '安全', isexpand:false, children: [
  	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'应用'},
  	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'启动 & 停止'}
  	   		]},
	   		{ text: '日志', isexpand:false, children: [
  	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'应用'},
  	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'启动 & 停止'}
  	   		]},
	   		{ text: '流程', isexpand:false, children: [
  	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'应用', icon: icon_workflow},
  	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'启动 & 停止', icon: icon_workflow}
  	   		]},
	   		{ text: '性能', isexpand:false, children: [
 	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'应用', icon: icon_perf},
 	   		   {url:'/demo/page/cmdb/org/roles.htm',text:'启动 & 停止', icon: icon_perf}
 	   		]}
	   	]}
	   ];
   $('#tree').ligerTree({
       data : indexdata,
       checkbox: false,
       slide: false,
       nodeWidth: 114,
       attribute: ['nodename', 'url'],
       onSelect: function (node) {
           if (!node.data.url) return;
           $("#frame").attr("src", node.data.url);
       }
   });