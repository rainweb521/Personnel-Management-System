
function tocheckLoginname() {
		//先用js代码做校验,如果校验通过再进行ajax想服务器发送数据
		var loginname=document.getElementById("loginname").value;
			 $.ajax({
	             url:"checkLoginname",
	             type:"post",
	             data:{"loginname":loginname},
	             dataType:"text",
	             beforeSend:function(){
	             },
	             success:function(message){
	            	 document.getElementById("test").value = message;
	            	if(message!=""){
	            		 document.getElementById("loginname").focus();
	            		 $('#loginname')[0].style.border="1px solid rgb(229,58,49)";
	            	}else{
	            		 $('#loginname')[0].style.border="1px solid #ccc";
	            	}
	             }
	          });
	}

function checkLoginnameAndPassword() {
	var password=document.getElementById("password").value;
	var loginname=document.getElementById("loginname").value;
	var user_input_verifyCode=document.getElementById("user_input_verifyCode").value;
	var params = {
            "loginname":loginname,
            "password":password,
            "user_input_verifyCode":user_input_verifyCode
        }
	  $.ajax({
         url:"checkLoginnameAndPassword",
         type:"post",
         data:params,
         dataType:"text",
         beforeSend:function(){
         },
         success:function(message){
             	document.getElementById("span_id").value = message;
             	if(message!=""){
             		if(message=="登录名和密码不能为空,请重新输入"){
             			document.getElementById("loginname").focus();
             		  $('#password')[0].style.border="1px solid rgb(229,58,49)";
             		  $('#loginname')[0].style.border="1px solid rgb(229,58,49)";
             		  $('#user_input_verifyCode')[0].style.border="1px solid rgb(229,58,49)";
             		}else if(message=="密码不能为空,请输入您的密码"){
             			document.getElementById("password").focus();
               		    $('#password')[0].style.border="1px solid rgb(229,58,49)";
             		}else if(message=="校验码不能为空,请输入校验码"){
             			document.getElementById("user_input_verifyCode").focus();
               		    $('#user_input_verifyCode')[0].style.border="1px solid rgb(229,58,49)";
             		}else if(message=="登录名不能为空,请重新输入"){
             			document.getElementById("loginname").focus();
               		    $('#loginname')[0].style.border="1px solid rgb(229,58,49)";
             		}else{
             			document.getElementById("loginname").focus();
               		    $('#loginname')[0].style.border="1px solid rgb(229,58,49)";
             		}
             		
             	}else{
             		 $('#loginname')[0].style.border="1px solid #ccc";
             		 $('#password')[0].style.border="1px solid #ccc";
             		 $('#user_input_verifyCode')[0].style.border="1px solid #ccc";
             		 $("#toLoginForm").submit();
             		
             		
             	}
         }
      });
		
	}

function toregistCode() {
	
	var registCode=document.getElementById("registCode").value;
	var params = {
            "registCode":registCode
        }
	  $.ajax({
         url:"checkRegistCode",
         type:"post",
         data:params,
         dataType:"text",
         beforeSend:function(){
         },
         success:function(message){
        	 document.getElementById("span_id").value = message;
             	if(message!=""){
             		  document.getElementById("registCode").focus();
             		  $('#registCode')[0].style.border="1px solid rgb(229,58,49)";
             	}else{
             		 $('#registCode')[0].style.border="1px solid #ccc";
             		 $("#toLoginForm").submit();
             	}
         }
      });
	}
	



















